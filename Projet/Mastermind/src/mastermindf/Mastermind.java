package mastermindf;

import graphik.*;

/**
 * Celle classe est celle qui repr�sente le jeu avec tous ces options
 * @author Jordan
 */
public class Mastermind
{

    Grid grid;
    Integer gameMode = null; // Mode de jeux
    /**
     * L'ordinateur doit trouver une combinaison g�n�r� al�atoirement
     */
    public static final int ORDI_VS_ORDI = 0;
    /**
     * L'ordinateur doit trouver une combinaison entr�e par l'utilisateur
     */
    public static final int USER_VS_ORDI = 1;
    /**
     * Le joueur doit trouver une combinaison g�nr�e al�atoirement
     */
    public static final int ORDI_VS_USER = 2;
    /**
     * Un joueur doit trouver un combinaison entr�e par un autre joueur
     */
    public static final int USER_VS_USER = 4;
    Integer gridWidth = null; // Nombre de pions par ligne
    // Nombre d'essais maximum pour deviner la combinaison (0 pour illimit�)
    Integer maxTrials = null;
    // D�finit le niveau de log (0: aucun, 1: messages importants, 2: messages important et infos, 3: spam)
    int logLevel = 0;
    Combination combinationToGuess; // Combinaison � d�viner
    
    /**
     * Classe main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
      {
        Mastermind mastermind = new Mastermind();

        mastermind.setGameMode(ORDI_VS_ORDI);
        mastermind.startGame();
      }

    /**
     * Demande à l'utilisateur la configuration du jeux désirée
     */
    public void ConfigureGame(int logLvl, int gridWidth, int maxTrials, int gameMode)
      {
        // D�finition des param�tres entr�es par l'utilisateur
        setLogLevel(logLvl);
        setGridWidth(gridWidth);
        setMaxTrials(maxTrials);
        setGameMode(gameMode);
      }

    /**
     * Lance l'algorithme de résolution pour trouver la combinaison
     */
    public void findCombination()
      {

        System.out.println("Combinaison à trouver : " + combinationToGuess.get() + "\n");

        Logger.write(2, logLevel, "Début de l'algorithme de résolution");
        Resolver r = new Resolver(combinationToGuess);

        while (!r.isGussed() && !isGameOver(r.getStepNumber())) {
            System.out.println("Essai " + (r.getStepNumber() + 1) + " : " + r.nextStep());
        }

        if (isGameOver(r.getStepNumber())) {
            Logger.write(2, logLevel, "Algorithme de résolution interrompu : nombre d'essai maximum atteint");
            System.out.println("Perdu ! Nombre d'essai maximum atteint");
        }
        else {
            Logger.write(2, logLevel, "Algorithme de résolution terminé : Combinaison trouvé");
            System.out.println("\nCombinaison trouvé ! [" + r.getStepNumber() + " coups]  : " + r.combinationGuessed.get());
        }
      }

    /**
     * Demmande au joueur d'entrer des combinaisons afin de trouver celle �
     * deviner
     *
     * @return true si la combinaison a �t� trouv� dans le nombre d'essai
     * impartis
     */
    private boolean askUserForResolution()
      {

        int trials = 0;

        Combination playerCombination = new Combination(gridWidth);

        do {
            trials++;
            playerCombination.askPegsFromConsole();
            playerCombination.compare(combinationToGuess);
            System.out.println("Votre essai num " + trials + " : " + playerCombination);

        } while (!playerCombination.equals(combinationToGuess) && !isGameOver(trials));

        return !isGameOver(trials) && playerCombination.equals(combinationToGuess);
      }

    /**
     * D�finit le mode de jeux
     *
     * @param gm Mode de jeux (voir variables static)
     */
    public void setGameMode(int gm)
      {

        switch (gm) {
            case ORDI_VS_ORDI:
            case USER_VS_ORDI:
            case ORDI_VS_USER:
            case USER_VS_USER:
                gameMode = gm;
                break;

            default:
                gameMode = null;
        }

        if (gameMode == ORDI_VS_ORDI) {
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Ordi vs Ordi");
        }
        else if (gameMode == USER_VS_ORDI) {
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Utilisateur vs Ordi");
        }
        else if (gameMode == ORDI_VS_USER) {
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Ordi vs Utlisateur");
        }
        else if (gameMode == USER_VS_USER) {
            Logger.write(1, logLevel, "Mode de jeu sélectionné : Utilisateur vs Utilisateur");
        }
      }

