/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbs.gui.actuele.bestellingen;

/**
 *
 * @author User
 */

/**
 * Created by User on 12-5-2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by pjott on 11-5-2017.
 */
public class StatusVeranderen extends JFrame {
    
    int Keuze;
    String input;
    int rowsAffected = -1;
    String naam;
    JPanel jp = new JPanel();

    JButton jb = new JButton("Status updaten");
    JLabel jl1 = new JLabel("Fatale error. Probeerd u het alstublieft opnieuw. Indien dit geen oplossing bied neemt u dan contact op met de systeembeheerder");
    JLabel jl2 = new JLabel("Er is geen bestelling gevonden met dit bestelling ID");
    JLabel jl3 = new JLabel("De status van bestelling nummer van bezorger is succesvol gewijzigd");
    JLabel jl = new JLabel("Bestelling nummer:   ");
    JFrame jf = new JFrame();
    JTextField jt = new JTextField(30);
    JRadioButton jr1 = new JRadioButton("Aangemeld bij startlocatie");
    JRadioButton jr2 = new JRadioButton("Bezorgd bij eindlocatie");
    
    
    
    
    
    
    ButtonGroup group = new ButtonGroup();
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
        
        jl1.setVisible(false);
        jl2.setVisible(false);
        jl3.setVisible(false);
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
                NaamOpvragen(input);
            }
        });
        
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();
                statusUpdaten(input, Keuze);
                NaamOpvragen(input);
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
   
    public void setRowsAffected(int rowsAffected) {
        this.rowsAffected = rowsAffected;
    }
    
    //Status Updaten 
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
            
            
            //Controleren of er echt waardes zijn veranderd
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println(rowsAffected);
            
            if (rowsAffected == 0){
                jl1.setVisible(false);
                jl2.setVisible(true);
                jl3.setVisible(false);
            }
            else if (rowsAffected == 1){
                jl1.setVisible(false);
                jl2.setVisible(false);
                jl3.setVisible(true);
            }
            else {
                jl1.setVisible(true);
                jl2.setVisible(false);
                jl3.setVisible(false);
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
    private String NaamOpvragen(String input){
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?","root" , "usbw");
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM account WHERE Account_id in (SELECT Koerier FROM bestelling WHERE Bestelling_id = " + input + ")");
		    
		    while(rs.next()){
                        naam = rs.getString("naam");
                        System.out.println(naam);
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
}
