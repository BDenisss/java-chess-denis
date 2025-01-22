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
                piece.setPosition(x, y);
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
            placePiece(x, 1, new Pawn("white")); // Pions blancs sur la ligne 2
        }
        System.out.println("Pions blancs placés sur la ligne 2.");

        // Pions noirs
        for (int x = 0; x < 8; x++) {
            placePiece(x, 6, new Pawn("black")); // Pions noirs sur la ligne 7
        }
        System.out.println("Pions noirs placés sur la ligne 7.");

        // Pièces majeures
        String[] majorPieces = {"Rook", "Knight", "Bishop"};
        for (int i = 0; i < 3; i++) {
            // Pièces blanches
            placePiece(i, 0, createPiece(majorPieces[i], "white"));
            placePiece(7 - i, 0, createPiece(majorPieces[i], "white"));

            // Pièces noires
            placePiece(i, 7, createPiece(majorPieces[i], "black"));
            placePiece(7 - i, 7, createPiece(majorPieces[i], "black"));
        }
        System.out.println("Pièces majeures placées.");

        // Reine et roi
        placePiece(3, 0, new Queen("white"));
        placePiece(4, 0, new King("white"));
        placePiece(3, 7, new Queen("black"));
        placePiece(4, 7, new King("black"));

        System.out.println("Reines et rois placés.");
    }


    // Méthode auxiliaire pour créer des pièces par type
    private Piece createPiece(String type, String color) {
        return switch (type) {
            case "Rook" -> new Rook(color);
            case "Knight" -> new Knight(color);
            case "Bishop" -> new Bishop(color);
            default -> throw new IllegalArgumentException("Type de pièce invalide : " + type);
        };
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

    /**
     * Vérifie si le chemin entre deux cases est libre.
     * @param startX Coordonnée X de départ
     * @param startY Coordonnée Y de départ
     * @param endX Coordonnée X d'arrivée
     * @param endY Coordonnée Y d'arrivée
     * @return true si le chemin est libre, false sinon
     */
    public boolean isPathClear(int startX, int startY, int endX, int endY) {
        int deltaX = Integer.compare(endX, startX); // Direction horizontale (-1, 0, 1)
        int deltaY = Integer.compare(endY, startY); // Direction verticale (-1, 0, 1)

        int x = startX + deltaX;
        int y = startY + deltaY;

        while (x != endX || y != endY) {
            if (getPieceAt(x, y) != null) {
                return false; // Une pièce bloque le chemin
            }
            x += deltaX;
            y += deltaY;
        }

        return true; // Chemin libre
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
        int[] kingPosition = findKing(color);
        if (kingPosition == null) {
            throw new IllegalStateException("Roi introuvable pour la couleur : " + color);
        }

        int kingX = kingPosition[0];
        int kingY = kingPosition[1];

        return isSquareUnderThreat(kingX, kingY, color);
    }

    private int[] findKing(String color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPieceAt(x, y);
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return new int[]{x, y};
                }
            }
        }
        return null; // Roi non trouvé
    }

    private boolean isSquareUnderThreat(int x, int y, String color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getPieceAt(i, j);
                if (piece != null && !piece.getColor().equals(color)) {
                    if (piece.isValidMove(x, y, this)) {
                        return true; // La case est menacée
                    }
                }
            }
        }
        return false; // Pas de menace
    }

    public boolean isCheckmate(String color) {
        if (!isKingInCheck(color)) {
            return false; // Pas en échec
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

        return true; // Aucun déplacement ne peut éviter l'échec
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
