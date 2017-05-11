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
 private JPanel f;

    public PBSGuiActueleBestellingen(){
        setTitle("Begin Scherm Applicatie");
        setSize(1200, 1000);
           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
        AanB = new JButton("Aantal bestellingen");
        this.AanB.setBounds(20,30,300,60);
        this.AanB.addActionListener(this);
                
        this.add(AanB);
                
        ActB = new JButton("Actuele bestellingen");
        ActB.setBounds(20,100,300,60);
        ActB.addActionListener(this);
                
        this.add(ActB);
        
        KlantZ = new JButton("Klant zakelijk");
        KlantZ.setBounds(20,170,300,60);
        this.add(KlantZ);
        
        KlantP = new JButton("Klant particulier");
        KlantP.setBounds(20,240,300,60);
        this.add(KlantP);
        
        JLabel Afgeleverd = new JLabel("");
        Afgeleverd.setBounds(800, 100,10 ,10 );
        this.add(Afgeleverd);
                
        setVisible(true);
    }
    
  public void actionPerformed(ActionEvent e){
        if(e.getSource()== AanB){
            JOptionPane.showMessageDialog(null, "Aantal bestellingen");
        }
    }

  public void actionPerformed1(ActionEvent e){
        if(e.getSource()== ActB){
            JOptionPane.showMessageDialog(null, "Actuele bestellingen");
        }
    }
  
  public static void main(String[] args) {
       PBSGuiActueleBestellingen act = new PBSGuiActueleBestellingen();
       System.out.println(act);      
    }
}

abstract class ActueleBestellingen extends JFrame{
                private JPanel f;
                public ActueleBestellingen(){
                this.add(f);
                f.setSize(400,400);    
                f.setLayout(null);    
                f.setVisible(true);   
                }
                
} 

abstract class AantalBestellingen extends JFrame{
                private JPanel f;
                public AantalBestellingen(){
                this.add(f);
                f.setSize(400,400);    
                f.setLayout(null);    
                f.setVisible(true);   
                }
                
} 

abstract class KlantZakelijk extends JFrame{
                private JPanel f;
                public KlantZakelijk(){
                this.add(f);
                f.setSize(400,400);    
                f.setLayout(null);    
                f.setVisible(true);   
                }
                
} 

