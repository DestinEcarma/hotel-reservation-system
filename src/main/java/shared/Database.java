/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maker
 */
public class Database {

    public static final Connection connection;

    static {
        String dbHost = Defs.dotenv.get("DB_HOST");
        String dbUser = Defs.dotenv.get("DB_USER");
        String dbPass = Defs.dotenv.get("DB_PASS");

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
            System.out.println("Connection Established!");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Could not connect to Database!", ex);
        }

        connection = conn;
    }
}
