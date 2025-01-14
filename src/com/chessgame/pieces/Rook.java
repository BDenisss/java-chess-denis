package com.chessgame.pieces;

public class Rook extends Piece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Une tour peut se déplacer en ligne droite horizontalement ou verticalement
        // Vérifie si le déplacement est en ligne droite
        if (newX == x || newY == y) {
            return true;
        }

        // Si aucune condition n'est remplie, le mouvement est invalide
        return false;
    }

    @Override
    public String toString() {
        return color.equals("white") ? "R" : "r"; // Blanc = "R", Noir = "r"
    }
}
