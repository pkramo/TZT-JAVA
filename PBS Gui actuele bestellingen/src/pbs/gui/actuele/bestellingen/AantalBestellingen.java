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
    private int BestellingId;
    private int Koerier;
    private int Klant;
    private String LocatieBegin;
    private String EindLocatie;
    private JButton Sluit;
    private JPanel rechts;
    private JPanel links;
    private JLabel opvulling;
    private JPanel boven;
    private JLabel Welafg;
    
    AantalBestellingen() {
       setTitle("Actuele bestellingen");
       setSize(2000, 1000);
       
       Welafg = new JLabel("Afgeleverd");
       Welafg.setBounds(50,4,200,50);
       this.add(Welafg);
       
       JLabel Nietafg = new JLabel("Onderweg");
       Nietafg.setBounds(930,4,200,50);
       this.add(Nietafg);
       
       Sluit = new JButton("Sluit");
       this.add(Sluit);
       Sluit.setBounds(1830,0,80,50);
       Sluit.addActionListener(this);
       
       opvulling = new JLabel("");
       opvulling.setBounds(500,500,10,10);
       this.add(opvulling);
       
       links = new JPanel();
       links.setBounds(0,50, 930,1000);
       links.setBackground(Color.GREEN);
       this.add(links);
       
       boven = new JPanel();
       boven.setBounds(0,0,2000,50);
       boven.setBackground(Color.WHITE);
       this.add(boven);
       
       rechts = new JPanel();
       rechts.setBounds(930,50,900,1000);
       rechts.setBackground(Color.RED);
       this.add(rechts);
       
       

       
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== Sluit){
            
            this.setVisible(false);
        }
    }   
}
