package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    
    // Nombre de pion à deviner
    private final int keyNumber = 4;
    
    // Grille du jeu
    ArrayList<Color[]> gameGrid;
    
    
    /**
     * Génération de la combinaison à deviner
     **/
    void generateCombination(){
        
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
        
    }
    
    /**
     * 
     * @return si la dernière combinaison entrée est la bonne
     */
    boolean checkCurrentCombination(){
        return (countWrongKey() == 0 && countRightKey() == keyNumber);
    }
    
    /**
     * 
     * @return le nombre de pion de la bonne couleur et bien placé 
     */
    int countWrongKey(){
        
    }
    
    /**
     * 
     * @return le nombre de pion de la bonne couleur et mal placé 
     */
    int countRightKey(){
        
    } 
    
}
