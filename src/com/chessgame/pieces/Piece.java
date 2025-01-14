package com.chessgame.pieces;

public abstract class Piece {
    protected String color; // Blanc ou Noir

    public Piece(String color) {
        this.color = color;
    }

    public abstract boolean isValidMove(int newX, int newY);

    public String getColor() {
        return color;
    }
}
