package com.chessgame.pieces;

import com.chessgame.board.Board;

public abstract class Piece {
    protected String color;
    protected int x, y;

    public Piece(String color) {
        this.color = color;
    }

    public abstract boolean isValidMove(int newX, int newY, Board board);

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }
}
