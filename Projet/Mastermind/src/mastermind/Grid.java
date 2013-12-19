package mastermind;

import java.util.ArrayList;

/**
 * Cette classe représente la grille du mastermind.
 * Elle n'apporte pour l'instant aucune fonctiinnalités.
 *
 * @author Jordan
 */
public class Grid
{

    int gridWidth = 4; // Nombre de pions à deviner   
    int initGridLine = 20; // Nombre de ligne initial présente dans la grille
    ArrayList<Combination> gameGrid; // Grille du jeux    
    int gameRound = 0; // Position de la dernière combinaison saisie

    public Grid()
      {
        gameGrid = new ArrayList(initGridLine); // Création de la grille
      }

    /**
     * Ajoute une nouvelle combinaison dans la grille
     *
     * @param c combinaison à ajouter
     */
    void putCombinationOnGrid(Combination c)
      {
        gameGrid.add(c);
        gameRound++;
      }
}