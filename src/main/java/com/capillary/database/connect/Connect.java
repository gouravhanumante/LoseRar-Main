package com.capillary.database.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect implements IDBConnect{
     /**
     * Connect to a sample database
     */
     @Override
    public Connection connect() throws SQLException {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/home/sarthakjain/sqlite/huffmanDB.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
//                    conn.close();
            }
        }
        return null;
    }
    /**
     * @param args the command line arguments
     */
}
