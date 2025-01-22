package com.chessgame.board;

import com.chessgame.pieces.*;

public class Board {
    private Cell[][] grid; // Grille de 8x8 cases

    // Constructeur
    public Board() {
        this.grid = new Cell[8][8]; // Initialise la grille de 8x8
        initializeBoard(); // Initialise chaque case
    }

    public void placePiece(int x, int y, Piece piece) {
        if (isValidPosition(x, y)) {
            grid[x][y].setPiece(piece);
            if (piece != null) {
                piece.setPosition(x, y); // Mise à jour des coordonnées internes
            }
        } else {
            throw new IllegalArgumentException("Position invalide : (" + x + ", " + y + ")");
        }
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

    public Piece getPieceAt(int x, int y) {
        if(isValidPosition(x,y)) {
            return grid[x][y].getPiece();
        }
        return null;
    }

    public void movePiece(int srcX, int srcY, int destX, int destY) {
        if (isValidPosition(srcX, srcY) && isValidPosition(destX, destY)) {
            Piece piece = grid[srcX][srcY].getPiece();
            grid[destX][destY].setPiece(piece); // Place la pièce à la nouvelle position
            grid[srcX][srcY].setPiece(null);    // Vide la position d'origine
            if (piece != null) {
                piece.setPosition(destX, destY); // Met à jour la position de la pièce
            }
        }
    }

    public boolean isPathClear(int startX, int startY, int endX, int endY) {
        // Déplacement vertical
        if (startX == endX) {
            int step = (endY > startY) ? 1 : -1;
            for (int y = startY + step; y != endY; y += step) {
                if (getPieceAt(startX, y) != null) {
                    return false; // Une pièce bloque le chemin
                }
            }
        }
        // Déplacement horizontal
        else if (startY == endY) {
            int step = (endX > startX) ? 1 : -1;
            for (int x = startX + step; x != endX; x += step) {
                if (getPieceAt(x, startY) != null) {
                    return false; // Une pièce bloque le chemin
                }
            }
        }
        // Déplacement diagonal
        else if (Math.abs(endX - startX) == Math.abs(endY - startY)) {
            int stepX = (endX > startX) ? 1 : -1;
            int stepY = (endY > startY) ? 1 : -1;
            int x = startX + stepX;
            int y = startY + stepY;
            while (x != endX && y != endY) {
                if (getPieceAt(x, y) != null) {
                    return false; // Une pièce bloque le chemin
                }
                x += stepX;
                y += stepY;
            }
        }
        return true; // Le chemin est libre
    }



    public void promotePawn(int x, int y, String choice) {
        Piece pawn = getPieceAt(x, y);
        if (pawn instanceof Pawn && ((Pawn) pawn).isEligibleForPromotion()) {
            Piece promotedPiece = null;
            switch (choice.toLowerCase()) {
                case "queen":
                    promotedPiece = new Queen(pawn.getColor());
                    break;
                case "rook":
                    promotedPiece = new Rook(pawn.getColor());
                    break;
                case "bishop":
                    promotedPiece = new Bishop(pawn.getColor());
                    break;
                case "knight":
                    promotedPiece = new Knight(pawn.getColor());
                    break;
                default:
                    System.out.println("Choix invalide, promotion en Reine par défaut.");
                    promotedPiece = new Queen(pawn.getColor());
            }
            promotedPiece.setPosition(x, y); // Conserve la position du pion
            grid[x][y].setPiece(promotedPiece); // Remplace le pion par la pièce promue
        }
    }

    public boolean isKingInCheck(String color) {
        int kingX = -1, kingY = -1;

        // Trouver la position du roi
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPieceAt(x, y);
                if (piece instanceof King && piece.getColor().equals(color)) {
                    kingX = x;
                    kingY = y;
                    System.out.println("Roi trouvé : (" + kingX + ", " + kingY + ")");
                    break;
                }
            }
        }

        if (kingX == -1 || kingY == -1) {
            throw new IllegalStateException("Roi introuvable pour la couleur : " + color);
        }

        // Vérifier si une pièce adverse peut atteindre le roi
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPieceAt(x, y);
                if (piece != null && !piece.getColor().equals(color)) {
                    System.out.println("Test menace : " + piece + " en (" + x + ", " + y + ")");
                    if (piece.isValidMove(kingX, kingY, this)) {
                        System.out.println("Roi menacé par : " + piece + " en (" + x + ", " + y + ")");
                        return true; // Le roi est en échec
                    }
                }
            }
        }

        return false; // Le roi n'est pas en échec
    }





    public boolean isCheckmate(String color) {
        if (!isKingInCheck(color)) {
            return false; // Pas en échec, donc pas en échec et mat
        }

        // Parcourir toutes les pièces du joueur
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPieceAt(x, y);
                if (piece != null && piece.getColor().equals(color)) {
                    // Tester tous les déplacements possibles de cette pièce
                    for (int newX = 0; newX < 8; newX++) {
                        for (int newY = 0; newY < 8; newY++) {
                            if (piece.isValidMove(newX, newY, this)) {
                                // Simuler le déplacement
                                Piece originalPiece = getPieceAt(newX, newY);
                                movePiece(x, y, newX, newY);
                                boolean stillInCheck = isKingInCheck(color);
                                // Annuler le déplacement
                                movePiece(newX, newY, x, y);
                                grid[newX][newY].setPiece(originalPiece);

                                if (!stillInCheck) {
                                    return false; // Une action peut éviter l'échec
                                }
                            }
                        }
                    }
                }
            }
        }

        return true; // Aucun déplacement possible pour sortir de l'échec
    }

    public boolean canPlayerAvoidCheck(String color) {
        // Parcourir toutes les pièces du joueur
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPieceAt(x, y);
                if (piece != null && piece.getColor().equals(color)) {
                    // Tester tous les déplacements possibles de cette pièce
                    for (int newX = 0; newX < 8; newX++) {
                        for (int newY = 0; newY < 8; newY++) {
                            if (piece.isValidMove(newX, newY, this)) {
                                // Simuler le déplacement
                                Piece originalPiece = getPieceAt(newX, newY);
                                movePiece(x, y, newX, newY);
                                boolean stillInCheck = isKingInCheck(color);
                                // Annuler le déplacement
                                movePiece(newX, newY, x, y);
                                grid[newX][newY].setPiece(originalPiece);

                                if (!stillInCheck) {
                                    return true; // Un mouvement peut éviter l'échec
                                }
                            }
                        }
                    }
                }
            }
        }
        return false; // Aucun déplacement ne peut éviter l'échec
    }





}
