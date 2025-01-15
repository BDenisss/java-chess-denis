package com.chessgame.pieces;

import com.chessgame.board.Board;

public class King extends Piece {

    public King (String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1) { // Déplacement d'une case
            Piece target = board.getPieceAt(newX, newY);
            if (target == null || !target.getColor().equals(this.color)) {
                return true; // Case vide ou capture valide
            }
        }
        return false; // Déplacement invalide
    }


    @Override
    public String toString() {
        return color.equals("white") ? "K" : "k";
    }

}
