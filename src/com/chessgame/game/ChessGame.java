package com.chessgame.game;

import com.chessgame.board.Board;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;

import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu d'échecs !");
        Board board = new Board();
        board.initializePieces();
        Scanner scanner = new Scanner(System.in);

        boolean isWhiteTurn = true; // Les blancs commencent

        while (true) {
            board.printBoard();
            System.out.println((isWhiteTurn ? "Blancs" : "Noirs") + ", c'est votre tour !");
            System.out.println("Entrez votre commande (format : 'e2 e4' ou 'exit' pour quitter) :");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Fin du jeu !");
                break;
            }

            // Traiter la commande
            String[] parts = command.split(" ");
            if (parts.length != 2) {
                System.out.println("Commande invalide. Veuillez utiliser le format 'origine destination'.");
                continue;
            }

            // Convertir les coordonnées
            int[] source = convertPosition(parts[0]);
            int[] destination = convertPosition(parts[1]);

            if (source == null || destination == null) {
                System.out.println("Coordonnées invalides. Veuillez réessayer.");
                continue;
            }

            // Vérifier et effectuer le déplacement
            Piece piece = board.getPieceAt(source[0], source[1]);
            if (piece == null) {
                System.out.println("Aucune pièce à cette position. Veuillez réessayer.");
                continue;
            }

            // Vérifier si la pièce appartient au joueur actif
            if ((isWhiteTurn && !piece.getColor().equals("white")) ||
                    (!isWhiteTurn && !piece.getColor().equals("black"))) {
                System.out.println("Ce n'est pas votre pièce. Veuillez réessayer.");
                continue;
            }

            // Vérifier et effectuer le déplacement
            if (piece.isValidMove(destination[0], destination[1], board)) {
                // Simuler le déplacement
                Piece originalPiece = board.getPieceAt(destination[0], destination[1]);
                board.movePiece(source[0], source[1], destination[0], destination[1]);

                // Vérifier si le roi est toujours en échec
                if (board.isKingInCheck(isWhiteTurn ? "white" : "black")) {
                    // Annuler le déplacement
                    board.movePiece(destination[0], destination[1], source[0], source[1]);
                    board.placePiece(destination[0], destination[1], originalPiece); // Restaurer la pièce
                    System.out.println("Déplacement invalide : le roi est toujours en échec !");
                    continue;
                }


                // Si le déplacement est valide, vérifier les promotions
                if (piece instanceof Pawn && ((Pawn) piece).isEligibleForPromotion()) {
                    System.out.println("Votre pion est éligible à une promotion !");
                    System.out.println("Choisissez une pièce (queen, rook, bishop, knight) :");
                    String choice = scanner.nextLine();
                    board.promotePawn(destination[0], destination[1], choice);
                }

                if (board.isKingInCheck(isWhiteTurn ? "black" : "white")) {
                    System.out.println((isWhiteTurn ? "Noirs" : "Blancs") + " sont en échec !");

                    // Vérifier si le joueur peut éviter l'échec
                    if (!board.canPlayerAvoidCheck(isWhiteTurn ? "black" : "white")) {
                        System.out.println((isWhiteTurn ? "Noirs" : "Blancs") + " sont en échec et mat !");
                        System.out.println((isWhiteTurn ? "Blancs" : "Noirs") + " gagnent la partie !");
                        break; // Fin de la partie
                    }
                }


                System.out.println("Déplacement effectué !");
                isWhiteTurn = !isWhiteTurn; // Changer de joueur
            } else {
                System.out.println("Déplacement invalide pour cette pièce. Veuillez réessayer.");
            }
        }

        scanner.close();
    }

    // Convertir une position au format 'e2' en indices [x, y]
    private static int[] convertPosition(String pos) {
        if (pos.length() != 2) return null;

        char col = pos.charAt(0);
        char row = pos.charAt(1);

        int x = col - 'a'; // Convertit 'a'-'h' en 0-7
        int y = row - '1'; // Convertit '1'-'8' en 0-7

        if (x < 0 || x > 7 || y < 0 || y > 7) return null;
        return new int[]{x, y};
    }
}
