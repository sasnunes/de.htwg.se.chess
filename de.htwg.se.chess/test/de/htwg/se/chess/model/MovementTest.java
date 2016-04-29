package de.htwg.se.chess.model;

import de.htwg.chess.Chess;
import de.htwg.chess.model.*;
import static de.htwg.chess.model.FieldConstants.*;
import java.util.LinkedList;
import org.junit.*;
import junit.framework.TestCase;

public final class MovementTest extends TestCase {

	Chess chess;
	Chesspiece piece_test;
	Chesspiece piece_friendly;
	Chesspiece piece_enemy;

	
//	@Test
//	public void testMove() {
//		Chesspiece piece_test = new Rook(chess.getWhite(), chess.getChessboard()[D][FIVE], chess);
//		assertTrue(piece_test.getPosition().toString().equals("D5"));
//		piece_test.possibleMoves();
//
//		// right
//		Chesspiece rook_1 = new Rook(chess.getWhite(), chess.getChessboard()[F][FIVE], chess);
//		piece_test.possibleMoves();
//		Chesspiece rook_enemy_1 = new Rook(chess.getBlack(), chess.getChessboard()[F][FIVE], chess);
//		piece_test.possibleMoves();
//		// left
//		rook_1.setPosition(chess.getChessboard()[A][FIVE]);
//		piece_test.possibleMoves();
//		rook_enemy_1.setPosition(chess.getChessboard()[A][FIVE]);
//		piece_test.possibleMoves();
//
//		// up
//		piece_test.setPosition(chess.getChessboard()[D][EIGHT]);
//		piece_test.possibleMoves();
//		piece_test.setPosition(chess.getChessboard()[D][FIVE]);
//		rook_1.setPosition(chess.getChessboard()[D][SIX]);
//		piece_test.possibleMoves();
//		// down
//		piece_test.setPosition(chess.getChessboard()[D][ONE]);
//		piece_test.possibleMoves();
//
//		Chesspiece bishop_test = new Bishop(chess.getWhite(), chess.getChessboard()[D][FIVE], chess);
//		bishop_test.possibleMoves();
//		// rook_1.setPosition(chess.getChessboard()[C][SIX]);
//		// rook_enemy_1.setPosition(chess.getChessboard()[E][SIX]);
//
//		LinkedList<Position> poslist = new LinkedList<>();
//		poslist.add(chess.getChessboard()[A][EIGHT]);
//		poslist.add(chess.getChessboard()[H][EIGHT]);
//		poslist.add(chess.getChessboard()[A][ONE]);
//		poslist.add(chess.getChessboard()[H][ONE]);
//
//		for (Position pos : poslist) {
//			bishop_test.setPosition(pos);
//			bishop_test.possibleMoves();
//		}
//
//		for (Position pos : poslist) {
//			bishop_test.setPosition(pos);
//			bishop_test.possibleMoves();
//		}
//
//		Chesspiece queen = new Queen(chess.getWhite(), chess.getChessboard()[A][SIX], chess);
//		queen.possibleMoves();
//
//		Chesspiece pferdchen = new Knight(chess.getWhite(), chess.getChessboard()[B][FIVE], chess);
//		pferdchen.possibleMoves();
//
//	}
}
