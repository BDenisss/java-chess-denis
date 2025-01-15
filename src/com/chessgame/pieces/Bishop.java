package com.chessgame.pieces;

public class Bishop extends Piece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Vérifie si le déplacement est en diagonale
        return Math.abs(newX - x) == Math.abs(newY - y);
    }

    @Override
    public String toString() {
        return color.equals("white") ? "F" : "f"; // Blanc = "F", Noir = "f"
    }
}
