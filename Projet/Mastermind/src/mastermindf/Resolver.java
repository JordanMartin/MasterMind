package mastermindf;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Cette classe permet de trouver une combinaison �tape par �tape. Il va dans un
 * premier temps trouver le nombre de pions de chaque couleur qui sont dans la
 * combinaison � trouver Ensuite il va essayer toutes les possibilit�s avec ces
 * pions trouv�s
 *
 * @author Jordan
 */
public class Resolver
{

    private int currentColorIndice = 0; // Indice de la couleur test�
    private int currentCaseIndice = 0; // Indice de la case on l'on cherche le bon pionc
    private int currentPegsIndice = 0; // Indice du pions test�
    private int stepNumber = 0; // Num�ro de l'�tape depuis le d�but
    Combination combinationToGuess; // Combinaison � trouver
    Combination combinationGuessed; // Combinaison que l'algorithme remplit au fur et � mesure
    // Nombre de couleurs diff�rentes    
    private int possibleColorNumber = Combination.POSSIBLE_COLORS.size();
    // Conitendra les pions de bonnes couleurs mais pas dans le bon ordre
    private ArrayList<Color> pegsInCombination = new ArrayList();
    private int combinationSize; // Nombre de pions (largeur) de la combinaison
    // Indique si tous les pions de bonnes couleurs ont �t� trouv�s
    private boolean allColorsFound = false;
    // Indique si la solution a �t� trouv�
    private boolean combinationFound = false;

    /**
     * Cr�er le resolver avec la combinaison qu'il va devoir trouver
     *
     * @param toGuess
     */
    public Resolver(Combination toGuess)
      {
        combinationToGuess = toGuess;
        combinationSize = toGuess.width;
        combinationGuessed = new Combination(combinationSize);
      }

    /**
     * Test une nouvelle combinaison avec l'algorithme
     *
     * @return la combinaison test�e
     */
    public Combination nextStep()
      {
        stepNumber++;
        // Toutes les couleurs n'ont pas encore �t� trouv�es. On continue � les chercher
        if (!allColorsFound) {
            return findAllColorsPegs();
        }
        else {
            return findRightCombination();
        }
      }

    /**
     * Test une combinaison afin de trouver le nombre de pions de chaque
     * couleurs
     *
     * @return la combinaison test�e
     */
    private Combination findAllColorsPegs()
      {
        // on r�cup�re une couleur parmis toutes celles possibles
        Color colorToTest = Combination.POSSIBLE_COLORS.get(currentColorIndice);
        // On cr�er un combinaison qu'avec cette couleur
        Combination combinationToTest = new Combination(combinationSize);
        combinationToTest.setSamePegToAll(colorToTest);

        // On la compare � la combinaison � deviner pour r�cup�rer les pions rouges/noirs
        combinationToTest.compare(combinationToGuess);

        if (combinationToTest.white > 0) {
            for (int i = 0; i < combinationToTest.white; i++) {
                pegsInCombination.add(colorToTest);
            }

            if (pegsInCombination.size() == combinationSize) {
                allColorsFound = true;
            }
            else {
                currentColorIndice++;
            }
        }
        else {
            currentColorIndice++;
        }
        System.out.println(combinationToTest);
        return combinationToTest;
      }

    /**
     * Test une nouvelle combinaison pour trouver la bonne combinaison
     *
     * @return la combinaison test�
     */
    private Combination findRightCombination()
      {
          System.out.println(pegsInCombination);
        Color pegsToTest = pegsInCombination.get(currentPegsIndice);

        /* On met la couleur � tester sur la case a tester. Sur les autres cases
         * on met une couleur non pr�sente dans la solutio */
        Combination combinationToTest = new Combination(combinationSize);
        combinationToTest.setSamePegToAll(Combination.UNUSABLE_COLOR);
        combinationToTest.setPeg(currentCaseIndice, pegsToTest);

        combinationToTest.compare(combinationToGuess);

        if (combinationToTest.white == 1) {
            // Ajotu du pions trouv� � la r�ponse
            combinationGuessed.setPeg(currentCaseIndice, pegsToTest);

            // Supprime le pions qui vient d'�tre trouv�
            pegsInCombination.remove(pegsToTest);

            /* On incr�mente l'indice de la case dans laquelle il faudra placer 
             * un pion au prochain appel */
            currentCaseIndice++;

            // On remet � 0 l'indice du prochain pion � tester
            currentPegsIndice = 0;

            //if(combinationToTest.equals(combinationToGuess))
            if (pegsInCombination.isEmpty()) {
                combinationFound = true;
            }

        }
        else {
            currentPegsIndice++;
        }
        System.out.println(combinationToTest);
        return combinationToTest;
      }

    /**
     * @return Le num�ro de l'�tape courante
     */
    public int getStepNumber()
      {
        return stepNumber;
      }

    /**
     * @return vrai si la combinaison a �t� trouv�
     */
    public boolean isGussed()
      {
        return (combinationFound);
      }

    /**
     * @return la combinaison que l'algo a trouv�
     */
    public Combination getGussedCombination()
      {
        return combinationGuessed;
      }
}