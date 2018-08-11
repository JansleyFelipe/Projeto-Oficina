package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection("jdbc:sqlserver://fs5:1433;databasename=poo20188", "poo20188", "Pwnna5");
    }

    public static Connection getMySQLConnection() throws SQLException {
    	return DriverManager.getConnection("jdbc:mysql://localhost/users", "root", "root");
    }
}
