import com.chessgame.board.Board;
import com.chessgame.pieces.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckTest {

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

        // Afficher l'état initial de l'échiquier
        System.out.println("Avant modifications :");
        printBoardState(board);

        // Supprimer toutes les pièces du plateau pour un test isolé
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board.placePiece(x, y, null); // Supprime les pièces
            }
        }

        // Placer manuellement le roi noir et la reine blanche
        board.placePiece(4, 7, new King("black"));  // Roi noir en e8
        board.placePiece(7, 4, new Queen("white")); // Reine blanche en h5

        // Afficher l'état après placement des pièces
        System.out.println("Après placement des pièces :");
        printBoardState(board);

        // Vérifier l'état d'échec
        boolean isCheck = board.isKingInCheck("black");
        System.out.println("Le roi noir est en échec : " + isCheck);
        assertTrue("Le roi noir doit être en échec.", isCheck);
    }

    @Test
    public void testKingNotInCheck() {
        Board board = new Board();
        board.initializePieces();

        // Afficher l'état initial de l'échiquier
        System.out.println("État initial :");
        printBoardState(board);

        // Aucun roi ne devrait être en échec à l'initialisation
        assertFalse("Le roi blanc ne doit pas être en échec.", board.isKingInCheck("white"));
        assertFalse("Le roi noir ne doit pas être en échec.", board.isKingInCheck("black"));
    }
}
