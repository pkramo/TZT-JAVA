/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbs.gui.actuele.bestellingen;

//Alles importeren
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Latest Update 16th May 2017
 * By Peter Pluim
 * Online alias: Pjotr Rine, Pjotter32
 * Any questions dont hesitate to send a email to peterpluimcode@hotmail.com
 */
public class StatusVeranderen extends JFrame {
    
    // Alle variablen Creëren
    int Keuze = 0;
    int Puntenwaarde = 0;
    int Koerier_id = 0;
    int rowsAffected = 0;
    int AantalBestellingen = 0;
    int Puntensaldo = 0;
    int NieuwePuntenWaarde = 0;
    int HuidigeStatus;
    String naam = "";
    String input = "";
    
    //alle atributen van de gui aanmaken
    JPanel jp = new JPanel();

    JButton jb = new JButton("Status updaten");
    JLabel jl1 = new JLabel("Fatale error. Probeerd u het alstublieft opnieuw. Indien dit geen oplossing bied neemt u dan contact op met de systeembeheerder");
    JLabel jl2 = new JLabel("Er is geen bestelling gevonden met dit bestelling ID");
    JLabel jl3 = new JLabel("De status van bestelling nummer van bezorger is succesvol gewijzigd");
    JLabel jl4 = new JLabel("Deze bestilling is al bezorgd. Probeer het nog een keer en neem anders contact op met uw leidinggevende.");
    JLabel jl = new JLabel("Bestelling nummer:   ");
    JFrame jf = new JFrame();
    JTextField jt = new JTextField(30);
    JRadioButton jr1 = new JRadioButton("Aangemeld bij startlocatie");
    JRadioButton jr2 = new JRadioButton("Bezorgd bij eindlocatie");
    ButtonGroup group = new ButtonGroup();
    
    //Gui scherm initialiseren en alle atributen toevoegen aan het JFrame. Ook is dit het startpunt van de SQL Query's
    public StatusVeranderen() {
        setTitle("Titel");
        setSize(1920, 1080);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        
        jp.add(jl);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        
        jl1.setVisible(false);
        jl2.setVisible(false);
        jl3.setVisible(false);
        jl4.setVisible(false);
        jp.add(jt);
        
        
        jp.add(jb);
        jp.add(jr1);
        jp.add(jr2);
        group.add(jr1);
        group.add(jr2);
        
        
        
        jt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();
                statusUpdaten(input, Keuze);
            }
        });
        
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();
                HuidigeStatusOpvragen(input);
            }
        });
        
        jr1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Keuze = 1;
        }
    });
        
        jr2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Keuze = 2;
        }
    });
       
        add(jp);
    }
    public static void main(String[] args){
        StatusVeranderen d = new StatusVeranderen();
    }
       
    //Deze functie zoekt in de database de huidige status van de bestelling op. Als dit 2 is (Dan is hij bezorgd bij de eindlocatoe). Dan is het niet meer mogelijk om aanpassingen te maken aan deze bestelling.
    private int HuidigeStatusOpvragen(String input){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;


		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM bestelling WHERE Bestelling_id = " + input );
                    System.out.println("We eindigen net voor de while loop");

		    
		    while(rs.next()){
                        System.out.println("We komen in de while loop");
                        HuidigeStatus = rs.getInt("BestellingGeleverd");
                        
                        if (HuidigeStatus == 2){
                            jl1.setVisible(false);
                            jl2.setVisible(false);
                            jl3.setVisible(false);
                            jl4.setVisible(true);
                        }
                        else if (HuidigeStatus == 1 || HuidigeStatus == 0){
                            statusUpdaten(input, Keuze);
                        }
                        else {
                            jl1.setVisible(false);
                            jl2.setVisible(true);
                            jl3.setVisible(false);
                            jl4.setVisible(false);
                        }
                        
                        
                        
		    }
                    
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    //  release resources
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
                return 5;
	}
    
    //Deze functie update de Status van een bestelling. Hierbij wordt er gezocht op bestelling_id en daarna wordt de status gewijzigd in de keuze die is gegeven via de radiobuttons.
    private int statusUpdaten(String input, int Keuze){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            // Get a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");

            // Create a statement
            stmt = conn.createStatement();

            // Execute Sql query
            String sql = "Update bestelling Set BestellingGeleverd = " + Keuze + " Where Bestelling_id = " + input;
            stmt.executeUpdate(sql);
            if (Keuze == 2){
                Koerier_id_Opvragen(input);
                
            }
            
            
            //Controleren of er echt waardes zijn veranderd
            System.out.println("We komen hier uit");
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println(rowsAffected);
            if (rowsAffected == 0){
                jl1.setVisible(false);
                jl2.setVisible(true);
                jl3.setVisible(false);
                jl4.setVisible(false);
            }
            else if (rowsAffected == 1){
                jl1.setVisible(false);
                jl2.setVisible(false);
                jl3.setVisible(true);
                jl4.setVisible(false);
            }
            else {
                jl1.setVisible(true);
                jl2.setVisible(false);
                jl3.setVisible(false);
                jl4.setVisible(false);
            }

        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            //  release resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
        //algemene return voor werking
        return 5;
    }
    
    //Hier wordt het koerier_id opgevraagd van de Koerier die is gekoppeld aan de bestelling. Deze word hierna verder gebruikt in de functie Punten_AantalBestellingen_Selecteren
    private String Koerier_id_Opvragen(String input){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM bestelling WHERE Bestelling_id = " + input );
		    
		    while(rs.next()){
                            Koerier_id = rs.getInt("Koerier");
                            Puntenwaarde = rs.getInt("Puntenwaarde");
                            Punten_AantalBestellingen_Selecteren(Koerier_id, Puntenwaarde);
                            
                            
		    }   
                    
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    //  release resources
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
                return "Dit is een standaard return";
	}
    
    //Deze functie gebruikt het Koerier_id om de totale hoeveelheid bestellingen die de koerier heeft bezorgd en zijn puntensaldo ophaald. Hierna berekend hij wat na deze bezorging zijn nieuwe puntensaldo en totaal aantal bestelling moet zijn en geeft hij dit door aan Punten_AantalBesetllingen_Updaten.
    private int Punten_AantalBestellingen_Selecteren(int Koerier_id, int Puntenwaarde){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;


		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM koerier WHERE Account_id = " + Koerier_id );

		    
		    while(rs.next()){
                        AantalBestellingen = rs.getInt("Totaal_geleverd");
                        AantalBestellingen = AantalBestellingen + 1;
                        Puntensaldo = rs.getInt("Puntensaldo");
                        NieuwePuntenWaarde = Puntensaldo + Puntenwaarde;
                        Punten_AantalBestellingen_Updaten(AantalBestellingen, NieuwePuntenWaarde, Koerier_id);
                        
                        
		    }
                    
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    //  release resources
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
                return 5;
	}
    
    //Deze functie gebruikt de gegevens van Punten_AantalBestellingen_Selecteren en update de waarde in de tabel met behulp van Koerier_id
    private int Punten_AantalBestellingen_Updaten(int AantalBestellingen, int NieuwePuntenWaarde, int Koerier_id){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");
		    stmt = conn.createStatement();
                    
                    
		    String sql = "Update koerier Set Totaal_geleverd = " + AantalBestellingen + " , Puntensaldo = " + NieuwePuntenWaarde + " WHERE Account_id = " + Koerier_id;
                    stmt.executeUpdate(sql);
                    
		}
		catch (SQLException ex){
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    //  release resources
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore
		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore
		        stmt = null;
		    }
		}
                return 5;
	}
}
