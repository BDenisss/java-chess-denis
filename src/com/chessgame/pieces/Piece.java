package com.chessgame.pieces;

public abstract class Piece {
    protected String color; // "white" ou "black"
    protected int x; // Coordonnée X de la pièce sur l'échiquier
    protected int y; // Coordonnée Y de la pièce sur l'échiquier

    public Piece(String color) {
        this.color = color;
    }

    // Définit la position actuelle de la pièce
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Renvoie la couleur de la pièce
    public String getColor() {
        return color;
    }

    // Méthode abstraite pour valider un mouvement
    public abstract boolean isValidMove(int newX, int newY);

    @Override
    public String toString() {
        return color.equals("white") ? "W" : "B"; // Blanc = "W", Noir = "B" (peut être redéfini dans les sous-classes)
    }
}
