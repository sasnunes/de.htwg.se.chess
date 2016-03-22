package de.htwg.chess.model;

public final class Position {
	private char x;
	private int y;
	
	public Position(char x, int y) {
		setPosition(x, y);
	}
	
	public boolean setPosition(char x, int y) {
		if (x < 'A' || x > 'H' || y < 1 || y > 8) {
			return false;
		}
		this.x = x;
		this.y = y;
		return true;
	}
	
	public Position getPosition() {
		return this;
	}

	public char getX() {
		return x;
	}

	public boolean setX(char x) {
		return setPosition(x, this.y);
	}

	public int getY() {
		return y;
	}

	public boolean setY(int y) {
		return setPosition(this.x, y);
	}
	
	@Override 
	public String toString() {
		return String.valueOf(x) + y;
	}
}