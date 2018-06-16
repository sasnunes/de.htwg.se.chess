package de.htwg.chess;

import de.htwg.chess.model.IChessboard;
import de.htwg.chess.model.IField;

import java.util.List;

public interface ChessDAO {

    /**
     * Saves all the Fields on the Chessboard and the corresponding Chesspieces
     * to the database linked via the "hibernate.cfg.xml".
     * */
    void saveBoard(IChessboard chessboard);

    /**
     * Loads all the Fields and the corresponding Chesspieces from the Database
     * linked via the "hibernate.cfg.xml".
     * */
    List<IField> loadBoard();

    /**
     * Tells the DAO to close all sessions for a smooth shutdown of the database-connection.
     */
    void shutdown();
}
