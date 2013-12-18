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
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import javax.swing.ScrollPaneLayout;

public class Grille extends JScrollPane
{

    PanelGrille grille;

    public Grille(Color[][] couleurs)
      {
        super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
        setSize(40 * 4 + 50, 40 * 10 + 50);
        grille = new PanelGrille(couleurs);
        ScrollPaneLayout flow = new ScrollPaneLayout();
        setLayout(flow);
        setVisible(true);
        setViewportView(grille);
      }
}
