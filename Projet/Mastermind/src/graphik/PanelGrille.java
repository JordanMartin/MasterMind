/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p1202922
 */
public class PanelGrille extends JPanel
{

    Color[][] couleurs;

    /**
     * @param n the desired number of circles.
     */
    PanelGrille(Color[][] couleurs)
      {
        super(true);
        this.couleurs = couleurs;
      }

    @Override
    protected void paintComponent(Graphics g)
      {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        setSize(40 * 4 + 50, 40 * 10 + 50);
        for (int i = 0; i < 10; i++) {
            for (int e = 0; e < 4; e++) {

                g2d.setColor(couleurs[i][e]);
                g2d.fillOval(5 + e * 40, 5 + i * 40, 30, 30);
            }
        }


      }
}
