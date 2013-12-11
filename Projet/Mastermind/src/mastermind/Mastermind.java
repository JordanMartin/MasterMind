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
    // Les différents modes de jeux
    public static final int SYSTEM_VS_SYSTEM = 0;
    public static final int USER_VS_SYSTEM   = 1;
    public static final int SYSTEM_VS_USER   = 2;
    public static final int USER_VS_USER     = 3;
    
   
    
    Integer gridWidth = null; // Nombre de pions par ligne
    
    // Nombre d'essais maximum pour deviner la combinaison (0 pour illimité)
    Integer maxTrials = null;
    
    // Définit le niveau de log (0: aucun, 1: messages importants, 2: messages important et infos, 3: spam)
    int logLevel = 0;
    
    Combination combinationToGuess; // Combinaison à déviner
    
    
    public Mastermind(){
        // Création de la grille de jeux
        grid = new Grid();        
    }
    
    /**
     * Demande à l'utilisateur la configuration du jeux désirée
     */
    public void askUserToConfigureGame(){
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Niveau de log (0: aucun, 1: messages importants, 2: maximum) : ");
        setLogLevel(Integer.parseInt(s.nextLine())); 
        
        System.out.print("Largeur de la grille de jeux : ");
        setGridWidth(Integer.parseInt(s.nextLine()));
        
        System.out.print("Nombre d'essais maximum ? (0 pour illimité) : ");
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
     * Lance l'algorithme de résolution pour trouver la combinaison
     */
    public void findCombination(){
        
        System.out.println("Combinaison à trouver : " + combinationToGuess.get() + "\n");
        
        Logger.write(2, logLevel, "Début de l'algorithme de résolution");        
        Resolver r = new Resolver(combinationToGuess);
        
        while(!r.combinationFound && !isGameOver(r.stepNumber))
            System.out.println("Essai " + (r.stepNumber+1) + " : " + r.nextStep());
        
        if(isGameOver(r.stepNumber)){
            Logger.write(2, logLevel, "Algorithme de résolution interrompu : nombre d'essai maximum atteint");
            System.out.println("Perdu ! Nombre d'essai maximum atteint");
        }else{
            Logger.write(2, logLevel, "Algorithme de résolution terminé : Combinaison trouvé");
            System.out.println("\nCombinaison trouvé ! [" + r.stepNumber + " coups]  : " + r.combinationGuessed.get());
        }
        
    }
    
    /**
     * Remplis la zone d'indication (nombre de pion correct et incorrect)
     */
    void fillIndicationArea(){
            
    }
    
    /**
     * Définit le mode de jeux
     * @param gm Mode de jeux (voir variables static)
     */
    public void setGameMode(int gm){
        Logger.write(2, logLevel, "Valeur de gm pour setGameMode : "+ gm);        
        gameMode = gm;
        if(gameMode == SYSTEM_VS_SYSTEM)
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Ordi vs Ordi");
        else if(gameMode == USER_VS_SYSTEM)
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Utilisateur vs Ordi");
        else if(gameMode == SYSTEM_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Ordi vs Utlisateur");
        else if(gameMode == USER_VS_USER)
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Utilisateur vs Utilisateur");
        else 
            gameMode = null;
    }
    
     /**
     * Generation d'une combinaison aléatoire à deviner
     */
    void generateRandomCombinationToGuess(){
        Logger.write(1, logLevel, "Génération d'une combinaison aléatoire");
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.randomCombination();  
        Logger.write(2, logLevel, "Combinaison aléatoire générée : " + combinationToGuess.get());
    }
    
    /**
     * Définit la largeur de la grille du jeux
     * @param n nombre de pions en largeur
     */
    void setGridWidth(int n) {
        if(n < 1)
            gridWidth = 4;
        else
            gridWidth = n;
        
        Logger.write(2, logLevel, "Définition de la taille de la grille de jeu à : " + gridWidth);
    }    
    
    /**
     * Définit le nombre maximum d'essai pour déviner la combinaison
     * @param n Nombre d'essai maximim (0 pour illimité)
     */
    void setMaxTrials(int n){
        maxTrials = (n <= 0) ? 0 : n;         
        Logger.write(2, logLevel, "Définition du nombre maximum de coups à : " + maxTrials);
    }
    
    /**
     * Définit le niveau de log pendant le jeux
     * @param n niveau de log (0: aucun, 1: messages importants, 2: maximum)
     */
    void setLogLevel(int n){
        if(n < 0)
            logLevel = 0;
        else if(n > 3)
            logLevel = 3;
        else
            logLevel = n;
        
        Logger.write(2, logLevel, "Définition du niveau de log à : " + logLevel);
    }
    
    /**
     * Retourne vraie si le nombre maxium d'essai est dépassé et que le nombre d'essais est limité
     * @param trialNb Nombre d'essai depuis le début de partie
     * @return 
     */
    boolean isGameOver(int trialNb){        
        return maxTrials > 0 && trialNb > maxTrials;
    }

    /**
     * Commence le jeu selon le mode
     */
    private void startGame() {
        Logger.write(1, logLevel, "Début d'une nouvelle partie");
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
             Logger.write(1, logLevel, "Combinaison du Joueur créée");
             
              
             
    }
}
    
