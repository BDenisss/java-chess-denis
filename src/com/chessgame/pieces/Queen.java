package com.chessgame.pieces;

import com.chessgame.board.Board;

public class Queen extends Piece {

     public Queen(String color) {
         super(color);
     }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Vérifie le type de déplacement (ligne, colonne, diagonale)
        if ((newX == x || newY == y || Math.abs(newX - x) == Math.abs(newY - y)) &&
                board.isPathClear(x, y, newX, newY)) {

            // Vérifie la case cible
            Piece target = board.getPieceAt(newX, newY);
            if (target == null) {
                return true; // Case cible vide
            } else if (!target.getColor().equals(this.color)) {
                return true; // Capture d'une pièce adverse
            }
        }
        return false; // Déplacement invalide
    }


    @Override
    public String toString() {
         return color.equals("white") ? "D" : "d";
    }

}
