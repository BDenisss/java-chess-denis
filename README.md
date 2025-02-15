# Jeu d'échecs en Java

Bienvenue dans le projet **Jeu d'échecs en Java**, un projet de développement orienté objet conçu pour pratiquer et mettre en œuvre des concepts avancés en Java, tout en recréant les règles et fonctionnalités d'un jeu d'échecs.

---

## **Objectifs**
- Développer un jeu d'échecs fonctionnel en Java.
- Appliquer les principes de la programmation orientée objet (encapsulation, héritage, polymorphisme, abstraction).
- Implémenter les règles complètes du jeu d'échecs.
- Permettre une gestion basique du jeu via une interface utilisateur console.

---

## **Fonctionnalités actuelles**
### **1. Initialisation de l'échiquier :**
- L'échiquier est correctement initialisé avec toutes les pièces (pions, tours, cavaliers, fous, reine, roi).
- Les pièces sont disposées conformément aux règles officielles d'échecs.

### **2. Déplacement des pièces :**
- Gestion des mouvements spécifiques à chaque pièce (pion, tour, cavalier, fou, reine, roi).
- Validation des déplacements avec prise en compte des règles :
  - Respect des mouvements autorisés pour chaque type de pièce.
  - Détection et gestion des obstacles sur le chemin.
  - Gestion des captures de pièces adverses.

### **3. Gestion des règles avancées :**
- Détection de l’échec : Vérification si un roi est menacé par une pièce adverse.
- Détection de l’échec et mat : Le jeu se termine si un joueur est en échec et ne peut effectuer aucun mouvement légal.
- Promotion des pions : Lorsqu'un pion atteint la dernière rangée, il peut être promu en reine, tour, fou ou cavalier.
- Simulations pour valider les mouvements (annulation des déplacements invalides).

### **4. Interface utilisateur console :**
- Affichage visuel simplifié de l’échiquier dans la console.
- Saisie des commandes de déplacement au format : `origine destination` (ex. : `e2 e4`).

---

## **Fonctionnalités à venir**
- Implémentation des règles supplémentaires :
  - **Roque** (petit et grand).
  - **Prise en passant** pour les pions.
- Détection du **pat** (match nul).
- Interface graphique avec JavaFX pour une expérience utilisateur améliorée.

---

## **Structure du projet**
```plaintext
java-chess-denis/
├── src/
│   ├── com.chessgame/
│   │   ├── board/
│   │   │   ├── Board.java       # Classe gérant l'échiquier
│   │   │   ├── Cell.java        # Classe représentant une case
│   │   ├── pieces/
│   │   │   ├── Piece.java       # Classe abstraite pour les pièces
│   │   │   ├── Pawn.java        # Classe pour les pions
│   │   │   ├── Rook.java        # Classe pour les tours
│   │   │   ├── Bishop.java      # Classe pour les fous
│   │   │   ├── Knight.java      # Classe pour les cavaliers
│   │   │   ├── Queen.java       # Classe pour la reine
│   │   │   ├── King.java        # Classe pour le roi
│   │   ├── game/
│   │   │   ├── ChessGame.java   # Point d'entrée du jeu
│   │   ├── ui/                  # Gestion future de l'interface graphique
│   │   │   └── ConsoleUI.java   # (Future) Gestion de l'interface console
├── README.md                    # Documentation du projet
