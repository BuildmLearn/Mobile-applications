package sqlite3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abhishek
 */
public class Database {

    private Connection c = null;
    private String dbName;
    private Statement stmt = null;
    private int result;

    public Database(String dbName) {
        this.dbName = dbName;
        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
            this.c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            this.stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*    public void executeQuery(String query) {
    try {h
    ResultSet rs = stmt.execute(query);
    System.out.println(rs.toString());
    } catch (SQLException e) {
    System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Query: " + query);
    System.exit(0);
    }
    }*/

    public void executeQuery1(String query) {
        try {
            stmt.executeUpdate(query);
            //ResultSet rs = stmt.getResultSet();
            //System.out.println("Rows affected: " + status + "\n");
            
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() + " Query: " + query);
            //System.exit(0);
        }
    }

    public void closeDb() {
        try {
            c.commit();
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
