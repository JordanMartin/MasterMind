package mastermind;

/**
 *
 * @author Jordan
 */
public class Mastermind {
    
    Grid grid;
    
    Integer gameMode = null; // Modes de jeux
    // Les diff�rents modes de jeux
    public static final int SYSTEM_VS_SYSTEM = 0;
    public static final int USER_VS_SYSTEM   = 1;
    public static final int SYSTEM_VS_USER   = 2;
    public static final int USER_VS_USER     = 4;
    
    Integer gridWidth = null; // Nombre de pions par ligne
    
    // Nombre d'essais maximum pour deviner la combinaison (0 pour illimit�)
    Integer maxTrials = null;
    
    Combination combinationToGuess; // Combinaison � d�viner
    
    
    public Mastermind(){
        // Cr�ation de la grille de jeux
        grid = new Grid();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        mastermind.setGameMode(SYSTEM_VS_SYSTEM);
        mastermind.setGridWidth(4);
        mastermind.generateRandomCombinationToGuess();
        mastermind.findCombinationToGuess();
    }
    
    /**
     * Lance l'algorithme de r�solution pour trouver la combinaison
     */
    public void findCombinationToGuess(){
        
        System.out.println("Combinaison � trouver : " + combinationToGuess.get() + "\n");
        
        Resolver r = new Resolver(combinationToGuess);
        
        while(!r.combinationFound)
            System.out.println("Essai " + r.stepNumber + " : " + r.nextStep());
        
        System.out.println("\nTrouv� ! [" + r.stepNumber + " coups]  : " + r.combinationGuessed.get());
    }
    
    /**
     * Remplis la zone d'indication (nombre de pion correct et incorrect)
     */
    void fillIndicationArea(){
            
    }
    
    /**
     * D�finit le mode de jeux
     * @param gm Mode de jeux (voir variables static)
     */
    public void setGameMode(int gm){
        
        switch(gm){
            case SYSTEM_VS_SYSTEM:
            case USER_VS_SYSTEM:
            case SYSTEM_VS_USER:
            case USER_VS_USER:
                gameMode = gm;
                break;
                
            default:
                gameMode = null;
        }        
    }
    
     /**
     * Generation d'une combinaison al�atoire � deviner
     */
    void generateRandomCombinationToGuess(){
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.randomCombination();        
    }
    
    /**
     * D�finit la largeur de la grille du jeux
     * @param n nombre de pions en largeur
     */
    void setGridWidth(int n) {
        if(n < 1)
            gridWidth = null;
        else
            gridWidth = n;
    }
    
}
