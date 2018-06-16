package de.htwg.chess;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PersistentField implements Serializable{
    @Id
    @Column(name = "x")
    private char x;
    @Id
    @Column(name = "y")
    private int y;
    @Column(name = "piece")
    private String cp;

    public PersistentField(char x, int y, String chesspieceName) {
        this.x = x;
        this.y = y;
        this.cp = chesspieceName;
    }

    public PersistentField() {
    }

    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCp() {
        return cp;
    }
}
