/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author p1202922
 */


public class Graphik extends JFrame implements ActionListener{
    JPanel panel = new JPanel();
    JLabel text1 = new JLabel("MasterMind");
    JTextArea cons = new JTextArea("");
    JTextArea params = new JTextArea("");
    JButton selectionMode = new JButton("Selectionner le mode de jeu");
    JButton parametrerRegles = new JButton("Paramètres");
    JButton rejouer = new JButton("Rejouer");
    Color[][] couleurs; 
    String console;
    
    //Paramètres d'application
    int type_de_jeu = 3;
    boolean couleur_double = true;
    int nb_essais = 10;
    int nb_brique = 4;
    String paramResume = new String();
    public Graphik()
    {       
        selectionMode.addActionListener(this);
        parametrerRegles.addActionListener(this);
        rejouer.addActionListener(this);
        cons.setSize(new Dimension(500,500));
        cons.setBackground(new Color(0,0,0));
        actualiserParams();
        couleurs = new Color[10][4];
        for(int i = 0; i<10; i++)
        {
            for(int e = 0; e<4; e++)
            {
                couleurs[i][e] = Color.GRAY;
            }
        }
        setTitle("MasterMind Auto");
        console = "MasterMind Auto Console\n";
        setSize(1000, 600);
        setLocationRelativeTo(null);
        //action à faire quand on clique sur le bouton "fermer"
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
       
        setLayout(null);
        PanelGrille grille = new PanelGrille(couleurs);
        grille.setBounds(350,40,200,200);
        add(grille);
        
        text1.setBounds(440,0,100,50);
        add(text1);
        
        cons.setForeground(Color.RED);
        add(cons);
        cons.setBounds(700,100,250,400);
        
        selectionMode.setBounds(300,500,100,30);
        parametrerRegles.setBounds(450,500,100,30);
        rejouer.setBounds(600,500,100,30);
        add(selectionMode);  
        add(parametrerRegles);  
        add(rejouer);
        
        params.setBounds(30,100,300,300);
        add(params);
        setVisible(true);
        
        
    }
    public void actualiserParams()
    {int type_de_jeu = 3;
    boolean couleur_double = true;
    int nb_essais = 10;
    int nb_brique = 4;
        paramResume="Paramètres de jeu : \n";
        switch(type_de_jeu)
        {
            case 1:
                paramResume+="Interface texte\n";
                break;
                
            case 2:
                paramResume+="Interface texte Interactive\n";
                break;
                
            case 3:
                paramResume+="Interface draphique seule\n";
                break;
                
        }
        if(couleur_double) 
        {
            paramResume+="Les couleurs doubles sont autorisées\n";
        }
        else
        {
            paramResume+="Les couleurs doubles sont interdites\n";
        }
        paramResume+=nb_essais + " essais autorisés\n";
        paramResume+=nb_brique + " briques\n";
        params.setText(paramResume);
    }
    public void setColor(int i, int e, Color col)
    {
        couleurs[i][e] = col;
        repaint();
    }
    public void ecrireConsole(String ligne)
    {
        console= console + ligne + "\n";
        cons.setText(console);
    }
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
 
	if(source == selectionMode){
                JFrame f = new JFrame();
                ModeJeu m = new ModeJeu();
                m.setVisible(true);
                f.add(m);
                f.setSize(500, 300);
                f.setVisible(true);
		ecrireConsole("Selection de mode");
	} else if(source == parametrerRegles){
                JFrame f = new JFrame();
                Parametres m = new Parametres();
                m.setVisible(true);
                f.add(m);
                f.setSize(500, 300);
                f.setVisible(true);
		ecrireConsole("Selection des paramètres");
	} else if(source == rejouer){
		ecrireConsole("Rejouer");	
	}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graphik jeu = new Graphik();
        jeu.ecrireConsole("Début du jeu");
    }
}
