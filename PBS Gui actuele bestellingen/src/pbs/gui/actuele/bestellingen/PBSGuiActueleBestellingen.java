package pbs.gui.actuele.bestellingen;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author hugoh
 */
public class PBSGuiActueleBestellingen extends JFrame implements ActionListener {
private JButton AanB;
private JButton ActB;
private JButton KlantZ;
private JButton KlantP;

/**
 * GETTERS 
 */
    public JButton getAanB() {
        return AanB;
    }
    public JButton getActB() {
        return ActB;
    }
    public JButton getKlantZ() {
        return KlantZ;
    }
    public JButton getKlantP() {
        return KlantP;
    }
    
    public PBSGuiActueleBestellingen(){
        setTitle("Begin Scherm Applicatie");
        setSize(2000, 1000);
           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
        AanB = new JButton("Aantal bestellingen");
        AanB.setBounds(500,150,800,100);
        this.add(AanB);
                
        ActB = new JButton
        ("Actuele bestellingen");
        ActB.setBounds(500,300,800,100);
        ActB.addActionListener(this);  
        
        this.add(ActB);
        
        KlantZ = new JButton("Klant zakelijk");
        KlantZ.setBounds(500,450,800,100);
        this.add(KlantZ);
        
        KlantP = new JButton("Klant particulier");
        KlantP.setBounds(500,600,800,100);
        this.add(KlantP);
        
        JLabel Afgeleverd = new JLabel("");
        Afgeleverd.setBounds(300,750,800,100);
        this.add(Afgeleverd);
                
        setVisible(true);
    }
    
  public void actionPerformed(ActionEvent e){
        if(e.getSource()== ActB){
            ActueleBestellingen Actueel = new ActueleBestellingen();
            Actueel.setVisible(true);
        }
    }
  
  public static void main(String[] args) {
       PBSGuiActueleBestellingen act = new PBSGuiActueleBestellingen();
       System.out.println(act);      
    }
}