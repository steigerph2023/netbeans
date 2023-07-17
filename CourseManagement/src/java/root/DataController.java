/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hrkas
 */
public class DataController {

    public static Connection con;
    
    public DataController() {
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        DataController.con = con;
    }
    
    public static Connection getConnection(){
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/test";
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
