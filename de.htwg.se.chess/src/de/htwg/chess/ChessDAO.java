package de.htwg.chess;

import de.htwg.chess.model.IField;

public interface ChessDAO {

    public void saveField(IField field);

    public void saveAllFields();
}
