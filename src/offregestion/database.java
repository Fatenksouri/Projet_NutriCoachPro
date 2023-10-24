/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jaafr
 */
public class database {
    
      Connection cnx;
     static database instance;
    
    String url = "jdbc:mysql://localhost:3306/chadha";
     String user = "root";
    String password = "";
     private database(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static database getInstance(){
        if(instance == null){
            instance = new database();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return this.cnx;
    }
}
