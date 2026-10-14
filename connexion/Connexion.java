package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    public static Connection connect() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521/EEXDB.oracle.docker";
        //try {
        //    Class.forName("oracle.jdbc.OracleDriver");            
        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}
        return DriverManager.getConnection(url, "foot", "foot");
    }

}
