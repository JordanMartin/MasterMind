
package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    int maxTry = 10; // Nombre de tentative maximum   
    int nbPegs = 4; // Nombre de pions à deviner   
    ArrayList<Combination> gameGrid; // Grille du jeux    
    Combination combinationToGuess; // Combinaison à déviner
    
    int gameRound = 0; // Position de la dernière combinaison saisie
    
    public Grid() {
       
    }
    
    /**
     * Generation de la combinaison à deviner
     **/
    void generateRandomCombination(){
        combinationToGuess = new Combination(nbPegs);
        combinationToGuess.randomCombination();
        
    }

    
    /**
     * Algo de réolution de la ligne
     */
    void solveCombinationStep(){
        
    }
    
    /**
     * Renvoi vrai si le nombre max d'essai est atteinte
     **/
    boolean checkGameOver(){
         return(gameRound==maxTry);
    }
    
    /**
     * 
     * @return si la dernière combinaison entrée est la bonne
     */
    boolean checkLastCombination(){
        Combination currentCombination = gameGrid.get(gameRound);        
        return combinationToGuess.equalsTo(currentCombination);
    }
    
    
    /**
     * 
     * @return Rajoute une ligne à la grille gameGrid 
     */
    void nextTry()
    {
        gameGrid.add(new Combination(nbPegs));
        gameRound++;
    }
        
}