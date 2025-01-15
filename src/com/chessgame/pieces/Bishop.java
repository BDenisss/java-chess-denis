package com.chessgame.pieces;

import com.chessgame.board.Board;

public class Bishop extends Piece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if (Math.abs(newX - x) == Math.abs(newY - y) && board.isPathClear(x, y, newX, newY)) {
            Piece target = board.getPieceAt(newX, newY);
            if (target == null || !target.getColor().equals(this.color)) {
                return true; // Case vide ou capture valide
            }
        }
        return false; // Déplacement invalide
    }


    @Override
    public String toString() {
        return color.equals("white") ? "F" : "f"; // Blanc = "F", Noir = "f"
    }
}
