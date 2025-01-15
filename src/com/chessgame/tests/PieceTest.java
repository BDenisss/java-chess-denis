package com.chessgame.tests;

import com.chessgame.pieces.*;

public class PieceTest {

    public static void main(String[] args) {
        testPawn();
        testRook();
        testKnight();
        testBishop();
        testQueen();
        testKing();
    }

    public static void testPawn() {
        System.out.println("Testing Pawn...");
        Pawn whitePawn = new Pawn("white");
        whitePawn.setPosition(4, 4);
        System.out.println(whitePawn.isValidMove(4, 5)); // True: avance d'une case
        System.out.println(whitePawn.isValidMove(4, 6)); // False: trop loin
        System.out.println(whitePawn.isValidMove(5, 5)); // False: pas en ligne
        System.out.println(whitePawn.isValidMove(4, 3)); // False: ne peut pas reculer
    }

    public static void testRook() {
        System.out.println("Testing Rook...");
        Rook rook = new Rook("white");
        rook.setPosition(4, 4);
        System.out.println(rook.isValidMove(4, 7)); // True: en ligne droite
        System.out.println(rook.isValidMove(7, 4)); // True: en ligne droite
        System.out.println(rook.isValidMove(5, 5)); // False: pas en ligne droite
    }

    public static void testKnight() {
        System.out.println("Testing Knight...");
        Knight knight = new Knight("white");
        knight.setPosition(4, 4);
        System.out.println(knight.isValidMove(6, 5)); // True: en "L"
        System.out.println(knight.isValidMove(5, 6)); // True: en "L"
        System.out.println(knight.isValidMove(4, 6)); // False: pas en "L"
    }

    public static void testBishop() {
        System.out.println("Testing Bishop...");
        Bishop bishop = new Bishop("white");
        bishop.setPosition(4, 4);
        System.out.println(bishop.isValidMove(6, 6)); // True: en diagonale
        System.out.println(bishop.isValidMove(2, 2)); // True: en diagonale
        System.out.println(bishop.isValidMove(5, 8)); // False: en diagonale
    }

    public static void testQueen() {
        System.out.println("Testing Queen...");
        Queen queen = new Queen("white");
        queen.setPosition(4, 4);
        System.out.println(queen.isValidMove(4, 7)); // True: en ligne droite
        System.out.println(queen.isValidMove(7, 7)); // True: en diagonale
        System.out.println(queen.isValidMove(5, 7)); // False: mouv invalide
    }

    public static void testKing() {
        System.out.println("Testing King...");
        King king = new King("white");
        king.setPosition(4, 4);
        System.out.println(king.isValidMove(5, 5)); // True: une case en diagonale
        System.out.println(king.isValidMove(4, 5)); // True: une case verticale
        System.out.println(king.isValidMove(6, 6)); // False: trop loin
    }



}
