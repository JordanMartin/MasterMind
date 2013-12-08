
package mastermind;

import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    
    int gridWidth = 4; // Nombre de pions à deviner   
    int initGridLine = 20; // Nombre de ligne initial présente dans la grille
    ArrayList<Combination> gameGrid; // Grille du jeux    
        
    int gameRound = 0; // Position de la dernière combinaison saisie
    

    public Grid() {
       gameGrid = new ArrayList(initGridLine); // Création de la grille
    }    
    
    /**
     * 
     * @param c combinaison à ajouter
     */
    void putCombinationOnGrid(Combination c){
        gameGrid.add(c);
        gameRound++;
        
        System.out.println("Essai : " + gameGrid.get(gameRound-1));
    }
}