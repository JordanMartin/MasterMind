/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

/**
 *
 * @author p1202922
 */
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import javax.swing.ScrollPaneLayout;
import mastermindf.Combination;

public class Grille extends JScrollPane{
    PanelGrille pane;
    int courant;
    Color[][] couleurs;
    int i,e;
    /**
     * Classe Grille
     *
     * @param couleurs Le tableau de couleur INITIAL
     * @param largeur Le nombre de briques par ligne
     * @param nb_ligne Nombre de ligne initial. Devrait être à 1 au début
     */
    public Grille(Color[][] couleurs,int largeur, int nb_ligne)
    {
        super(VERTICAL_SCROLLBAR_ALWAYS,HORIZONTAL_SCROLLBAR_ALWAYS);
        this.couleurs = couleurs;
        i = nb_ligne;
        e = largeur;
        ScrollPaneLayout flow = new ScrollPaneLayout();
        setPreferredSize(new Dimension(300,300));
        setLayout(flow);
        pane = new PanelGrille(couleurs,i,e);
        setViewportView(pane);
        setVisible(true);
        courant = 0;
        pane.setStep(courant);
    }
    
    /**
     * Affecte à la ligne courante les couleurs de la combinaison
     * @param comb La combinaison à affecter
     *
     */
    void setAllColors(Combination comb)
    {
        
        for(int e = 0; e<this.e;e++)
        {
            System.out.println("Accès à "+e);
             couleurs[e][courant] = comb.getColor(e); //Erreur ICI 
        }
    }
    
    /**
     * Permet de récupérer la couleur d'indice pos dans la ligne courante
     * @return Color La couleur
     *  @param pos La position de la couleur à récupérer
     */
    Color getPegAt(int pos)
    {
        System.out.println(pos+" " + courant);
        if(pos<0||pos>e-1)
        {
            return Color.GRAY;
        }
        System.out.println(couleurs[pos][courant]);
        return couleurs[pos][courant];
        
    }
    
    /**
     * Augmente le nombre de ligne (nb ligne = nb ligne +1)
     * 
     *
     */
    void improveSize()
    {
        Color col[][] = new Color[e][i+1];
        for(int a = 0; a<i+1; a++)
        {
            for(int b = 0; b<e; b++)
            {
                if(a == i)
                {
                    col[b][a] = Color.GRAY;
                }
                else
                {
                    col[b][a] = couleurs[b][a];
                }
            }
        }
        couleurs = col;
        pane = new PanelGrille(couleurs,i+1,e);
        i++;
        setViewportView(pane);
    }
    
    /**
     * Passe à l'étape d'après (ligne suivante)
     * 
     *
     */
    void nextStep()
    {
        courant++;
        pane.setStep(courant);
    }
    
    /**
     * Réinitialise la grille
     * 
     *
     */
    void reinit()
    {
        courant = 0;
        pane.setStep(courant);
    }
    
    /**
     * Redessine les éléments
     * 
     *
     */
    @Override
    public void repaint()
    {
        try
        {
            pane.repaint();
            super.repaint();
        }
        catch(Exception e)
        {
            
        }

    }
}
