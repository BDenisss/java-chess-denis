import com.chessgame.board.Board;
import com.chessgame.pieces.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessTest {

    // Méthode pour imprimer l'état de l'échiquier
    private void printBoardState(Board board) {
        System.out.println("État actuel de l'échiquier :");
        for (int y = 7; y >= 0; y--) { // Affichage inversé pour correspondre à l'échiquier
            for (int x = 0; x < 8; x++) {
                Piece piece = board.getPieceAt(x, y);
                System.out.print((piece != null ? piece.toString() : ".") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void testKingInCheck() {
        Board board = new Board();
        board.initializePieces();

        System.out.println("Avant modifications :");
        printBoardState(board);

        // Supprimer toutes les pièces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board.placePiece(x, y, null);
            }
        }

        // Placer le roi et la reine
        board.placePiece(4, 7, new King("black"));
        board.placePiece(7, 4, new Queen("white"));

        System.out.println("Après placement des pièces :");
        printBoardState(board);

        assertTrue("Le roi noir doit être en échec.", board.isKingInCheck("black"));
    }

    @Test
    public void testKingNotInCheck() {
        Board board = new Board();
        board.initializePieces();

        System.out.println("État initial :");
        printBoardState(board);

        assertFalse("Le roi blanc ne doit pas être en échec.", board.isKingInCheck("white"));
        assertFalse("Le roi noir ne doit pas être en échec.", board.isKingInCheck("black"));
    }

    @Test
    public void testInitialBoardSetup() {
        Board board = new Board();
        board.initializePieces();

        System.out.println("État initial de l'échiquier :");
        printBoardState(board);

        for (int x = 0; x < 8; x++) {
            assertTrue(board.getPieceAt(x, 1) instanceof Pawn);
            assertEquals("white", board.getPieceAt(x, 1).getColor());
            assertTrue(board.getPieceAt(x, 6) instanceof Pawn);
            assertEquals("black", board.getPieceAt(x, 6).getColor());
        }
    }

    @Test
    public void testValidMove() {
        Board board = new Board();
        board.initializePieces();

        System.out.println("Avant le déplacement :");
        printBoardState(board);

        board.movePiece(4, 1, 4, 3);

        System.out.println("Après le déplacement :");
        printBoardState(board);

        assertTrue(board.getPieceAt(4, 3) instanceof Pawn);
        assertNull(board.getPieceAt(4, 1));
    }

    @Test
    public void testInvalidMove() {
        Board board = new Board();
        board.initializePieces();

        System.out.println("État initial :");
        printBoardState(board);

        board.movePiece(4, 3, 4, 4);

        System.out.println("Après tentative de déplacement invalide :");
        printBoardState(board);

        assertNull(board.getPieceAt(4, 4));
    }

    @Test
    public void testPathClear() {
        Board board = new Board();

        // Placer une tour blanche et un pion blanc pour bloquer le chemin
        board.placePiece(0, 0, new Rook("white")); // Tour blanche en a1
        board.placePiece(0, 1, new Pawn("white")); // Pion blanc en a2

        System.out.println("Avant modification du chemin :");
        printBoardState(board);

        // Vérifie que le chemin est bloqué
        assertFalse("Le chemin ne doit pas être dégagé.", board.isPathClear(0, 0, 0, 2)); // a1 vers a3

        // Supprimer le pion pour dégager le chemin
        board.placePiece(0, 1, null);

        System.out.println("Après modification du chemin :");
        printBoardState(board);

        // Vérifie que le chemin est dégagé
        assertTrue("Le chemin doit être dégagé.", board.isPathClear(0, 0, 0, 2)); // a1 vers a3
    }



    @Test
    public void testCheckmate() {
        Board board = new Board();

        // Mat du berger
        board.placePiece(3, 7, new Queen("black"));  // Dame noire en d8
        board.placePiece(4, 7, new King("black"));  // Roi noir en e8
        board.placePiece(2, 3, new Bishop("white")); // Fou blanc en c4
        board.placePiece(5, 6, new Queen("white")); // Reine blanche en f7

        System.out.println("Échiquier avant vérification de l'échec et mat :");
        printBoardState(board);

        assertTrue("Le roi noir doit être en échec et mat.", board.isCheckmate("black"));
    }


    @Test
    public void testPawnPromotion() {
        Board board = new Board();

        board.placePiece(0, 7, new Pawn("white"));
        ((Pawn) board.getPieceAt(0, 7)).isEligibleForPromotion();

        System.out.println("Avant la promotion :");
        printBoardState(board);

        board.promotePawn(0, 7, "queen");

        System.out.println("Après la promotion :");
        printBoardState(board);

        assertTrue(board.getPieceAt(0, 7) instanceof Queen);
    }
}
