
package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    private int nbKey = 4;
    //Nombre de tentatives
    private final int nbTry = 10;
    //Position dans la grille
    private int gameRound = 0;
    // Nombre de pion à deviner
    private final int keyNumber = 4;
    // Combinaison à deviner
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
     * Generation de la combinaison à deviner
     **/
    void generateCombination(){
    
        for(int i=0; i <keyNumber; i++ ) 
               combination[i]=color[ (int) Math.random()*5];
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
         return(gameRound==nbTry);
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
     * @return le nombre de pion de la bonne couleur et mal placé 
     */
    int countWrongKey(){
        
    }
    
    /**
     * 
     * @return le nombre de pion de la bonne couleur et bien placé 
     */
    int countRightKey(){
        
        int count = 0;
        for(int i=0 ; i<nbKey ; i++)
        {
            if(gameGrid.get(gameRound)[i]==color[i])
            {
                count++;
                
            }
        }
        return count;
        
    } 
    /**
     * 
     * @return Rajoute une ligne à la grille gameGrid 
     */
    void nextTry()
    { 
        Color [] next= new Color[nbKey];
        gameGrid.add(next);
    }
    
    
    
    
    
    
    
    
}