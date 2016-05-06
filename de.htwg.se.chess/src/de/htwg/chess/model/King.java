package de.htwg.chess.model;

import java.util.List;

public class King extends Chesspiece {
	private boolean isInCheck = false;

	public King(Player player, Position position, Chessboard chess) {
		super(player, position, chess);
	}

	public boolean getIsInCheck() {
		return isInCheck;
	}

	public void setIsInCheck(boolean check) {
		isInCheck = check;
	}
	
	@Override
	public List<Position> possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
