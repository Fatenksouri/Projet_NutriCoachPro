/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;


import entities.Objecttype;
import entities.Regime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.Servicefichemesures;
import services.Serviceregime;

/**
 *
 * @author Kardo
 */
public class MyDB {
    
    String url = "jdbc:mysql://localhost:3306/gestregime";
    String user = "root";
    String pwd = "";
    
    
    Connection con;
    
    //3 
    static MyDB instance;
     //1 rendre le constructeur prive
    private MyDB() {
        
        try {
            con = DriverManager.getConnection(url, user, pwd);
            
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probeleme de connexion");
        }
    }
    
    // 2 etape: de creer une methode static pour utiliser le const 
    public static MyDB getinstance(){
        if(instance == null){
            instance =  new MyDB();
        }
        return instance;
        
    }

    public Connection getCon() {
        return con;
    }
      
 /*   public static ObservableList<Regime> getALLRegime(){
     Connection   con = MyDB.getinstance().getCon();
        ObservableList<Regime> list = FXCollections.observableArrayList();
        try {
            String sql ="select * from regime";
            Statement ste;

            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);        
        while(rs.next()){
               
                Objecttype objectifreg=Objecttype.valueOf(rs.getString("objectifreg"));
                Regime r;
                java.sql.Date sqlDate = rs.getDate("datecreationreg");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                r = new Regime(
                        rs.getInt("idreg"),
                        
                        rs.getString("nomreg"),
                        rs.getString("descriptionreg") ,
                        objectifreg ,
                        rs.getString("dureereg") ,
                        utilDate,
                        rs.getDouble("imcMin"),
                        rs.getDouble("imcMin"));
                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return list;
    }*/
    
} 


