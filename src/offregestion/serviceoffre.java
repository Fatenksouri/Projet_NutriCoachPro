
 //* To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
 //*/
package offregestion;
//import static com.sun.deploy.config.JREInfo.getAll;
import offregestion.typeoffre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaafr
 */


 public class serviceoffre implements Iserviceoffre<offre> {
       typeoffre to;
   // ServiceTypeOffre  to = new ServiceTypeOffre();
   // User u;
    //UserService us = new UserService();
Connection cnx ;
Statement ste;
public serviceoffre(){
    
    cnx= database.getInstance().getConnection();
}
  
    public void ajouter(offre o) {
     try {
            String req = "insert into resoffres(nom_offre,date_offre,heure_debut,heure_fin,prix,description,type)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(req);
        ste.setString(1, o.getNom_offre());
        ste.setString(2, o.getDate_offre());
        ste.setString(3, o.getHeure_debut());
        ste.setString(4, o.getHeure_fin());
        ste.setDouble(5, o.getPrix());
        ste.setString(6, o.getDescription());

         // Convertissez l'énumération en une chaîne de caractères pour l'insérer dans la base de données
        ste.setString(7, o.getType().name());         
        
            ste.executeUpdate();
            System.out.println("offre ajoutée");
        } catch (SQLException ex) {
         
            System.out.println(ex.getMessage());
        }
}
    public List<offre> getAll() {
    List<offre> listeOffres = new ArrayList<>();

    // Écrivez la requête SQL pour sélectionner toutes les offres
    String req = "SELECT * FROM resoffres";

    try {
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet rs = ste.executeQuery();

        while (rs.next()) {
            // Créez un nouvel objet Offre pour chaque enregistrement dans la base de données
            offre offre = new offre();
            offre.setId_offre(rs.getInt("id_offre"));
            offre.setNom_offre(rs.getString("nom_offre"));
            offre.setDate_offre(rs.getString("date_offre"));
            offre.setHeure_debut(rs.getString("heure_debut"));
            offre.setHeure_fin(rs.getString("heure_fin"));
            offre.setType(typeoffre.valueOf(rs.getString("type"))); // Vous devez avoir une méthode pour convertir une chaîne en enum TypeOffre
            offre.setPrix(rs.getInt("prix"));
            offre.setDescription(rs.getString("description"));

            // Ajoutez l'offre à la liste
            listeOffres.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return listeOffres;
}

    public void supprimer(offre o) {
        String sql = "delete from resoffres where id_offre=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, o.getId_offre());
            ste.executeUpdate();

            System.out.println("offre supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

    }

    
   /**
 *
 * @author jaafr
 */

   @Override
public void modifier(offre o) {
    String sql = "UPDATE resoffres SET nom_offre=?, date_offre=?, heure_debut=?, heure_fin=?, prix=?, description=?, type=? WHERE id_offre=?";
    
    try {
        // Préparez la requête
        PreparedStatement ste = cnx.prepareStatement(sql);
        
        // Définissez les valeurs des paramètres dans la requête
        ste.setString(1, o.getNom_offre());
        ste.setString(2, o.getDate_offre());
        ste.setString(3, o.getHeure_debut());
        ste.setString(4, o.getHeure_fin());
        ste.setDouble(5, o.getPrix());
        ste.setString(6, o.getDescription());
        ste.setString(7, o.getType().name());
        ste.setInt(8, o.getId_offre()); // Assurez-vous que la classe offre a une méthode getId_offre() pour récupérer l'ID de l'offre

        // Exécutez la mise à jour
        int rowsAffected = ste.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Offre mise à jour avec succès.");
        } else {
            System.out.println("Aucune offre n'a été mise à jour.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

/**
 *
 * @author jaafr
 */
    
    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
 *
 * @author jaafr
 */
    @Override
    public List<offre> getAll(offre o) {
        List<offre> listeOffres = new ArrayList<>();

    // Écrivez la requête SQL pour sélectionner toutes les offres
    String req = "SELECT * FROM resoffres";

    try {
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet rs = ste.executeQuery(req);

        while (rs.next()) {
            // Créez un nouvel objet Offre pour chaque enregistrement dans la base de données
            offre offre = new offre();
            offre.setId_offre(rs.getInt("id_offre"));
            offre.setNom_offre(rs.getString("nom_offre"));
            offre.setDate_offre(rs.getString("date_offre"));
            offre.setHeure_debut(rs.getString("heure_debut"));
            offre.setHeure_fin(rs.getString("heure_fin"));
            offre.setType(typeoffre.valueOf(rs.getString("type"))); // Vous devez avoir une méthode pour convertir une chaîne en enum TypeOffre
            offre.setPrix(rs.getInt("prix"));
            offre.setDescription(rs.getString("description"));

            // Ajoutez l'offre à la liste
            listeOffres.add(offre);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return listeOffres;
    }

    
public List<offre> chercherParType(typeoffre type) {
    List<offre> offresFiltrees = new ArrayList<>();
    
    for (offre offre : getAll()) {
        if (offre.getType() == type) {
            offresFiltrees.add(offre);
        }
    }
    
    return offresFiltrees;
}

  public int getNombreOffres() {
    int nombreOffres = 0;
    String req = "SELECT COUNT(*) as count FROM resoffres";
    
    try {
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        
        if (rs.next()) {
            nombreOffres = rs.getInt("count");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return nombreOffres;
}
public List<offre> getOffresByType(String type) {
    List<offre> offresByType = new ArrayList<>();

    // Supposez que vous ayez une liste d'offres stockée dans une variable appelée "allOffres"
    // Vous devez itérer sur la liste complète et ajouter les offres correspondant au type spécifié
    // à la liste "offresByType".

     for (offre offre : getAll()) {
        if (offre.getType() == typeoffre.valueOf(type)) {
            // Si le type de l'offre correspond au type spécifié, l'ajouter à la liste.
            offresByType.add(offre);
        }
    }

    return offresByType;
}
 }  
 

