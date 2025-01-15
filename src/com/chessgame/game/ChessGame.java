package com.chessgame.game;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;

import java.util.Scanner;

public class ChessGame {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu d'échecs !");
        Board board = new Board();
        board.initializePieces();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.printBoard();
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

            if (piece.isValidMove(destination[0], destination[1], board)) {
                board.movePiece(source[0], source[1], destination[0], destination[1]);
                System.out.println("Déplacement effectué !");
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
