/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphik;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author p1202922
 */
public class FParametres extends JFrame implements ActionListener {
    Parametres m = new Parametres();
    JButton valider = new JButton("Valider les modifications");
    public FParametres()
    {
        JPanel pane = (JPanel)getContentPane();
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        valider.addActionListener(this);
        pane.setLayout(flow);
        m.setVisible(true);
        pane.add(m);
        pane.add(valider);
        setSize(500, 300);
        setVisible(true);
    }
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();

            if(source == valider){
                    dispose();
            }
        }
}
