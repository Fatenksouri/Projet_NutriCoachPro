/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

/**
 *
 * @author jaafr
 */
public class reservation {
   private int id_res ;
   private user user  ; 
   private String userName ;
   private int id_offre ;
   private int userId ; 
   private String date_offre ;
   private String nom_offre ;
   private double prixT ;
   private typeoffre type ;
           
    public reservation() {
    }

    public reservation(int id_res, user user, String userName, int id_offre, int userId, String date_offre, String nom_offre, double prixT) {
        this.id_res = id_res;
        this.user = user;
        this.userName = userName;
        this.id_offre = id_offre;
        this.userId = userId;
        this.date_offre = date_offre;
        this.nom_offre = nom_offre;
        this.prixT = prixT;
    }

    public reservation(int id_res, String userName, int id_offre, int userId, String date_offre) {
        this.id_res = id_res;
        this.userName = userName;
        this.id_offre = id_offre;
        this.userId = userId;
        this.date_offre = date_offre;
    }

    public reservation(int id_res, user user, String userName, int id_offre, int userId, String date_offre) {
        this.id_res = id_res;
        this.user = user;
        this.userName = userName;
        this.id_offre = id_offre;
        this.userId = userId;
        this.date_offre = date_offre;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate_offre() {
        return date_offre;
    }

    public void setDate_offre(String date_offre) {
        this.date_offre = date_offre;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public double getPrixT() {
        return prixT;
    }

    public void setPrixT(double prixT) {
        this.prixT = prixT;
    }

    public typeoffre getType() {
        return type;
    }

    public void setType(typeoffre type) {
        this.type = type;
    }
   

   
}
