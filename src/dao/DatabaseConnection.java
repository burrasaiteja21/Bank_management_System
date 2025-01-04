package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides a method to establish a connection to the database.
 * The `getConnection` method loads the appropriate JDBC driver and establishes
 * a connection to the specified database using the provided URL and credentials.
 */
public class DatabaseConnection {

    /**
     * This method attempts to create a connection to the DERBY database.
     * It loads the JDBC driver for DERBY and establishes a connection using 
     * the provided credentials and database URL.
     * 
     * @return Connection object that represents the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            /**
             * Load the JDBC driver class for DERBY.
             * The Class.forName() method dynamically loads the driver class.
             */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (Exception e) {
            /**
             * If an exception occurs during the loading of the driver,
             * this block catches it. However, it is a rare occurrence and
             * should not typically happen.
             */
            System.out.println(e);
        }
        
        
        /**
         * Database URL for Derby connection
         * The URL specifies the IP address of the database server, the port, and the database name.
         */
        String type4URLDerby = "jdbc:derby://localhost:1527/bog;create=true";
        Connection con = DriverManager.getConnection(type4URLDerby, "scrummaster", "1234554321");
        
        /**
         * Return the established connection object.
         * This connection can be used to interact with the database.
         */
        return con;
    }
}
