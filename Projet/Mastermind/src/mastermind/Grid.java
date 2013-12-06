
package mastermind;

import exception.CombinationException;
import java.awt.Color;
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
    
    
    public static void main(String args[]) throws Exception{
        
        Grid g = new Grid();
        g.findCombinationToGuess();
    }

    public Grid() {
       gameGrid = new ArrayList(initGridLine); // Création de la grille
    }
    

    
    /**
     * Algorithme perettant de trouver la combinaison à trouver
     * @throws Exception 
     */
    void findCombinationToGuess() throws CombinationException
    {
        /* 1er étape : On compte, pour chaque couleur, le nombre de pions de cette 
         * couleur contenu dans la combinaison à deviner
         */     
        int possibleColorsNb = Combination.POSSIBLE_COLORS.size();
        
        ArrayList<Color> pegsInCombination = new ArrayList(gridWidth);
        Combination tmp = new Combination(gridWidth);
        int total = 0;
                
        for(int i = 0; i < possibleColorsNb; i++)
        {
            // On récupère une couleur parmi celle dispo
            Color currentColor = Combination.POSSIBLE_COLORS.get(i); 
            
            // On créer une combination avec tous les pions de la même couleur
            tmp.setSamePegToAll(currentColor);
            
            // On compare la combinaison créée avec celle à deviner pour récupérer le nombre de pions blanc
            tmp.compare(combinationToGuess);
            
            putCombinationOnGrid(tmp);
            
            // On récupère le nombre de pions de bonne et bien placés
            if(tmp.white > 0)
            {
                // On ajoute dans le tableau les pions contenu dans la solution
                for(int j = 0; j < tmp.white; j++)
                    pegsInCombination.add(currentColor);
                
                total += tmp.white;
            
                // Si on a trouvé tous les pions pas besoin de continuer
                if(total >= gridWidth) break;
            }
        }
        
        /* Maintenant que l'on connait le nombre d'élément de chaque couleur, 
         * On va les placer
         */       
        Combination tmpCombination = new Combination(gridWidth); // Combinaison qui nous permettra de comparer à une autre
        Combination combinationGuessed = new Combination(gridWidth); // Solution que l'on construit
        
        // Parcours de toutes les cases
        for(int i = 0; i < gridWidth; i++)
        {
            // Pour chaque case, on essaye toutes les couleurs
            for(int j = 0; j < pegsInCombination.size(); j++)
            {            
                // On met toutes les cases à null sauf le pions que l'on veut placer
                tmpCombination.setSamePegToAll(Combination.UNUSABLE_COLOR);
                tmpCombination.setPeg(i, pegsInCombination.get(j));
                
                // Comme un seul pion est de la bonne couleur, si on  
                // obtient un pion blanc cela signifie qu'il est bien placé
                tmpCombination.compare(combinationToGuess);
                putCombinationOnGrid(tmpCombination);
                
                if(tmpCombination.white == 1){
                    // Il est bien placé donc on l'ajoute à la combinaison finale
                    combinationGuessed.setPeg(i, pegsInCombination.get(j));
                    // On retire le pions ajouté
                    pegsInCombination.remove(j);
                    // On quitte cette boucle car le pions a été trouvé à cette possition
                    break;
                }               
            }
        }     
        
        // Combinaison trouvé on la compare pour mettre les flags blanc et noir à la bonne valeur
        combinationGuessed.compare(combinationToGuess);
        putCombinationOnGrid(combinationGuessed);
    }
    
    
    /**
     * 
     * @return vrai si la dernière combinaison entrée est la bonne. Faux sinon
     */
    boolean checkLastCombination(){
        Combination currentCombination = gameGrid.get(gameRound-1);        
        return combinationToGuess.equals(currentCombination);
    }
    
    /**
     * 
     * @param c combinaison à ajouter
     */
    void putCombinationOnGrid(Combination c){
        gameGrid.add(c);
        gameRound++;
        
        System.out.println("Essai : " + gameGrid.get(gameRound-1));
        
        if(checkLastCombination())
            System.out.println("Gangné ! "  + gameRound + " essais");
    }
}