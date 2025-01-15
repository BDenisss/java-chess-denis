package com.chessgame.pieces;

public class Bishop extends Piece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Vérifie si le déplacement est en diagonale
        if (Math.abs(newX - x) == Math.abs(newY - y)) {
            // Vérifie que la position finale est différente de la position actuelle
            if (newX != x || newY != y) {
                // Vérifie que le déplacement est dans les limites de l'échiquier
                return newX >= 0 && newX < 8 && newY >= 0 && newY < 8;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return color.equals("white") ? "F" : "f"; // Blanc = "F", Noir = "f"
    }
}
