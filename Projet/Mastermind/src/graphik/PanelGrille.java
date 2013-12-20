/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author p1202922
 */
public class PanelGrille extends JPanel implements ActionListener{

    Color[][] couleurs;
    JButton[][] cercles;
    int courant;
    
    int i,e;
    /** @param n  the desired number of circles. */
    PanelGrille(Color[][] couleurs,int i, int e) {
        super(true);
        this.couleurs = couleurs;
        this.i = i;
        this.e = e;
        cercles = new JButton[e][i];
        for(int a = 0; a<i; a++)
        {
            for(int b = 0; b<e; b++)
            {
                cercles[b][a] = new JButton();
                cercles[b][a].setText("txt");
                cercles[b][a].addActionListener(this);
            }
        }
        
        reinit();
    }
    
    
   /* void improveSize()
    {
        int size_cercle = i+1;
        JButton temp[][] = new JButton[e][size_cercle];
        for(int a = 0; a<size_cercle; a++)
        {
            for(int b = 0; b<e; b++)
            {
                System.out.println(a + " " + b);
                temp[b][a] = new JButton();
                temp[b][a].setText("txt");
                temp[b][a].addActionListener(this);
            }
        }
        
        cercles = temp;
        setColors();
        reinit();
        repaint();
    }*/
    void setStep(int courant)
    {
        this.courant = courant;
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        if(courant <i)
            for(int b = 0; b<this.e; b++)
            {
                
                if(source == cercles[b][courant])
                {
                    cercles[b][courant].setBackground(couleurSuivante(cercles[b][courant].getBackground()));
                    couleurs[b][courant] = cercles[b][courant].getBackground();
                }
            }
        
    }
    Color couleurSuivante(Color a)
    {
        if(a == Color.GREEN)
        {
            return Color.ORANGE;
        }
        if(a == Color.ORANGE)
        {
            return Color.YELLOW;
        }
        if(a == Color.YELLOW)
        {
            return Color.GRAY;
        }
        if(a == Color.GRAY)
        {
            return Color.RED;
        }
        if(a == Color.RED)
        {
            return Color.BLUE;
        }
        if(a == Color.BLUE)
        {
            return Color.MAGENTA;
        }
        if(a == Color.MAGENTA)
        {
            return Color.PINK;
        }
        if(a == Color.PINK)
        {
            return Color.CYAN;
        }
        if(a == Color.CYAN)
        {
            return Color.GREEN;
        }
        return Color.GRAY;
    }
    final void reinit()
    {
        setColors();
        GridBagLayout finale = new GridBagLayout();
        setLayout(finale);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;

        gc.anchor = GridBagConstraints.LINE_START; // pas WEST.
        /* weightx définit le nombre de cases en abscisse */
		gc.weightx=e;
		
		/* weightx définit le nombre de cases en ordonnée */
		gc.weighty=i;
        for(int i = 0; i<this.i; i++)
        {
            for(int e = 0; e<this.e; e++)
            {
                gc.gridx = e;
                gc.gridy = i;
                add(cercles[e][i],gc);
            }
        }
        
    }
    void setColors()
    {
        for(int i = 0; i<this.i; i++)
        {
            for(int e = 0; e<this.e; e++)
            {
                cercles[e][i].setBackground(couleurs[e][i]);
            }
        }
    }
    @Override
    public void repaint()
    {
        setColors();
        super.repaint();
    }

   
}
    

