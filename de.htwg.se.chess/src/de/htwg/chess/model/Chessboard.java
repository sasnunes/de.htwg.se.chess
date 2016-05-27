package de.htwg.chess.model;

import static de.htwg.chess.model.Team.Color.*;

/**
 * 
 * @author Sascha Nunes, Kevin Deggelmann (team 10)
 *
 */

public class Chessboard {
	private Field[][] board;
	private Team[] teamlist;
	private MoveChecker moveChecker;

	public Chessboard() {
		moveChecker = new MoveChecker(this);
		initChessboard();
		initTeamlist();
	}

	private void initChessboard() {
		board = new Field[8][8];
		for (char x = 'A'; x <= 'H'; x++) {
			for (int y = 1; y <= 8; y++) {
				board[x - 'A'][y - 1] = new Field(x, y);
			}
		}
	}

	private void initTeamlist() {
		teamlist = new Team[2];
		teamlist[WHITE.ordinal()] = new Team(WHITE, this, moveChecker);
		teamlist[BLACK.ordinal()] = new Team(BLACK, this, moveChecker);
	}

	public Field getField(int x, int y) {
		return board[x][y];
	}

	public Team getTeam(Team.Color color) {
		return teamlist[color.ordinal()];
	}
	
	public void updateTeams() {
		for (Team team : teamlist)
			team.updatePosMoves();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				sb.append(board[i][j] + ": " + board[i][j].getChesspiece() + "\n");
		return sb.toString();
	}

}
