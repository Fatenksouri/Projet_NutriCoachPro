/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import offregestion.reservation;

/**
 *
 * @author jaafr
 * @param <<error>>
 *
 */

    public class servicereservation {
    Connection cnx ;
     Statement ste;

  public servicereservation(){
    this.cnx= database.getInstance().getConnection();
}  
  public void ajouter(reservation r) {
     try {
            String sql = "insert into reservation(id_res,userName,id_offre,userId,date_offre)"
                    + " values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, r.getId_res());
            ste.setString(2, r.getUserName());
            ste.setInt(3, r.getId_offre());
            ste.setInt(4, r.getUserId());
            ste.setString(5, r.getDate_offre());
          
        
           
           
            ste.executeUpdate();
            System.out.println("reservation ajoutée");
        } catch (SQLException ex) {
         
            System.out.println(ex.getMessage());
        }
}
 
  public void supprimer_res(reservation r) {
           String sql = "delete from reservation where id_res=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, r.getId_res());
            ste.executeUpdate();

            System.out.println("resesrvation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    public void modifier(reservation r) {
                try {
              
String sql = "UPDATE reservation SET userName=?, id_offre=?, userId=?, date_offre=? WHERE id_res=?";

             PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, r.getUserName());
            ste.setInt(2, r.getId_offre());
            ste.setInt(3, r.getUserId());
            ste.setString(4, r.getDate_offre());
            ste.setInt(5, r.getId_res());

             int rowsAffected = ste.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("reservation mise à jour avec succès.");
        } else {
            System.out.println("Aucune reservation n'a été mise à jour.");
        }
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }}
    /*    public reservation getreservationParId(int id) {
    String sql = "SELECT * FROM reservation WHERE id_res = ?";
    try {
        PreparedStatement pre = cnx.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            int id_res = rs.getInt("id_res");
           int id_user = rs.getInt("userId");
           int id_offre= rs.getInt("id_offre");
            
            // Create and return a restaurant object with retrieved data
            return new reservation(id_res, id_user, id_offre);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}*/
        

    public List<reservation> getAll() {
    List<reservation> listeReservations = new ArrayList<>();

    try {
        String sql = "SELECT * FROM reservation";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ResultSet rs = ste.executeQuery();

        while (rs.next()) {
            reservation reservation = new reservation();
            reservation.setId_res(rs.getInt("id_res"));
            reservation.setUserName(rs.getString("userName"));
            reservation.setId_offre(rs.getInt("id_offre"));
            reservation.setUserId(rs.getInt("userId"));
            reservation.setDate_offre(rs.getString("date_offre"));

            listeReservations.add(reservation);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des réservations : " + ex.getMessage());
    }

    return listeReservations;
}
 public List<reservation> rechercherParUserId(int userId) {
    List<reservation> reservationsByUserId = new ArrayList<>();

    try {
        String sql = "SELECT * FROM reservation WHERE userId = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, userId);
        ResultSet rs = ste.executeQuery();

        while (rs.next()) {
            reservation reservation = new reservation();
            reservation.setId_res(rs.getInt("id_res"));
            reservation.setUserName(rs.getString("userName"));
            reservation.setId_offre(rs.getInt("id_offre"));
            reservation.setUserId(rs.getInt("userId"));
            reservation.setDate_offre(rs.getString("date_offre"));
           
            // Chargez l'utilisateur associé à cette réservation
            user user = new user();
            user.setUserId(userId); // Vous pouvez également charger l'utilisateur en fonction de l'ID de l'utilisateur associé à la réservation
            reservation.setUser(user);

            reservationsByUserId.add(reservation);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la recherche des réservations par userId : " + ex.getMessage());
    }

    return reservationsByUserId;
}



 



}
