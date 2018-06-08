package de.htwg.chess;

import de.htwg.chess.model.IChessboard;
import de.htwg.chess.model.IField;

public interface ChessDAO {

    /**
     * Saves all the Fields on the Chessboard and the corresponding Chesspieces
     * to the database linked via the hibernate config.
     * */
    public void saveAllFields(IChessboard chessboard);
}
