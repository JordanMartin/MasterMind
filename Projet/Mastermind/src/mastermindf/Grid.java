package mastermindf;

import java.util.ArrayList;

/**
 * Cette classe repr�sente la grille du mastermind.
 * Elle n'apporte pour l'instant aucune fonctiinnalit�s.
 *
 * @author Jordan
 */
public class Grid
{

    int gridWidth = 4; // Nombre de pions � deviner   
    int initGridLine = 20; // Nombre de ligne initial pr�sente dans la grille
    ArrayList<Combination> gameGrid; // Grille du jeux    
    int gameRound = 0; // Position de la derni�re combinaison saisie

    public Grid()
      {
        gameGrid = new ArrayList(initGridLine); // Cr�ation de la grille
      }

    /**
     * Ajoute une nouvelle combinaison dans la grille
     *
     * @param c combinaison � ajouter
     */
    void putCombinationOnGrid(Combination c)
      {
        gameGrid.add(c);
        gameRound++;
      }
}