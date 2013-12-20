/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import mastermindf.Combination;
import mastermindf.Logger;
import mastermindf.Mastermind;
import mastermindf.Resolver;

/**
 *
 * @author p1202922
 */
public class Graphik extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JLabel text1 = new JLabel("MasterMind");
    JTextArea cons = new JTextArea("");
    JTextArea params = new JTextArea("");
    JButton rejouer = new JButton("Rejouer");
    JButton valider = new JButton("valider");
    Color[][] couleurs;
    String console;
    int courant = 1;
    //Paramètres d'application
    int type_de_jeu = 3;
    boolean couleur_double = true;
    int nb_essais = 10;
    int nb_brique = 4;
    int logLevel;
    String paramResume = new String();
    Grille grille;
    // Fenetres de param
    JFrame f = new JFrame();
    ParametersUI m = new ParametersUI();
    Combination combinationToGuess;
    Resolver r;
    /**
     * Classe main
     *
     * @param logLevel Le niveau de log du jeu
     * @param gameMode Le mode de jeu
     * @param nb_brique Le nombre de briques par ligne
     * @param nb_essai_max Le nombre max d'essais avant echec
     */
    public Graphik(int logLevel, int gameMode, int nb_brique, int nb_essai_max) {
        
        type_de_jeu = gameMode;
        this.logLevel = logLevel;
        this.nb_brique = nb_brique;
        this.nb_essais = nb_essai_max;
        m.setVisible(false);
        //f.add(m);
        f.setSize(500, 300);
        f.setVisible(false);
        rejouer.addActionListener(this);
        valider.addActionListener(this);
        cons.setSize(new Dimension(500, 500));
        cons.setBackground(new Color(0, 0, 0));
        actualiserParams();
        couleurs = new Color[nb_brique][1];
        for (int i = 0; i < 1; i++) {
            for (int e = 0; e < nb_brique; e++) {
                couleurs[e][i] = Color.GRAY;
            }
        }
        setTitle("MasterMind Auto");
        console = "MasterMind Auto Console\n";
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //action à faire quand on clique sur le bouton "fermer"
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        setLayout(null);
        grille = new Grille(couleurs,4,1);
        grille.setBounds(350, 40, 300, 300);
        add(grille);
        
        valider.setBounds(390, 560, 200, 30);
        add(valider);


        text1.setBounds(440, 0, 100, 50);
        add(text1);

        cons.setForeground(Color.RED);
        add(cons);
        cons.setBounds(700, 100, 250, 400);

        rejouer.setBounds(600, 600, 100, 30);
        add(rejouer);

        params.setBounds(30, 100, 300, 300);
        add(params);
        setVisible(true);
        if(gameMode == Mastermind.ORDI_VS_USER)
        {
            combinationToGuess = new Combination(nb_brique);
            generateRandomCombinationToGuess();
            ecrireConsole("Combinaison générée. Trouvez-la !");
        }
        if(gameMode == Mastermind.ORDI_VS_ORDI)
        {
            combinationToGuess = new Combination(nb_brique);
            generateRandomCombinationToGuess();
            ecrireConsole("Combinaison générée. L'ordi va la trouver !");
            r = new Resolver(combinationToGuess);
        }


    }
    /**
     * Reprends l'utilisation de generateRandomCombinationToGuess() de
     * MasterMind
     */
    void generateRandomCombinationToGuess()
      {
        Logger.write(1, logLevel, "Génération d'une combinaison aléatoire");
        combinationToGuess.randomCombination();
        Logger.write(2, logLevel, "Combinaison aléatoire générée : " + combinationToGuess.get());
      }
    /**
     * Permet d'afficher les paramètres du jeu dans l'interface graphique
     * 
     */
    public void actualiserParams() {
        paramResume = "Paramètres de jeu : \n";
        switch (type_de_jeu) {
            case 1:
                paramResume += "Interface texte\n";
                break;

            case 2:
                paramResume += "Interface texte Interactive\n";
                break;

            case 3:
                paramResume += "Interface draphique seule\n";
                break;

        }
        if (couleur_double) {
            paramResume += "Les couleurs doubles sont autorisées\n";
        } else {
            paramResume += "Les couleurs doubles sont interdites\n";
        }
        paramResume += nb_essais + " essais autorisés\n";
        paramResume += nb_brique + " briques\n";
        
        paramResume += "Mode de jeu : ";
        switch(type_de_jeu)
        {
            case Mastermind.ORDI_VS_ORDI:
                paramResume += "Ordi vs Ordi";
                break;
            case Mastermind.ORDI_VS_USER:
                paramResume += "Ordi vs User";
                break;
            case Mastermind.USER_VS_ORDI:
                paramResume += "User vs Ordi";
                break;
            case Mastermind.USER_VS_USER:
                paramResume += "User vs User";
                break;
        }
        params.setText(paramResume);
    }
    /**
     * Permet d'affecter une coleur à un élement du tableau de couleur
     * 
     *
     */
    public void setColor(int i, int e, Color col) {
        couleurs[i][e] = col;
        repaint();
    }
    
    /**
     * écrit à la console de l'interface
     * 
     *
     */
    public void ecrireConsole(String ligne) {
        console = console + ligne + "\n";
        cons.setText(console);
    }
    
    /**
     * Gestion des évenements
     * 
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == rejouer) {
            console = "";
            ecrireConsole("Rejouer");
            courant = 1;
            remove(grille);

            
            couleurs = new Color[nb_brique][1];
            for (int i = 0; i < 1; i++) {
                for (int o = 0; o < nb_brique; o++) {
                    couleurs[o][i] = Color.GRAY;
                }
            }
            grille = new Grille(couleurs, 4, 1);
            grille.setBounds(350, 40, 300, 300);
            add(grille);
            repaint();
            grille.repaint();
            if(type_de_jeu == Mastermind.ORDI_VS_USER)
            {
                combinationToGuess = new Combination(nb_brique);
                generateRandomCombinationToGuess();
                ecrireConsole("Combinaison générée. Trouvez-la !");
            }
            
            if(type_de_jeu == Mastermind.ORDI_VS_ORDI)
            {
                combinationToGuess = new Combination(nb_brique);
                generateRandomCombinationToGuess();
                ecrireConsole("Combinaison générée. L'ordi va la trouver !");
                r = new Resolver(combinationToGuess);
            }
        } else if (source == valider) {
            
            courant++;
            if(courant<= nb_essais)
            {
                ecrireConsole("Validation");
                if(type_de_jeu == Mastermind.ORDI_VS_USER)
                {
                    Combination playerCombination = new Combination(nb_brique);
                    for(int i = 0; i<nb_brique;i++)
                    {
                        playerCombination.setPeg(i, grille.getPegAt(i));
                    }    
                    if(playerCombination.compare(combinationToGuess))
                    {
                        ecrireConsole("VICTOIRE");
                        ecrireConsole("---- FIN DU JEU -----");
                        ecrireConsole("Cliquez à nouveau sur valider pour recommencer un jeu");
                        courant= nb_essais + 1;
                        return;
                    }
                    else
                    {
                        Integer w = 0,b = 0;
                        ecrireConsole("Echec : White : " + playerCombination.getWhite() + " Black : "+playerCombination.getBlack());
                    }
                }
                else if(type_de_jeu == Mastermind.ORDI_VS_ORDI)
                {
                    Combination playerCombination = new Combination(nb_brique);
                    playerCombination = r.nextStep();
                    grille.setAllColors(playerCombination);
                    if(r.isGussed())
                    {
                        ecrireConsole("VICTOIRE");
                        ecrireConsole("---- FIN DU JEU -----");
                        ecrireConsole("Cliquez à nouveau sur valider pour recommencer un jeu");
                        courant= nb_essais + 1;
                        return;
                    }
                    else
                    {
                        Integer w = 0,b = 0;
                        ecrireConsole("Echec : White : " + playerCombination.getWhite() + " Black : "+playerCombination.getBlack());
                    }
                }
                grille.improveSize();
                grille.nextStep();
            }
            else if (courant > nb_essais) {
                grille.nextStep();
                ecrireConsole("---- FIN DU JEU -----");
                ecrireConsole("Cliquez à nouveau sur valider pour recommencer un jeu");
                
            }
        }
        if(console.length()>300)
        {
            console = console.substring(console.length()-300);
        }
    }

    
    /**
     * Conversion de Color vers String
     * @return String la couleur en toute lettre
     *  @param c La couleur à convertir
     */
    String switcher(Color c) {
        if (c == Color.GREEN) {
            return "Vert";
        }
        if (c == Color.ORANGE) {
            return "Orange";
        }
        if (c == Color.YELLOW) {
            return "Jaune";
        }
        if (c == Color.GRAY) {
            return "Gris";
        }
        if (c == Color.RED) {
            return "Rouge";
        }
        if (c == Color.BLUE) {
            return "Bleu";
        }
        if (c == Color.MAGENTA) {
            return "Magenta";
        }
        if (c == Color.PINK) {
            return "Rose";
        }
        if (c == Color.CYAN) {
            return "Cyan";
        }
        return "None";
    }
}
