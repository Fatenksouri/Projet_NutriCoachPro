/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author omnia
 */
public class DataSourceConvention {
    private Connection cnx;
    
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/web";

    private static DataSourceConvention instance;
    
    private DataSourceConvention() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSourceConvention getInstance() {
        if(instance == null)
            instance = new DataSourceConvention();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
}
