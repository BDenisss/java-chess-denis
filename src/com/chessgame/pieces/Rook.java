package com.chessgame.pieces;


import com.chessgame.board.Board;

public class Rook extends Piece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        if ((newX == x || newY == y) && board.isPathClear(x, y, newX, newY)) {
            Piece target = board.getPieceAt(newX, newY);
            if (target == null || !target.getColor().equals(this.color)) {
                return true; // Case vide ou capture valide
            }
        }
        return false; // Déplacement invalide
    }




    @Override
    public String toString() {
        return color.equals("white") ? "R" : "r"; // Blanc = "R", Noir = "r"
    }
}
