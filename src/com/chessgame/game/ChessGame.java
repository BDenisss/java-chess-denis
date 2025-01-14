package com.chessgame.game;

import com.chessgame.board.Board;

public class ChessGame {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu d'échecs !");

        Board board = new Board();
        board.initializePieces(); // Place les pièces de départ
        board.printBoard();       // Affiche l'échiquier
    }

}
