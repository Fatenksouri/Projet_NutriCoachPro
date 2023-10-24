/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ksouri
 */
public class ServiceCategory {
      Connection cnx ;
    
        public ServiceCategory() {
        cnx = DataSource.getInstance().getInstance().getCnx();
}
        
        
 public List <Category> getAll() {
ObservableList<Category> myList = FXCollections.observableArrayList();
    String requete=null;
        try {
            requete = "SELECT * FROM category ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Category e = new Category();
            e.setId(rs.getInt(1));
            e.setType(rs.getString("type"));
            System.out.println(e);     
            myList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;
    }
     ///////////////////////////////////////////////////////////
 
    public void modifier(Category e) {
        System.out.print(e);
try {
            String requete3 = "UPDATE category SET type=?  WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, e.getType());
            pst.setInt(2,e.getId());
            pst.executeUpdate();
            System.out.println("Category updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }


    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM category WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }

   
    public Category getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ajouter(Category e) {
        System.out.println(e);
           try {

            String requete2 = "INSERT INTO category (`type`) VALUES (?)";

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, e.getType());

            pst.executeUpdate();
            System.out.println("Category added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
           

    }
    
}