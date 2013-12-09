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
import javax.swing.JComboBox;
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
    JTextField jtfX = new JTextField("X(1-4)");
    JComboBox<String> a = new JComboBox();
    JButton confirmerModif = new JButton("Confirmer");
    JButton selectionMode = new JButton("Selectionner le mode de jeu");
    JButton parametrerRegles = new JButton("Paramètres");
    JButton rejouer = new JButton("Rejouer");
    JButton valider = new JButton("valider");
    Color[][] couleurs; 
    String console;
    int courant = 0;
    
    //Paramètres d'application
    int type_de_jeu = 3;
    boolean couleur_double = true;
    int nb_essais = 10;
    int nb_brique = 4;
    String paramResume = new String();
    
    
    // Fenetres de param
    
    JFrame f = new JFrame();
    Parametres m = new Parametres();
                
    public Graphik()
    {       
        
        m.setVisible(false);
        f.add(m);
        f.setSize(500, 300);
        f.setVisible(false);
        selectionMode.addActionListener(this);
        parametrerRegles.addActionListener(this);
        rejouer.addActionListener(this);
        valider.addActionListener(this);
        confirmerModif.addActionListener(this);
        cons.setSize(new Dimension(500,500));
        cons.setBackground(new Color(0,0,0));
        actualiserParams();
        couleurs = new Color[nb_essais][nb_brique];
        for(int i = 0; i<nb_essais; i++)
        {
            for(int e = 0; e<nb_brique; e++)
            {
                couleurs[i][e] = Color.GRAY;
            }
        }
        setTitle("MasterMind Auto");
        console = "MasterMind Auto Console\n";
        setSize(1000, 700);
        setLocationRelativeTo(null);
        //action à faire quand on clique sur le bouton "fermer"
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
       
        setLayout(null);
        Grille grille = new Grille(couleurs);
        grille.setBounds(350,40,200,400);
        add(grille);
        
        jtfX.setBounds(390,500,100,20);
        add(jtfX);
        a.setBounds(500,500,90,20);
        add(a);
        valider.setBounds(390,560,200,20);
        confirmerModif.setBounds(390,530,200,20);
        add(confirmerModif);
        add(valider);
        
        
        text1.setBounds(440,0,100,50);
        add(text1);
        
        cons.setForeground(Color.RED);
        add(cons);
        cons.setBounds(700,100,250,400);
        
        selectionMode.setBounds(300,600,100,30);
        parametrerRegles.setBounds(450,600,100,30);
        rejouer.setBounds(600,600,100,30);
        add(selectionMode);  
        add(parametrerRegles);  
        add(rejouer);
        
        params.setBounds(30,100,300,300);
        add(params);
        setVisible(true);
        
        a.addItem("Noir");
        a.addItem("Blanc");
        a.addItem("Vert");
        a.addItem("Jaune");
        a.addItem("Violet");
        a.addItem("Bleu");
        a.addItem("Rouge");
        
        
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
                FModeJeu m = new FModeJeu();
                m.setSize(500, 300);
                m.setVisible(true);
		ecrireConsole("Selection de mode");
	} 
        else if(source == parametrerRegles){
                FParametres m = new FParametres();
                m.setSize(500, 300);
                m.setVisible(true);
		ecrireConsole("Selection des paramètres");
                
	} 
        else if(source == rejouer){
		ecrireConsole("Rejouer");
                for(int i = 0; i<nb_essais; i++)
                {
                    for(int ei = 0; ei<nb_brique; ei++)
                    {
                        setColor(i,ei,Color.GRAY);
                    }
                }
	}
        else if(source == valider){
		ecrireConsole("Validation");
                
                
                
                
                /*
                        
                        
                        
                        ICI LE CODE DE VéRIFICATION
                        
                        
                        */
                courant= courant + 1;
                if(courant>10)
                {
                    ecrireConsole("---- FIN -----");
                    courant = -1;
                }
	}
        else if(source == confirmerModif)
        {
            try
            {
                int indice = Integer.parseInt(jtfX.getText());
                if(indice >0&&indice<=nb_brique)
                {
                    indice--;
                    switch(a.getSelectedIndex())
                    {
                        case 0:
                            setColor(courant,indice,Color.BLACK);
                            break;
                        case 1:
                            setColor(courant,indice,Color.WHITE);
                            break;
                        case 2:
                            setColor(courant,indice,Color.GREEN);
                            break;
                        case 3:
                            setColor(courant,indice,Color.YELLOW);
                            break;
                        case 4:
                            setColor(courant,indice,Color.PINK);
                            break;
                        case 5:
                            setColor(courant,indice,Color.BLUE);
                            break;
                        case 6:
                            setColor(courant,indice,Color.RED);
                            break;
                    }
                    
                    ecrireConsole("Effectué");
                }
                else
                {
                    ecrireConsole("Erreur");
                }
        }
            catch(NumberFormatException h)
            {
                ecrireConsole("Erreur");
            }
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
