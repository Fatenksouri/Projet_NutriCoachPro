/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Convention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSourceConvention;

/**
 *
 * @author omnia
 */
public class ServiceConvention {
    
   Connection cnx ;
    
        public ServiceConvention() {
        cnx = DataSourceConvention.getInstance().getInstance().getCnx();
}
    
    



 
     public List <Convention> getAll() {
ObservableList<Convention> myList = FXCollections.observableArrayList();
    String requete=null;
        try {
            requete = "SELECT * FROM convention";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Convention e = new Convention();
            e.setId(rs.getInt(1));
            e.setSociete(rs.getString("societe"));
            e.setAdresse(rs.getString("adresse"));
            e.setEmail(rs.getString("email"));
            e.setTelephone(rs.getString("telephone"));
            e.setStatus(rs.getString("status"));
            myList.add(e);
            System.out.println(e);        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;
    }
     
     //////////////////////////////////////////////////////////
          public List <Convention> getAllsos() {
ObservableList<Convention> myList = FXCollections.observableArrayList();
    String requete=null;
        try {
            requete = "SELECT * FROM convention";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Convention e = new Convention();
            e.setId(rs.getInt(1));
            e.setSociete(rs.getString("societe"));
            myList.add(e);
            System.out.println(e);        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;
    }
     ///////////////////////////////////////////////////////////
    
    public void modifier(Convention e) {
        System.out.print(e);
try {
            String requete3 = "UPDATE convention SET societe=?,adresse=?,email=?,telephone=?,status=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, e.getSociete());
            pst.setString(2, e.getAdresse());
            pst.setString(3, e.getEmail());
            pst.setString(4, e.getTelephone());  
            pst.setString(5,e.getStatus());
            pst.setInt(6,e.getId());
            pst.executeUpdate();
            System.out.println("convention updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    
    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM convention WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }

    public void ajouter(Convention e) {
        System.out.println(e);
           try {

            String requete2 = "INSERT INTO convention (`societe`, `adresse`, `email`, `telephone`, `status`) VALUES (?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, e.getSociete());
            pst.setString(2, e.getAdresse());
            pst.setString(3, e.getEmail());
            pst.setString(4, e.getTelephone());    
            pst.setString(5,"convention déposée");


            pst.executeUpdate();
            System.out.println("convention added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }








}
