package mastermind;

/**
 *
 * @author Jordan
 */
public class MathPerso {

    /**
     * Calcul de la factorielle d'un nombre
     * @param n
     */
    public static int fact(int n) {
        if(n == 0 || n == 1)
            return 1;
        else if(n < 0)
            System.err.println("Nombre négatif passé à la fonction factorielle");
        
        return fact(n-1)*n;
    }    
}
