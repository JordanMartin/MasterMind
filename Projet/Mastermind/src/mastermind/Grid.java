
package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    
    private final int nbTry = 10;
    private int gameRound = 0;
    // Nombre de pion � deviner
    private final int keyNumber = 4;
    // Combinaison � deviner
    private Color combination[];
    // tableau de couleur
    private Color color[]; 
    
    // Grille du jeu
    ArrayList<Color[]> gameGrid;

    public Grid() {
        gameGrid = new ArrayList< >( );
        color= new Color[6];
        color[0]=Color.BLUE;
        color[1]=Color.GREEN;
        color[2]=Color.ORANGE;
        color[3]=Color.RED;
        color[4]=Color.YELLOW;
        color[5]=Color.MAGENTA;
        
       
    }
    
    
    
    /**
     * G�n�ration de la combinaison � deviner
     **/
    void generateCombination(){
    
        for(int i=0; i <keyNumber; i++ ) 
               combination[i]=color[ (int) Math.random()*5];
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
         return(gameRound==nbTry);
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