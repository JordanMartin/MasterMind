package mastermind;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Cette classe permet de trouver une combinaison étape par étape.
 * Il va dans un premier temps trouver le nombre de pions de chaque couleur qui
 * sont dans la combinaison à trouver
 * Ensuite il va essayer toutes les possibilités avec ces pions trouvés
 * @author Jordan
 */
public class Resolver {
    
    private int currentColorIndice = 0; // Indice de la couleur testé
    private int currentCaseIndice = 0; // Indice de la case on l'on cherche le bon pionc
    private int currentPegsIndice = 0; // Indice du pions testé
    
    private int stepNumber = 0; // Numéro de l'étape depuis le début
    
    Combination combinationToGuess; // Combinaison à trouver
    Combination combinationGuessed; // Combinaison que l'algorithme remplit au fur et à mesure
    
     // Nombre de couleurs différentes    
    private int possibleColorNumber = Combination.POSSIBLE_COLORS.size();
    
    // Conitendra les pions de bonnes couleurs mais pas dans le bon ordre
    private ArrayList<Color> pegsInCombination = new ArrayList(); 
    
    private int combinationSize; // Nombre de pions (largeur) de la combinaison
    
    // Indique si tous les pions de bonnes couleurs ont été trouvés
    private boolean allColorsFound   = false;
    
    // Indique si la solution a été trouvé
    private boolean combinationFound = false;
    
    /**
     * Créer le resolver avec la combinaison qu'il va devoir trouver
     * @param toGuess 
     */
    public Resolver(Combination toGuess){
        combinationToGuess = toGuess;
        combinationSize = toGuess.width;
        combinationGuessed = new Combination(combinationSize);
    }
    
    /**
     * Test une nouvelle combinaison avec l'algorithme
     * @return la combinaison testée
     */
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
    
    /**
     * Test une combinaison afin de trouver le nombre de pions de chaque couleurs
     * @return la combinaison testée
     */
    private Combination findAllColorsPegs(){       
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
    
    /**
     * Test une nouvelle combinaison pour trouver la bonne combinaison
     * @return la combinaison testé
     */
    private Combination findRightCombination(){
        
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

    /**
     * @return Le numéro de l'étape courante
     */
    public int getStepNumber() {
        return stepNumber;
    }
    
     /**
     * @return vrai si la combinaison a été trouvé
     */
    public boolean isGussed() {
        return (combinationFound);
    }
}