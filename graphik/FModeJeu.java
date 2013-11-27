/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p1202922
 */
public class FModeJeu extends JFrame implements ActionListener {
    ModeJeu m = new ModeJeu();
    public FModeJeu()
    {
        
        m.setVisible(true);
        add(m);
        setSize(500, 300);
        setVisible(true);
    }
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();

            /*if(source == m.jButton1){
                    this.dispose();
            }*/
        }
}
