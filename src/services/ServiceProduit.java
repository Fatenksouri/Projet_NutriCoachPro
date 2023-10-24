/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Produit;
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
public class ServiceProduit {
    
   Connection cnx ;
    
        public ServiceProduit() {
        cnx = DataSource.getInstance().getInstance().getCnx();
}
    
  
     public List <Produit> getAll() {
ObservableList<Produit> myList = FXCollections.observableArrayList();
    String requete=null;
        try {
            requete = "SELECT * FROM produit e inner join category c on e.category_id = c.id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Produit e = new Produit();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString("nom"));
            e.setPrix(rs.getFloat("prix"));
            e.setQuantite(rs.getInt("quantite"));
            e.setDetails(rs.getString("details"));
            e.setImage(rs.getString("image"));
            e.setCategory_type(rs.getString("type"));
            e.setCategory_id(rs.getInt("category_id"));
            e.setRef(rs.getString("référence_produit"));

            System.out.println(e);     
            myList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;
    }
     ///////////////////////////////////////////////////////////  
     public Produit getProductById(int productId) {
    Produit product = null;
    String query = "SELECT * FROM produit e INNER JOIN category c ON e.category_id = c.id WHERE e.id = ?";
    
    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, productId);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        if (rs.next()) {
            product = new Produit();
            product.setId(rs.getInt("id"));
            product.setNom(rs.getString("nom"));
            product.setPrix(rs.getFloat("prix"));
            product.setQuantite(rs.getInt("quantite"));
            product.setDetails(rs.getString("details"));
            product.setImage(rs.getString("image"));
            product.setCategory_type(rs.getString("type"));
            product.setCategory_id(rs.getInt("category_id"));
           product.setRef(rs.getString("référence_produit"));

        }
        
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return product;
}
     ///////////////////////////////////////////////////////////  

    public void modifier(Produit e) {
        System.out.print(e);
try {
            String requete3 = "UPDATE produit SET nom=?,prix=?,quantite=?,details=?,image=?,category_id =?,référence_produit=?  WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, e.getNom());
            pst.setFloat(2, e.getPrix());
            pst.setInt(3,e.getQuantite());
            pst.setString(4, e.getDetails());
            pst.setString(5, e.getImage());    
            pst.setInt(6,e.getCategory_id());
                        pst.setString(7,e.getRef());

            pst.setInt(8,e.getId());
            pst.executeUpdate();
            System.out.println("produit updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }


    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM produit WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }

   
    public Produit getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ajouter(Produit e) {
        System.out.println(e);
           try {

            String requete2 = "INSERT INTO `produit`(`nom`, `details`, `prix`, `quantite`, `image`, `category_id`,référence_produit) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDetails());
            pst.setFloat(3, e.getPrix());
            pst.setInt(4,e.getQuantite());
            pst.setString(5, e.getImage());    
            pst.setInt(6,e.getCategory_id());
            pst.setString(7,e.getRef());

            pst.executeUpdate();
            System.out.println("produit added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }


    public boolean Check(String ref,int quantite) {
try {
    String conventionStatusCheckQuery = "SELECT * FROM produit WHERE référence_produit = ?";
    PreparedStatement conventionStatusCheckStmt = cnx.prepareStatement(conventionStatusCheckQuery);
    conventionStatusCheckStmt.setString(1, ref);

    ResultSet conventionStatusResult = conventionStatusCheckStmt.executeQuery();

    if (conventionStatusResult.next()) {
        String offreInsertQuery = "UPDATE produit SET quantite = quantite + ? WHERE référence_produit = ?";
        PreparedStatement offreInsertStmt = cnx.prepareStatement(offreInsertQuery);
        offreInsertStmt.setInt(1, quantite);
        offreInsertStmt.setString(2, ref);

        int rowsInserted = offreInsertStmt.executeUpdate();
        
        if (rowsInserted > 0) {
            System.out.println("A new record was updated successfully.");
            return true;
        } else {
            System.out.println("No record was updated.");
        }
    } else {
        System.out.println("produit_ref does not exist");
        return false;
    }
} catch (Exception ex) {
    System.out.println(ex.getMessage());
}

           
return false;
    }





}
