/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Panier;
import entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author ksouri
 */
public class ServicePanier {
    
   Connection cnx ;
    
        public ServicePanier() {
        cnx = DataSource.getInstance().getInstance().getCnx();
}
    
  

public List<Panier> getPaniersByUserId(int userId) {
    ObservableList<Panier> paniers = FXCollections.observableArrayList();

    String query = "SELECT\n" +
"    *, \n" +
"    COUNT(*) AS total_quantity,\n" +
"    SUM(c.prix) AS total_price\n" +
"FROM panier e\n" +
"INNER JOIN produit c ON e.id_produit = c.id\n" +
"WHERE e.id_user = ?\n" +
"GROUP BY c.id;";
    try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setId(rs.getInt("id"));
            panier.setId_produit(new Produit(rs.getString("nom"), rs.getString("details"), rs.getFloat("prix"), rs.getString("image")));
            panier.setId_user(rs.getInt("id_user"));
            panier.setQuantite(rs.getInt("total_quantity"));
            panier.setPrix_total(rs.getFloat("total_price"));
            paniers.add(panier);
        }
        
        rs.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return paniers;
}


     ///////////////////////////////////////////////////////////  




    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM panier WHERE id=?";
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

    public void ajouter(Panier e) {
        System.out.println(e);
           try {

            String requete2 = "INSERT INTO `panier`(`id_produit`, `id_user`) VALUES (?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, e.getId_produit().getId());
            pst.setInt(2, e.getId_user());
            pst.executeUpdate();
            System.out.println("panier added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }

}
