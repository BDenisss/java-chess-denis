package com.chessgame.pieces;

public class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Vérifie les mouvements pour un pion
        int direction = color.equals("white") ? 1 : -1; // Les pions blancs avancent vers le haut, les noirs vers le bas

        // Déplacement d'une case vers l'avant
        if (newX == x && newY == y + direction) {
            return true;
        }

        // Déplacement initial de deux cases vers l'avant
        if (newX == x && ((color.equals("white") && y == 1 && newY == 3) ||
                (color.equals("black") && y == 6 && newY == 4))) {
            return true;
        }

        // Déplacement diagonal pour capturer une pièce
        //if (Math.abs(newX - x) == 1 && newY == y + direction) {
            //return true;
        //}

        return false; // Si aucune des conditions n'est remplie, le mouvement est invalide
    }

    @Override
    public String toString() {
        return color.equals("white") ? "P" : "p"; // Blanc = "P", Noir = "p"
    }

}
