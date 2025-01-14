# Jeu d'échecs en Java

Bienvenue dans le projet **Jeu d'échecs en Java**, un projet de développement orienté objet créé pour pratiquer et mettre en œuvre des concepts avancés en Java.

## Objectifs
- Créer un jeu d'échecs fonctionnel en Java.
- Appliquer les principes de la programmation orientée objet (encapsulation, héritage, polymorphisme, abstraction).
- Implémenter les règles d'échecs tout en développant une interface utilisateur basique (console).

## Fonctionnalités actuelles
- Initialisation de l'échiquier avec les pions et les tours.
- Affichage de l'échiquier dans la console.
- Gestion des positions des pièces sur l'échiquier.
- Logique de base pour valider les déplacements des pions.

## Fonctionnalités à venir
- Ajout des autres pièces (cavaliers, fous, reine, roi).
- Implémentation des mouvements spécifiques à chaque pièce.
- Gestion des règles complètes d'échecs (prise en passant, roque, promotion, échec et mat).
- Interface graphique (optionnel).

## Structure du projet

```plaintext
java-chess-denis/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.chessgame/
│   │   │   │   ├── board/
│   │   │   │   │   ├── Board.java       # Classe gérant l'échiquier
│   │   │   │   │   └── Cell.java        # Classe représentant une case
│   │   │   │   ├── pieces/
│   │   │   │   │   ├── Piece.java       # Classe abstraite pour les pièces
│   │   │   │   │   ├── Pawn.java        # Classe pour les pions
│   │   │   │   │   ├── Rook.java        # Classe pour les tours
│   │   │   │   │   └── ...              # Autres pièces
│   │   │   │   ├── game/
│   │   │   │   │   ├── ChessGame.java   # Point d'entrée du jeu
│   │   │   │   ├── ui/
│   │   │   │   │   └── ConsoleUI.java   # Gestion de l'interface console (future)
├── README.md                            # Documentation du projet
```

## Exécution
1. Cloner ou télécharger le projet.
2. Ouvrir le projet dans un IDE comme IntelliJ IDEA ou Eclipse.
3. Exécuter la classe principale `ChessGame`.

L'échiquier s'affichera dans la console avec les pions et les tours placés correctement.

## Contributeurs
- **Denis** (développeur principal)
- Contributions ouvertes pour des suggestions ou améliorations !
---

Merci d'explorer et d'améliorer ce projet à votre guise !


