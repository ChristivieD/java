package project;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String db_full_driver = dotenv.get("DB_FULL_DRIVER");
        String connectionString = dotenv.get("DB_CONNECTION_STRING");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");
        try {
            Class.forName(db_full_driver);
        } catch (ClassNotFoundException e) {
        }
        Connection connection = DriverManager.getConnection(connectionString, user, password);
        if (connection.isValid(2)) {
            System.out.println("Connection successful");
            return connection;
        } else {
            System.out.println("Connection failed");
            return null;
        }
    }
    public static void showError(SQLException e){
        while( e!= null){
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            Throwable t = e.getCause();
            while (t != null) {
                System.out.println("Cause:" + t);
                t = t.getCause();
            }
            e = e.getNextException();
        }
    }
}
