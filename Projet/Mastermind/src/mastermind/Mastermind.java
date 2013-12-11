package mastermind;

import java.awt.Color;
import java.util.Scanner;

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
    public static final int USER_VS_USER     = 3;
    
   
    
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
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Niveau de log (0: aucun, 1: messages importants, 2: maximum) : ");
        setLogLevel(Integer.parseInt(s.nextLine())); 
        
        System.out.print("Largeur de la grille de jeux : ");
        setGridWidth(Integer.parseInt(s.nextLine()));
        
        System.out.print("Nombre d'essais maximum ? (0 pour illimit�) : ");
        setMaxTrials(Integer.parseInt(s.nextLine()));
        
        System.out.print("Choisir le mode de jeu :\n"
                + "1 - Ordi VS Ordi\n"
                + "2 - Joueur VS Ordi\n"
                + "3 - Ordi VS Joueur\n"
                + "4 - Joueur VS Joueur\n"
                + "Votre choix : ");
        setGameMode(Integer.parseInt(s.nextLine())-1);
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mastermind mastermind = new Mastermind();
       
       // mastermind.setGameMode(SYSTEM_VS_SYSTEM); 
        
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
        Logger.write(2, logLevel, "Valeur de gm pour setGameMode : "+ gm);        
        gameMode = gm;
        if(gameMode == SYSTEM_VS_SYSTEM)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Ordi");
        else if(gameMode == USER_VS_SYSTEM)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Ordi");
        else if(gameMode == SYSTEM_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Utlisateur");
        else if(gameMode == USER_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Utilisateur");
        else 
            gameMode = null;
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
        Logger.write(2, logLevel, "Valeur de gameMode : " + gameMode);
        
        switch(gameMode){
            case SYSTEM_VS_SYSTEM:                                
                generateRandomCombinationToGuess();
                findCombination();
                break;
            case USER_VS_SYSTEM:
                setCombinaisonToGuess();
                findCombination();
                break;
            default:
                System.out.println("MODE DE JEUX NON DISPO POUR LE MOMENT");
        }
    }
    
         public void setCombinaisonToGuess(){
             
             Scanner sc = new Scanner(System.in);
             combinationToGuess = new Combination(gridWidth);
             
             
             System.out.println("Veuillez saisir votre combinaison : ");
             for (int i =0; i <gridWidth; i++ ){
                 int sais = sc.nextInt();
                 System.out.println(sais);
                 Color c = combinationToGuess.intToColor(sc.nextInt());
                 
                 combinationToGuess.setPeg(i,c);
                          
             }
             Logger.write(1, logLevel, "Combinaison du Joueur cr��e");
             
              
             
    }
}
    
