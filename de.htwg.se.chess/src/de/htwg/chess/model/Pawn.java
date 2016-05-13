package de.htwg.chess.model;

import java.util.List;
import de.htwg.chess.model.Team.Color;

public final class Pawn extends Chesspiece {

	public Pawn(Color color, Field position) {
		super(color, position);
	}

//	public Chesspiece transformer(String cp) {
//		if (cp.equals("Queen")) {
//			return new Queen(this.team, this.field, this.board);
//		}
//		if (cp.equals("Rook")) {
//			return new Rook(this.team, this.field, this.board);
//		}
//		if (cp.equals("Knight")) {
//			return new Knight(this.team, this.field, this.board);
//		}
//		if (cp.equals("Bishop")) {
//			return new Bishop(this.team, this.field, this.board);
//		}
//		return this;
//	}

	@Override
	public List<Field> possibleMoves() {
		return (new MoveChecker(this)).checkPawn();
	}

}
