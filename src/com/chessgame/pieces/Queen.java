package com.chessgame.pieces;

import com.chessgame.board.Board;

public class Queen extends Piece {

     public Queen(String color) {
         super(color);
     }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        System.out.println("Reine en (" + x + ", " + y + ") teste un déplacement vers (" + newX + ", " + newY + ")");
        boolean isDiagonalOrStraight = (newX == x || newY == y || Math.abs(newX - x) == Math.abs(newY - y));
        System.out.println("Déplacement diagonal ou droit : " + isDiagonalOrStraight);

        if (isDiagonalOrStraight && board.isPathClear(x, y, newX, newY)) {
            System.out.println("Chemin dégagé entre (" + x + ", " + y + ") et (" + newX + ", " + newY + ")");
            Piece target = board.getPieceAt(newX, newY);
            boolean isCaptureOrEmpty = (target == null || !target.getColor().equals(this.color));
            System.out.println("Case cible : " + (target != null ? target.toString() : "vide") + " - Déplacement valide : " + isCaptureOrEmpty);
            return isCaptureOrEmpty;
        }

        return false; // Déplacement invalide
    }




    @Override
    public String toString() {
         return color.equals("white") ? "D" : "d";
    }

}
