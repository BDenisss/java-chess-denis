package com.chessgame.pieces;

public class King extends Piece {

    public King (String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Le roi peut se déplacer d'une case dans toutes les directions
        return Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1;
    }


    @Override
    public String toString() {
        return color.equals("white") ? "K" : "k";
    }

}