    /**
     * Generation d'une combinaison al�atoire � deviner
     */
    void generateRandomCombinationToGuess()
      {
        Logger.write(1, logLevel, "Génération d'une combinaison aléatoire");
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.randomCombination();
        Logger.write(2, logLevel, "Combinaison aléatoire générée : " + combinationToGuess.get());
      }

    /**
     * D�finit la largeur de la grille du jeux
     *
     * @param n nombre de pions en largeur
     */
    void setGridWidth(int n)
      {
        if (n < 1) {
            gridWidth = 4;
        }
        else {
            gridWidth = n;
        }

        Logger.write(2, logLevel, "D�finition de la taille de la grille de jeu � : " + gridWidth);
      }

    /**
     * D�finit le nombre maximum d'essai pour d�viner la combinaison
     *
     * @param n Nombre d'essai maximim (0 pour illimit�)
     */
    void setMaxTrials(int n)
      {
        maxTrials = (n <= 0) ? 0 : n;
        Logger.write(2, logLevel, "Définition du nombre maximum de coups à : " + maxTrials);
      }

    /**
     * D�finit le niveau de log pendant le jeux
     *
     * @param n niveau de log (0: aucun, 1: messages importants, 2: maximum)
     */
    void setLogLevel(int n)
      {
        if (n < 0) {
            logLevel = 0;
        }
        else if (n > 3) {
            logLevel = 3;
        }
        else {
            logLevel = n;
        }

        Logger.write(2, logLevel, "Définition du niveau de log à : " + logLevel);
      }

    /**
     * Retourne vraie si le nombre maxium d'essai est d�pass� et que le nombre
     * d'essais est limit�
     *
     * @param trialNb Nombre d'essai depuis le d�but de partie
     * @return
     */
    boolean isGameOver(int trialNb)
      {
        return maxTrials > 0 && trialNb > maxTrials;
      }

    /**
     * Commence le jeu et selectionne le bon mode
     */
    public void startGame()
      {
        Logger.write(1, logLevel, "D�but d'une nouvelle partie");


        switch (gameMode) {
            case ORDI_VS_ORDI:
                generateRandomCombinationToGuess();
                findCombination();
                break;

            case USER_VS_ORDI:
                // Demande au joueur de rentrer sa combinaison
                combinationToGuess = new Combination(gridWidth);
                combinationToGuess.askPegsFromConsole();

                // Recherche de la solution avec l'algorithme
                findCombination();
                break;

            case USER_VS_USER:
                playUserVsUser();
                break;

            case ORDI_VS_USER:
                playOrdiVsUser();
                break;

            default:
                System.out.println("MODE DE JEUX NON DISPO POUR LE MOMENT");
        }
      }

    private void playUserVsUser()
      {
        System.out.println("Joueur 1 : Entrez la combinaison à faire deviner");
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.askPegsFromConsole();

        System.out.println("Joueur 2 : Entrez les combinaisons et tentez de trouver la bonne");

        if (askUserForResolution()) {
            System.out.println("Bravo vous avez trouver la bonne combinaison !");
        }
        else {
            System.out.println("Perdu ! Vous avez atteint le nombre d'essai max.");
        }

      }

    private void playOrdiVsUser()
      {
        generateRandomCombinationToGuess();

        System.out.println("L'ordinateur vient de g�n�rer une combinaison. A vous de la deviner !");

        if (askUserForResolution()) {
            System.out.println("Bravo vous avez trouver la bonne combinaison !");
        }
        else {
            System.out.println("Perdu ! Vous avez atteint le nombre d'essai max.");
        }
      }
    public Mastermind()
    {
        logLevel = 0;
    }
}