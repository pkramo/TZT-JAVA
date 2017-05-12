/**
 * Created by User on 11-5-2017.
 */
import java.sql.*;

public class Driver {
    public static void main(String[] args){
        try {
            //1. Get a connection to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql:localhost:3306/database pbs?", "root", "usbw");

            //2. Create a statement
            Statement myStmt = myConn.createStatement();

            //3. Execute SQL query
            ResultSet myRs = myStmt.executeQuery("select * from bestelling");

            //4. Process the result set
            while (myRs.next()){
                System.out.println(myRs.getString("Bestelling_id") + "    " +  myRs.getString("Koerier") + "    " +  myRs.getString("Klant") + "    " +  myRs.getString("Beginlocatie") + "    "  +  myRs.getString("Eindlocatie"));

            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
