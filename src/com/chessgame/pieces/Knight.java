package com.chessgame.pieces;

import com.chessgame.board.Board;

public class Knight extends Piece {

    public Knight(String color) {
        super(color);
    }

    @Override
    public boolean isValidMove(int newX, int newY, Board board) {
        // Le déplacement en "L" du cavalier : 2 cases dans une direction et 1 dans une autre
        int deltaX = Math.abs(newX - x);
        int deltaY = Math.abs(newY - y);

        // Vérifie les combinaisons valides de déplacement
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            return true;
        }

        // Si aucune condition n'est remplie, le mouvement est invalide
        return false;
    }

    @Override
    public String toString() {
        return color.equals("white") ? "N" : "n"; // Utilisation de "N" pour le cavalier (notation standard)
    }
}
