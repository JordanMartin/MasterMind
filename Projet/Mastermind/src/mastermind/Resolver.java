package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jordan
 */
public class Resolver {
    // Indice de la couleur testé
    int currentColorIndice = 0; 
    // Indice de la case on l'on cherche le bon pion
    int currentCaseIndice = 0; 
    // Indice du pions testé
    int currentPegsIndice = 0;
    
    int stepNumber = 0;
    
    Combination combinationToGuess;
    Combination combinationGuessed;
    
     // Nombre de couleurs différentes    
    int possibleColorNumber = Combination.POSSIBLE_COLORS.size();
    
    // Conitendra les pions de bonnes couleurs mais pas dans le bon ordre
    ArrayList<Color> pegsInCombination = new ArrayList(); 
    
    int combinationSize;
    
    // Indique si tous les pions de bonnes couleurs ont été trouvés
    boolean allColorsFound   = false;
    boolean combinationFound = false;
    
    
    public Resolver(Combination toGuess){
        combinationToGuess = toGuess;
        combinationSize = toGuess.width;
        combinationGuessed = new Combination(combinationSize);
    }
    
    public Combination nextStep(){
        stepNumber++;
        // Toutes les couleurs n'ont pas encore été trouvées. On continue à les chercher
        if(!allColorsFound){
           return findAllColorsPegs();  
        }            
        else{
            return findRightCombination();
        }           
    }
    
    
    public Combination findAllColorsPegs(){       
        // on récupère une couleur parmis toutes celles possibles
        Color colorToTest = Combination.POSSIBLE_COLORS.get(currentColorIndice); 
        // On créer un combinaison qu'avec cette couleur
        Combination combinationToTest = new Combination(combinationSize);        
        combinationToTest.setSamePegToAll(colorToTest);
        
        // On la compare à la combinaison à deviner pour récupérer les pions rouges/noirs
        combinationToTest.compare(combinationToGuess);
        
        if(combinationToTest.white > 0)
        {
            for(int i = 0; i < combinationToTest.white; i++)
                pegsInCombination.add(colorToTest);
            
            if(pegsInCombination.size() == combinationSize)
                allColorsFound = true;
            else
                currentColorIndice++;
        }else
             currentColorIndice++;
        
        return combinationToTest;
    }
    
    public Combination findRightCombination(){
        
        Color pegsToTest = pegsInCombination.get(currentPegsIndice);
        
        /* On met la couleur à tester sur la case a tester. Sur les autres cases
         * on met une couleur non présente dans la solutio */        
        Combination combinationToTest = new Combination(combinationSize);
        combinationToTest.setSamePegToAll(Combination.UNUSABLE_COLOR);
        combinationToTest.setPeg(currentCaseIndice, pegsToTest);
        
        combinationToTest.compare(combinationToGuess);
        
        if(combinationToTest.white == 1){
            // Ajotu du pions trouvé à la réponse
            combinationGuessed.setPeg(currentCaseIndice, pegsToTest);
            
            // Supprime le pions qui vient d'être trouvé
            pegsInCombination.remove(pegsToTest);
            
            /* On incrémente l'indice de la case dans laquelle il faudra placer 
             * un pion au prochain appel */
            currentCaseIndice++;
            
            // On remet à 0 l'indice du prochain pion à tester
            currentPegsIndice = 0;     
            
            //if(combinationToTest.equals(combinationToGuess))
            if (pegsInCombination.isEmpty()) 
                combinationFound = true;            
                
        }else
            currentPegsIndice++;
        
        return combinationToTest;
    }
}








