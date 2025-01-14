package com.chessgame.board;

import com.chessgame.pieces.Piece;

public class Cell {
    private int x; // Coordonnée X
    private int y; // Coordonnée Y
    private Piece piece; // La pièce sur cette case (null si vide)

    // Constructeur
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null; // Par défaut, la case est vide
    }

    // Getter pour la pièce
    public Piece getPiece() {
        return piece;
    }

    // Setter pour la pièce
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Getters pour les coordonnées (facultatif)
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
