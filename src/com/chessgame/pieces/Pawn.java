package com.chessgame.pieces;

import com.chessgame.board.Board;

public class Pawn extends Piece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        int direction = color.equals("white") ? 1 : -1;

        // Déplacement d'une case vers l'avant
        if (newX == x && newY == y + direction && board.getPieceAt(newX, newY) == null) {
            return true;
        }

        // Déplacement initial de deux cases vers l'avant
        if (newX == x && ((color.equals("white") && y == 1 && newY == 3) ||
                (color.equals("black") && y == 6 && newY == 4)) &&
                board.getPieceAt(newX, newY) == null && board.getPieceAt(newX, y + direction) == null) {
            return true;
        }

        // Déplacement diagonal pour capturer une pièce
        if (Math.abs(newX - x) == 1 && newY == y + direction) {
            Piece target = board.getPieceAt(newX, newY);
            if (target != null && !target.getColor().equals(this.color)) {
                return true; // Capture d'une pièce adverse
            }
        }

        return false; // Si aucune des conditions n'est remplie, le mouvement est invalide
    }

    @Override
    public String toString() {
        return color.equals("white") ? "P" : "p"; // Blanc = "P", Noir = "p"
    }

    public boolean isEligibleForPromotion() {
        return (color.equals("white") && y == 7) || (color.equals("black") && y == 0);
    }


}
