package tests;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;

import mastermind.Combination;
import mastermind.Resolver;

/**
 * Cette classe permet de tester que le l'algoritme de résolution trouve bien la
 * bonne solution et avec un nombre de tentative raisonnable.
 *
 * @author Jordan
 */
public class ResolverTest extends TestCase
{

    public void testCombinationHaving4Pegs()
      {
        Combination toGuess = new Combination(4);
        // Génère une combinaison aléatoire de 4 pions
        toGuess.randomCombination();
        Resolver resolver = new Resolver(toGuess);

        int countStep = 0;

        while (!resolver.isGussed() && countStep < 30) {
            resolver.nextStep();
            countStep++;
        }

        if (countStep >= 70) {
            System.out.println("Nombre d'essais dépassés pour 4pions");
        }
        else {
            System.out.println(countStep + " essais pour 4 pions");
        }

        assertEquals(toGuess.get(), resolver.getGussedCombination().get());
      }

    public void testCombinationHaving10Pegs()
      {
        Combination toGuess = new Combination(10);
        // Génère une combinaison aléatoire de 4 pions
        toGuess.randomCombination();
        Resolver resolver = new Resolver(toGuess);

        int countStep = 0;

        while (!resolver.isGussed() && countStep < 70) {
            resolver.nextStep();
            countStep++;
        }

        if (countStep >= 70) {
            System.out.println("Nombre d'essais dépassés pour 10pions");
        }
        else {
            System.out.println(countStep + " essais pour 10 pions");
        }

        assertEquals(toGuess.get(), resolver.getGussedCombination().get());
      }
}
