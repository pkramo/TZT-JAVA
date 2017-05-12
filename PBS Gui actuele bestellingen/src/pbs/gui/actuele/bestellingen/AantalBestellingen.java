/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbs.gui.actuele.bestellingen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AantalBestellingen extends JDialog implements ActionListener{
 
    private JButton Sluit;

    AantalBestellingen() {
       setTitle("Aantal bestellingen");
       setSize(2000, 1000);
       
       Sluit = new JButton();
       Sluit.setBounds(0,0,80,60);
       this.add(Sluit);
       
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== Sluit){
            
            this.setVisible(false);
        }
    }   
}
