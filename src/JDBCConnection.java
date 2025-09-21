import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankmanagementsystem";
    private static final String USER = "root";
    private static final String PASS = "Radhe123";

    static {
        try {
            // Explicitly load MySQL driver (safe across environments)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        }
    }

    // Always returns a new connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println(" Database connection failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


