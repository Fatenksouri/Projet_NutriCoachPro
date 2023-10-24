/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Offre;
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
public class ServiceOffre {
      Connection cnx ;
    
        public ServiceOffre() {
        cnx = DataSourceConvention.getInstance().getInstance().getCnx();
}
        
        
 public List <Offre> getAll() {
ObservableList<Offre> myList = FXCollections.observableArrayList();
    String requete=null;
        try {
            requete = "SELECT * FROM offre e inner join convention c on c.id = e.convention_id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Offre e = new Offre();
            e.setId(rs.getInt(1));
            e.setNom_du_soin(rs.getString("Nom_du_soin"));
            e.setCategorie(rs.getString("categorie"));
            e.setPrix(rs.getFloat("prix"));
            e.setDate_debut(rs.getDate("date_debut"));
            e.setDate_fin(rs.getDate("date_fin"));
            e.setImage(rs.getString("image"));
            e.setConvention_id(rs.getInt("convention_id"));
            e.setConvention_sosciete(rs.getString("societe"));

            myList.add(e);
            System.out.println(e);     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;
    }
     ///////////////////////////////////////////////////////////
       
    public void modifier(Offre e) {
        System.out.print(e);
try {
            String requete3 = "UPDATE offre SET Nom_du_soin=? ,categorie=?,prix=?,date_debut=?,date_fin=? ,image=?,convention_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, e.getNom_du_soin());
            pst.setString(2, e.getCategorie());
            pst.setFloat(3,e.getPrix());
            pst.setDate(4,e.getDate_debut());
            pst.setDate(5,e.getDate_fin());
            pst.setString(6, e.getImage());
            pst.setInt(7,e.getConvention_id());       
            pst.setInt(8,e.getId());
            pst.executeUpdate();
            System.out.println("offre updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }


    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM offre WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }

   
    public Offre getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ajouter(Offre e) {
        System.out.println(e);
try {
    String conventionStatusCheckQuery = "SELECT * FROM convention WHERE id = ? AND status = 'convention acceptée'";
    PreparedStatement conventionStatusCheckStmt = cnx.prepareStatement(conventionStatusCheckQuery);
    conventionStatusCheckStmt.setInt(1, e.getConvention_id());

    ResultSet conventionStatusResult = conventionStatusCheckStmt.executeQuery();

    if (conventionStatusResult.next()) {
        String offreInsertQuery = "INSERT INTO `offre`(`Nom_du_soin`, `categorie`, `prix`, `date_debut`, `date_fin`, `image`, `convention_id`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement offreInsertStmt = cnx.prepareStatement(offreInsertQuery);
        offreInsertStmt.setString(1, e.getNom_du_soin());
        offreInsertStmt.setString(2, e.getCategorie());
        offreInsertStmt.setFloat(3, e.getPrix());
        offreInsertStmt.setDate(4, e.getDate_debut());
        offreInsertStmt.setDate(5, e.getDate_fin());
        offreInsertStmt.setString(6, e.getImage());
        offreInsertStmt.setInt(7, e.getConvention_id());

        int rowsInserted = offreInsertStmt.executeUpdate();
        
        if (rowsInserted > 0) {
            System.out.println("A new record was inserted successfully.");
        } else {
            System.out.println("No record was inserted.");
        }
    } else {
        System.out.println("Convention does not exist or its status is not true.");
    }
} catch (Exception ex) {
    System.out.println(ex.getMessage());
}

           

    }
    
}