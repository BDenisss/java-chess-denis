package com.chessgame.pieces;

public class Rook extends Piece{

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Vérifie les mouvements pour une tour
        int direction = color.equals("white") ? 1 : -1;

        // Déplacement de la tour


        // Si aucune condition n'est rempli, le mouvement est invalide
        return false;
    }

    @Override
    public String toString() {
        return color.equals("white") ? "R" : "r";
    }
}
