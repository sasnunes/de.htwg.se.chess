package de.htwg.chess.model.impl;

import static de.htwg.chess.model.impl.FieldConstants.*;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

import de.htwg.chess.MyActor;
import de.htwg.chess.model.Color;
import de.htwg.chess.model.IChessboard;
import de.htwg.chess.model.IChesspiece;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.ITeam;
import de.htwg.chess.model.MoveCheckerVisitor;

public class Team implements ITeam {
	private Color color;
	private List<IChesspiece> pieceList;
	private IChessboard chessboard;
	private MoveCheckerVisitor mc;
	private IChesspiece king;

	private final ActorSystem system = ActorSystem.create("MyActorSystem");
	private final Props props;

	public Team(Color color, IChessboard chessboard, MoveCheckerVisitor mc) {
		this.chessboard = chessboard;
		this.color = color;
		this.mc = mc;
		this.props = Props.create(MyActor.class, mc);
		pieceList = new LinkedList<>();
		switch (color) {
		case WHITE:
			backRowInit(ONE);
			pawnRowInit(TWO);
			break;
		case BLACK:
			backRowInit(EIGHT);
			pawnRowInit(SEVEN);
			break;
		default:
			throw new IllegalArgumentException(color.toString() + " must be WHITE or BLACK");
		}
		updatePosMoves();
	}

	private void pawnRowInit(int y) {
		for (int x = A; x <= H; x++)
			pieceList.add(new Pawn(color, chessboard.getField(x, y)));
	}

	private void backRowInit(int y) {
		pieceList.add(new Rook(color, chessboard.getField(A, y)));
		pieceList.add(new Knight(color, chessboard.getField(B, y)));
		pieceList.add(new Bishop(color, chessboard.getField(C, y)));
		pieceList.add(new Queen(color, chessboard.getField(D, y)));
		king = new King(color, chessboard.getField(E, y));
		pieceList.add(king);
		pieceList.add(new Bishop(color, chessboard.getField(F, y)));
		pieceList.add(new Knight(color, chessboard.getField(G, y)));
		pieceList.add(new Rook(color, chessboard.getField(H, y)));
	}

	@Override
	public void move(IChesspiece cp, IField target) {
		if (pieceList.contains(cp)) {
			updatePosMoves();
			cp.move(target);
			updatePosMoves();
		}
	}

	@Override
	public List<IChesspiece> getPieceList() {
		return pieceList;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Color opponent() {
		if (this.color == Color.WHITE)
			return Color.BLACK;
		else
			return Color.WHITE;
	}

	@Override
	public void updatePosMoves() {
		//create an actor
		ActorRef actor = system.actorOf(props);
		for (IChesspiece piece : pieceList) {
			//send one chesspiece per message
			actor.tell(piece, actor);
		}
		actor.tell(PoisonPill.getInstance(), ActorRef.noSender());
		while (!actor.isTerminated()) {
			//wait
		}
	}

	@Override
	public void addChesspiece(IChesspiece cp) {
		pieceList.add(cp);
		updatePosMoves();
	}

	@Override
	public IChesspiece getKing() {
		return king;
	}

	@Override
	public String toString() {
		return color.toString();
	}

	@Override
	public void removeChesspiece(IChesspiece cp) {
		pieceList.remove(cp);
		updatePosMoves();
	}

}