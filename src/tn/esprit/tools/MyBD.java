/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author Asus
 */
public class MyBD {
        //
    String url = "jdbc:mysql://localhost:3306/web";
    String user = "root";
    String pwd = "";
    
    
    Connection con;
    String filename =null;
    public static String path;
    
   //3 
    static MyBD instance;
     //1 rendre le constructeur prive
    private MyBD() {
        
        try {
            con = DriverManager.getConnection(url, user, pwd);
            
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("Probeleme de connexion");
        }
    }
    
    // 2 etape: de creer une methode static pour utiliser le const 
    public static MyBD getinstance(){
        if(instance == null){
            instance =  new MyBD();
        }
        return instance;
        
    }

    public Connection getCon() {
        return con;
    }
    
public String selectImage() {
    try {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if (f != null) {
            String imagePath = f.getAbsolutePath();
            return imagePath;
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une image");
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return null; // Retourne null si aucune image n'a été sélectionnée
}

}
