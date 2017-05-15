/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbs.gui.actuele.bestellingen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.management.Query;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ActueleBestellingen extends JDialog implements ActionListener{
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
    private JTable table;
    
    
    
    ActueleBestellingen() {
       setTitle("Actuele bestellingen");
       setSize(2000, 1000);
       setLayout(new FlowLayout());
       
       String[] columnNames = {"Bestelling ID","Koerier ID", "klant ID", "Eindlocatie", "Beginlocatie","status"};
       
       Object[][] Queries = {
               {"1","2","3","4","5","6"},
       };
       table = new JTable(Queries, columnNames);
       table.setPreferredScrollableViewportSize(new Dimension(1400,800));
       table.setFillsViewportHeight(true);
       
       JScrollPane scrollpane = new JScrollPane(table);
       add(scrollpane);
       
       Sluit = new JButton("Sluit");
       this.add(Sluit);
       Sluit.setBounds(30,600,50,80);
       Sluit.addActionListener(this);
       
       executeQuery(table, "SELECT * bestelling");
   
       
       
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== Sluit){
            
            this.setVisible(false);
        }
    }
    private static void executeQuery(JTable table,String Query ){
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = null;
 
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database pbs?" +
                                                   "user=root&password=usbw");
            stat = conn.createStatement();
            rs = stat.executeQuery(Query);
           
            while(table.getRowCount() > 0) 
        {
            ((DefaultTableModel) table.getModel()).removeRow(0);
        }
        int columns = rs.getMetaData().getColumnCount();
        
        while(rs.next())
        {  
            Object[] row = new Object[columns];
            for (int i = 1; i <= columns; i++)
            {  
                row[i - 1] = rs.getObject(i);
            }
            ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
        }

        rs.close();
        stat.close();
        conn.close();
    
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
 
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException sqlEx) { } // ignore
                stat = null;
            }
        }
}
}
