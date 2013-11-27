
package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    int maxTry = 10; // Nombre de tentative maximum   
    int nbPegs = 4; // Nombre de pions � deviner   
    ArrayList<Combination> gameGrid; // Grille du jeux    
    Combination combinationToGuess; // Combinaison � d�viner
    
    int gameRound = 0; // Position de la derni�re combinaison saisie
    
    public Grid() {
       
    }
    
    /**
     * Generation de la combinaison � deviner
     **/
    void generateRandomCombination(){
        combinationToGuess = new Combination(nbPegs);
        combinationToGuess.randomCombination();
        
    }

    
    /**
     * Algo de r�olution de la ligne
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
     * @return si la derni�re combinaison entr�e est la bonne
     */
    boolean checkLastCombination(){
        Combination currentCombination = gameGrid.get(gameRound);        
        return combinationToGuess.equalsTo(currentCombination);
    }
    
    
    /**
     * 
     * @return Rajoute une ligne � la grille gameGrid 
     */
    void nextTry()
    {
        gameGrid.add(new Combination(nbPegs));
        gameRound++;
    }
        
}