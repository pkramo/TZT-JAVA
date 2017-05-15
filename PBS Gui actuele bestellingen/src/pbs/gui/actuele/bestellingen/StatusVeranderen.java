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
    JPanel jp = new JPanel();
    JButton jb = new JButton("Status updaten");
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
        //jp = new JPanel();
        //jp2 = new JPanel();
        //jp.add(jl);
        //jt = new JTextField("Please enter all your shit in here");
        jp.add(jl);
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
                executeQuery(input, Keuze);
            }
        });
        
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jt.getText();
                executeQuery(input, Keuze);
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
        //jb = new JButton("Press me");
        //jp.add(jb);
        add(jp);
    }
    public static void main(String[] args){
        StatusVeranderen d = new StatusVeranderen();
    }

    private static void executeQuery(String input, int Keuze){
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
    }
}
