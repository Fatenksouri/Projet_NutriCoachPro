 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import java.sql.Connection;
import tn.esprit.entity.Activite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.Objectif;
import tn.esprit.entity.TypeActivite;
import tn.esprit.tools.MyBD;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ServiceActivite implements IActivite<Activite>{
    Connection con; 
    Statement ste;

    public ServiceActivite() {
        con = MyBD.getinstance().getCon();    }
    
    

   
    @Override
    public void ajouter(Activite A) {
        try {
            String req = "INSERT INTO Activite (duree_ac, nom_ac, repetition_ac, description_ac, type_ac, objectif_ac, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setFloat(1, A.getDuree_ac());
            pre.setString(2, A.getNom_ac());
            pre.setInt(3, A.getRepetition_ac());
            pre.setString(4, A.getDescription_ac());
            pre.setString(5, A.getType_ac().name());
            pre.setString(6, A.getObj_ac().name());
            pre.setString(7, A.getImagePath());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    public void supprimer(int idActivite) {
    try {
        String req = "DELETE FROM Activite WHERE id_ac = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idActivite);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}

    
    @Override
    public void supprimer(String nomActivite) {
    try {
        String req = "DELETE FROM Activite WHERE nom_ac = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, nomActivite);
        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }

    @Override
    public void modifier(Activite A) {
        try {
            String req = "UPDATE Activite SET duree_ac=?, nom_ac=?, repetition_ac=?, description_ac=?, type_ac=?, objectif_ac=? WHERE id_ac=?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setFloat(1, A.getDuree_ac());
            pre.setString(2, A.getNom_ac());
            pre.setInt(3, A.getRepetition_ac());
            pre.setString(4, A.getDescription_ac());
            pre.setString(5, A.getType_ac().name());
            pre.setString(6, A.getObj_ac().name());
            pre.setInt(7, A.getId_ac());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Activite> affihcer() {
  
        List<Activite> activites = new ArrayList<>();
        String sql = "SELECT * FROM Activite";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Activite A = new Activite(
                    rs.getInt("id_ac"),
                    rs.getFloat("duree_ac"),
                    rs.getString("nom_ac"),
                    rs.getInt("repetition_ac"),
                    rs.getString("description_ac"),
                    TypeActivite.valueOf(rs.getString("type_ac")), 
                    Objectif.valueOf(rs.getString("objectif_ac")) 
                
                ); 
                
                activites.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
        
    }
    
    public List<Activite> afficherParType(TypeActivite typeActivite) {
        List<Activite> activites = new ArrayList<>();
        String sql = "SELECT * FROM Activite WHERE type_ac = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, typeActivite.name()); // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Activite A = new Activite(
                    rs.getInt("id_ac"),
                    rs.getFloat("duree_ac"),
                    rs.getString("nom_ac"),
                    rs.getInt("repetition_ac"),
                    rs.getString("description_ac"),
                  TypeActivite.valueOf(rs.getString("type_ac")), // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
                    Objectif.valueOf(rs.getString("objectif_ac")) // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
                
                );// Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
                
                activites.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }
        return activites;
    }
   
    public List<Activite> afficherParTypeEtObjectif(TypeActivite typeActivite, Objectif objectif) {
        List<Activite> activites = new ArrayList<>();
        String sql = "SELECT * FROM Activite WHERE type_ac = ? AND objectif_ac = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, typeActivite.name()); // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
            pre.setString(2, objectif.name()); // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Activite A = new Activite(
                    rs.getInt("id_ac"),
                    rs.getFloat("duree_ac"),
                    rs.getString("nom_ac"),
                    rs.getInt("repetition_ac"),
                    rs.getString("description_ac"),
                    TypeActivite.valueOf(rs.getString("type_ac")), // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
                    Objectif.valueOf(rs.getString("objectif_ac")) // Assurez-vous que la colonne est une chaîne représentant une valeur d'énumération
                
                );
                activites.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }

    @Override
    public Activite getById(int id) {
    try {
        String sql = "SELECT * FROM Activite WHERE id_ac = ?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);

        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            Activite A = new Activite(
                rs.getInt("id_ac"),
                rs.getFloat("duree_ac"),
                rs.getString("nom_ac"),
                rs.getInt("repetition_ac"),
                rs.getString("description_ac"),
                TypeActivite.valueOf(rs.getString("type_ac")),
                Objectif.valueOf(rs.getString("objectif_ac"))
            );
            return A;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null; // Retourne null si l'activité n'est pas trouvée
}


    public void updateRating(Activite A) {
        try {
         
            // La requête SQL pour mettre à jour la note de l'activité
            String sql = "UPDATE activite SET rating_ac = ? WHERE id_ac = ?";

           PreparedStatement pre = con.prepareStatement(sql);
            pre.setFloat(1, A.getRating_ac());
            pre.setInt(2, A.getId_ac());
           // Assurez-vous d'avoir une méthode getId() dans votre classe Activite

            pre.executeUpdate();

           
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de base de données
        }
    }
}
    


    
 
    
    

////////////////////////////////////////:requette///////////////////////////////////////////////////
    

    






    
    
