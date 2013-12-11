package mastermind;

import graphik.*;

/**
 *
 * @author Jordan
 */
public class Mastermind {
    
    Grid grid;
    
    Integer gameMode = null; // Modes de jeux
    // Les diff�rents modes de jeux
    public static final int ORDI_VS_ORDI = 0;
    public static final int USER_VS_ORDI = 1;
    public static final int ORDI_VS_USER = 2;
    public static final int USER_VS_USER = 4;
   
    
    Integer gridWidth = null; // Nombre de pions par ligne
    
    // Nombre d'essais maximum pour deviner la combinaison (0 pour illimit�)
    Integer maxTrials = null;
    
    // D�finit le niveau de log (0: aucun, 1: messages importants, 2: messages important et infos, 3: spam)
    int logLevel = 0;
    
    Combination combinationToGuess; // Combinaison � d�viner
    
    
    public Mastermind(){
        // Cr�ation de la grille de jeux
        grid = new Grid();        
    }
    
    /**
     * Demande � l'utilisateur la configuration du jeux d�sir�e
     */
    public void askUserToConfigureGame(){
        
        ParametersUI ui = new ParametersUI();
        ui.setModal(true); // Attend que la fen�tre soit ferm�e
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        
        if(ui.isCanceled)
            System.exit(0);
        
        setLogLevel(ui.logLevel);
        setGridWidth(ui.gridWidth);
        setMaxTrials(ui.maxTrials);
        setGameMode(ui.gameMode);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
        
        mastermind.setGameMode(ORDI_VS_ORDI);       
        mastermind.askUserToConfigureGame();
        mastermind.startGame();
    }
    
    /**
     * Lance l'algorithme de r�solution pour trouver la combinaison
     */
    public void findCombination(){
        
        System.out.println("Combinaison � trouver : " + combinationToGuess.get() + "\n");
        
        Logger.write(2, logLevel, "D�but de l'algorithme de r�solution");        
        Resolver r = new Resolver(combinationToGuess);
        
        while(!r.combinationFound && !isGameOver(r.stepNumber))
            System.out.println("Essai " + (r.stepNumber+1) + " : " + r.nextStep());
        
        if(isGameOver(r.stepNumber)){
            Logger.write(2, logLevel, "Algorithme de r�solution interrompu : nombre d'essai maximum atteint");
            System.out.println("Perdu ! Nombre d'essai maximum atteint");
        }else{
            Logger.write(2, logLevel, "Algorithme de r�solution termin� : Combinaison trouv�");
            System.out.println("\nCombinaison trouv� ! [" + r.stepNumber + " coups]  : " + r.combinationGuessed.get());
        }        
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
            case ORDI_VS_ORDI:
            case USER_VS_ORDI:
            case ORDI_VS_USER:
            case USER_VS_USER:
                gameMode = gm;
                break;
                
            default:
                gameMode = null;
        }        
        
        if(gameMode == ORDI_VS_ORDI)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Ordi");
        else if(gameMode == USER_VS_ORDI)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Ordi");
        else if(gameMode == ORDI_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Utlisateur");
        else if(gameMode == USER_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Utilisateur");
    }
    
     /**
     * Generation d'une combinaison al�atoire � deviner
     */
    void generateRandomCombinationToGuess(){
        Logger.write(1, logLevel, "G�n�ration d'une combinaison al�atoire");
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.randomCombination();  
        Logger.write(2, logLevel, "Combinaison al�atoire g�n�r�e : " + combinationToGuess.get());
    }
    
    /**
     * D�finit la largeur de la grille du jeux
     * @param n nombre de pions en largeur
     */
    void setGridWidth(int n) {
        if(n < 1)
            gridWidth = 4;
        else
            gridWidth = n;
        
        Logger.write(2, logLevel, "D�finition de la taille de la grille de jeu � : " + gridWidth);
    }    
    
    /**
     * D�finit le nombre maximum d'essai pour d�viner la combinaison
     * @param n Nombre d'essai maximim (0 pour illimit�)
     */
    void setMaxTrials(int n){
        maxTrials = (n <= 0) ? 0 : n;         
        Logger.write(2, logLevel, "D�finition du nombre maximum de coups � : " + maxTrials);
    }
    
    /**
     * D�finit le niveau de log pendant le jeux
     * @param n niveau de log (0: aucun, 1: messages importants, 2: maximum)
     */
    void setLogLevel(int n){
        if(n < 0)
            logLevel = 0;
        else if(n > 3)
            logLevel = 3;
        else
            logLevel = n;
        
        Logger.write(2, logLevel, "D�finition du niveau de log � : " + logLevel);
    }
    
    /**
     * Retourne vraie si le nombre maxium d'essai est d�pass� et que le nombre d'essais est limit�
     * @param trialNb Nombre d'essai depuis le d�but de partie
     * @return 
     */
    boolean isGameOver(int trialNb){        
        return maxTrials > 0 && trialNb > maxTrials;
    }

    /**
     * Commence le jeu selon le mode
     */
    private void startGame() {
        Logger.write(1, logLevel, "D�but d'une nouvelle partie");
        
        
        switch(gameMode){
            case ORDI_VS_ORDI:                                
                generateRandomCombinationToGuess();
                findCombination();
                break;
            default:
                System.out.println("MODE DE JEUX NON DISPO POUR LE MOMENT");
        }
    }
}
