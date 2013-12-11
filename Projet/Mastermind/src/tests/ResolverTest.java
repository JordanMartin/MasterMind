package tests;

import junit.framework.TestCase;

import mastermind.Combination;
import mastermind.Resolver;

/**
 * Cette classe permet de tester que le l'algoritme de résolution trouve bien la 
 * bonne solution et avec un nombre de tentative raisonnable.
 * @author Jordan
 */
public class ResolverTest extends TestCase {
    
    public void testCombinationHaving4Pegs(){
        Combination toGuess = new Combination(4);
        // Génère une combinaison aléatoire de 4 pions
        toGuess.randomCombination();
        
        Combination solution = new Combination(4);
        Resolver resolver = new Resolver(toGuess);
        
        int countStep = 0;
        
        
        while(!resolver.isGussed() && countStep < 30){            
           solution = resolver.nextStep();
           countStep++;
        }
        
        assertEquals(toGuess, solution);
    }
    
    public void testCombinationHaving10Pegs(){
        Combination toGuess = new Combination(10);
        // Génère une combinaison aléatoire de 4 pions
        toGuess.randomCombination();
        
        Combination solution = new Combination(10);
        Resolver resolver = new Resolver(toGuess);
        
        int countStep = 0;
        
        
        while(!resolver.isGussed() && countStep < 100){
           solution = resolver.nextStep();
           countStep++;
        }
        
        assertEquals(toGuess, solution);
    }
}
