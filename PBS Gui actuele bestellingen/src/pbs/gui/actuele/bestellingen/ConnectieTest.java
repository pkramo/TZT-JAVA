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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
 
public class ConnectieTest {
 
    public static void main(String[] args) {
        System.out.println("** De groeten van **");
        executeQuery();
    }
   
    private static void executeQuery(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
 
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/database pbs?", "root", "usbw");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Bestelling");
           
            while(rs.next()){
                System.out.println(rs.getString("Bestelling_id") + "    " +  rs.getString("Koerier") + "    " +  rs.getString("Klant") + "    " +  rs.getString("Beginlocatie") + "    "  +  rs.getString("Eindlocatie"));
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
    }
}
