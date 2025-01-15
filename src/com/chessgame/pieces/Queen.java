package com.chessgame.pieces;

public class Queen extends Piece {

     public Queen(String color) {
         super(color);
     }

    @Override
    public boolean isValidMove(int newX, int newY) {
         // Vérifie si le déplacement est en ligne droite ou en diagonale
        return newX == x || newY == y || Math.abs(newX - x) == Math.abs(newY - y);
    }

    @Override
    public String toString() {
         return color.equals("white") ? "D" : "d";
    }

}
