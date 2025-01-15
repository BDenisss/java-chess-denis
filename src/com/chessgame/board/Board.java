package com.chessgame.board;

import com.chessgame.pieces.*;

public class Board {
    private Cell[][] grid; // Grille de 8x8 cases

    // Constructeur
    public Board() {
        this.grid = new Cell[8][8]; // Initialise la grille de 8x8
        initializeBoard(); // Initialise chaque case
    }

    // Initialise toutes les cases de l'échiquier
    private void initializeBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                grid[x][y] = new Cell(x, y); // Chaque case est une nouvelle Cell
            }
        }
    }

    // Place les pièces de départ sur l'échiquier
    public void initializePieces() {
        // Pions blancs
        for (int x = 0; x < 8; x++) {
            placePiece(new Pawn("white"), x, 1); // Ligne 2 pour les pions blancs
        }

        // Pions noirs
        for (int x = 0; x < 8; x++) {
            placePiece(new Pawn("black"), x, 6); // Ligne 7 pour les pions noirs
        }

        // Tours blanches
        placePiece(new Rook("white"), 0, 0); // Tour en a1
        placePiece(new Rook("white"), 7, 0); // Tour en h1

        // Tours noires
        placePiece(new Rook("black"), 0, 7); // Tour en a8
        placePiece(new Rook("black"), 7, 7); // Tour en h8

        // Cavaliers blancs
        placePiece(new Knight("white"), 1, 0);
        placePiece(new Knight("white"), 6, 0);

        // Cavaliers blancs
        placePiece(new Knight("white"), 1, 0);
        placePiece(new Knight("white"), 6, 0);

        // Cavaliers noirs
        placePiece(new Knight("black"), 1, 7);
        placePiece(new Knight("black"), 6, 7);

        // Fous blancs
        placePiece(new Bishop("white"), 2, 0);
        placePiece(new Bishop("white"), 5, 0);

        // Fous noirs
        placePiece(new Bishop("black"), 2, 7);
        placePiece(new Bishop("black"), 5, 7);

        // Dame blanche et noir
        placePiece(new Queen("white"),3, 0);
        placePiece(new Queen("black"),3, 7);

        // Roi blanc et noir
        placePiece(new King("white"),4,0);
        placePiece(new King("black"),4,7);


        // Autres pièces à ajouter plus tard : tours, cavaliers, fous, roi, reine...
    }

    // Place une pièce sur l'échiquier à une position donnée
    public void placePiece(Piece piece, int x, int y) {
        if (isValidPosition(x, y)) {
            grid[x][y].setPiece(piece);
            piece.setPosition(x, y); // Met à jour la position de la pièce
        } else {
            System.out.println("Position invalide : (" + x + ", " + y + ")");
        }
    }

    // Vérifie si une position est valide sur l'échiquier
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    // Affiche l'échiquier dans la console
    public void printBoard() {
        for (int y = 7; y >= 0; y--) { // Parcourt les lignes de haut en bas
            for (int x = 0; x < 8; x++) { // Parcourt les colonnes de gauche à droite
                Piece piece = grid[x][y].getPiece(); // Récupère la pièce sur la case
                // Utilise la méthode toString() pour afficher la pièce ou '.' si la case est vide
                System.out.print((piece != null ? piece.toString() : '.') + " ");
            }
            System.out.println(); // Passe à la ligne suivante
        }
    }

}
