package mastermind;

import graphik.*;

/**
 *
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
        mastermind.askUserToConfigureGame();
        mastermind.startGame();
      }

    /**
     * Initialize la grille du jeux
     */
    public Mastermind()
      {
        // Cr�ation de la grille de jeux
        grid = new Grid();
      }

    /**
     * Demande � l'utilisateur la configuration du jeux d�sir�e
     */
    public void askUserToConfigureGame()
      {

        ParametersUI ui = new ParametersUI();
        ui.setModal(true); // Attend que la fen�tre soit ferm�e
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);

        if (ui.isCanceled) {
            System.exit(0);
        }

        setLogLevel(ui.logLevel);
        setGridWidth(ui.gridWidth);
        setMaxTrials(ui.maxTrials);
        setGameMode(ui.gameMode);
      }

    /**
     * Lance l'algorithme de r�solution pour trouver la combinaison
     */
    public void findCombination()
      {

        System.out.println("Combinaison � trouver : " + combinationToGuess.get() + "\n");

        Logger.write(2, logLevel, "D�but de l'algorithme de r�solution");
        Resolver r = new Resolver(combinationToGuess);

        while (!r.isGussed() && !isGameOver(r.getStepNumber())) {
            System.out.println("Essai " + (r.getStepNumber() + 1) + " : " + r.nextStep());
        }

        if (isGameOver(r.getStepNumber())) {
            Logger.write(2, logLevel, "Algorithme de r�solution interrompu : nombre d'essai maximum atteint");
            System.out.println("Perdu ! Nombre d'essai maximum atteint");
        }
        else {
            Logger.write(2, logLevel, "Algorithme de r�solution termin� : Combinaison trouv�");
            System.out.println("\nCombinaison trouv� ! [" + r.getStepNumber() + " coups]  : " + r.combinationGuessed.get());
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
            System.out.println("Votre essai n�" + trials + " : " + playerCombination);

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
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Ordi");
        }
        else if (gameMode == USER_VS_ORDI) {
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Ordi");
        }
        else if (gameMode == ORDI_VS_USER) {
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Ordi vs Utlisateur");
        }
        else if (gameMode == USER_VS_USER) {
            Logger.write(1, logLevel, "Mode de jeu s�lectionn� : Utilisateur vs Utilisateur");
        }
      }

    /**
     * Generation d'une combinaison al�atoire � deviner
     */
    void generateRandomCombinationToGuess()
      {
        Logger.write(1, logLevel, "G�n�ration d'une combinaison al�atoire");
        combinationToGuess = new Combination(gridWidth);
        combinationToGuess.randomCombination();
        Logger.write(2, logLevel, "Combinaison al�atoire g�n�r�e : " + combinationToGuess.get());
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
        Logger.write(2, logLevel, "D�finition du nombre maximum de coups � : " + maxTrials);
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

        Logger.write(2, logLevel, "D�finition du niveau de log � : " + logLevel);
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
     * Commence le jeu selon le mode
     */
    private void startGame()
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
        System.out.println("Joueur 1 : Entrez la combinaison � faire deviner");
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
}
