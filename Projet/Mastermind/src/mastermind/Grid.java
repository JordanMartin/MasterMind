
package mastermind;

import exception.CombinationException;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Grid {
    
    int gridWidth = 4; // Nombre de pions � deviner   
    int initGridLine = 20; // Nombre de ligne initial pr�sente dans la grille
    ArrayList<Combination> gameGrid; // Grille du jeux    
        
    int gameRound = 0; // Position de la derni�re combinaison saisie
    
    
    public static void main(String args[]) throws Exception{
        
        Grid g = new Grid();
        g.findCombinationToGuess();
    }

    public Grid() {
       gameGrid = new ArrayList(initGridLine); // Cr�ation de la grille
    }
    

    
    /**
     * Algorithme perettant de trouver la combinaison � trouver
     * @throws Exception 
     */
    void findCombinationToGuess() throws CombinationException
    {
        /* 1er �tape : On compte, pour chaque couleur, le nombre de pions de cette 
         * couleur contenu dans la combinaison � deviner
         */     
        int possibleColorsNb = Combination.POSSIBLE_COLORS.size();
        
        ArrayList<Color> pegsInCombination = new ArrayList(gridWidth);
        Combination tmp = new Combination(gridWidth);
        int total = 0;
                
        for(int i = 0; i < possibleColorsNb; i++)
        {
            // On r�cup�re une couleur parmi celle dispo
            Color currentColor = Combination.POSSIBLE_COLORS.get(i); 
            
            // On cr�er une combination avec tous les pions de la m�me couleur
            tmp.setSamePegToAll(currentColor);
            
            // On compare la combinaison cr��e avec celle � deviner pour r�cup�rer le nombre de pions blanc
            tmp.compare(combinationToGuess);
            
            putCombinationOnGrid(tmp);
            
            // On r�cup�re le nombre de pions de bonne et bien plac�s
            if(tmp.white > 0)
            {
                // On ajoute dans le tableau les pions contenu dans la solution
                for(int j = 0; j < tmp.white; j++)
                    pegsInCombination.add(currentColor);
                
                total += tmp.white;
            
                // Si on a trouv� tous les pions pas besoin de continuer
                if(total >= gridWidth) break;
            }
        }
        
        /* Maintenant que l'on connait le nombre d'�l�ment de chaque couleur, 
         * On va les placer
         */       
        Combination tmpCombination = new Combination(gridWidth); // Combinaison qui nous permettra de comparer � une autre
        Combination combinationGuessed = new Combination(gridWidth); // Solution que l'on construit
        
        // Parcours de toutes les cases
        for(int i = 0; i < gridWidth; i++)
        {
            // Pour chaque case, on essaye toutes les couleurs
            for(int j = 0; j < pegsInCombination.size(); j++)
            {            
                // On met toutes les cases � null sauf le pions que l'on veut placer
                tmpCombination.setSamePegToAll(Combination.UNUSABLE_COLOR);
                tmpCombination.setPeg(i, pegsInCombination.get(j));
                
                // Comme un seul pion est de la bonne couleur, si on  
                // obtient un pion blanc cela signifie qu'il est bien plac�
                tmpCombination.compare(combinationToGuess);
                putCombinationOnGrid(tmpCombination);
                
                if(tmpCombination.white == 1){
                    // Il est bien plac� donc on l'ajoute � la combinaison finale
                    combinationGuessed.setPeg(i, pegsInCombination.get(j));
                    // On retire le pions ajout�
                    pegsInCombination.remove(j);
                    // On quitte cette boucle car le pions a �t� trouv� � cette possition
                    break;
                }               
            }
        }     
        
        // Combinaison trouv� on la compare pour mettre les flags blanc et noir � la bonne valeur
        combinationGuessed.compare(combinationToGuess);
        putCombinationOnGrid(combinationGuessed);
    }
    
    
    /**
     * 
     * @return vrai si la derni�re combinaison entr�e est la bonne. Faux sinon
     */
    boolean checkLastCombination(){
        Combination currentCombination = gameGrid.get(gameRound-1);        
        return combinationToGuess.equals(currentCombination);
    }
    
    /**
     * 
     * @param c combinaison � ajouter
     */
    void putCombinationOnGrid(Combination c){
        gameGrid.add(c);
        gameRound++;
        
        System.out.println("Essai : " + gameGrid.get(gameRound-1));
        
        if(checkLastCombination())
            System.out.println("Gangn� ! "  + gameRound + " essais");
    }
}