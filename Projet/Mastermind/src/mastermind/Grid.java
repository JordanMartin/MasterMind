package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    
    // Nombre de pion � deviner
    private final int keyNumber = 4;
    
    // Grille du jeu
    ArrayList<Color[]> gameGrid;
    
    
    /**
     * G�n�ration de la combinaison � deviner
     **/
    void generateCombination(){
        
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
        
    }
    
    /**
     * 
     * @return si la derni�re combinaison entr�e est la bonne
     */
    boolean checkCurrentCombination(){
        return (countWrongKey() == 0 && countRightKey() == keyNumber);
    }
    
    /**
     * 
     * @return le nombre de pion de la bonne couleur et bien plac� 
     */
    int countWrongKey(){
        
    }
    
    /**
     * 
     * @return le nombre de pion de la bonne couleur et mal plac� 
     */
    int countRightKey(){
        
    } 
    
}
