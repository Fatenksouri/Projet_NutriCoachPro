/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author omnia
 */
public class Offre {
    
    private int id ;
    private String Nom_du_soin;
    private Categorie categorie;
    private float prix;
    private Date  date_debut;
    private Date  date_fin;
    private String image;
    private int convention_id;
    private String convention_sosciete;
  

    public Offre(int id, String Nom_du_soin, Categorie categorie, float prix, Date date_debut, Date date_fin, String image, int convention_id) {
        this.id = id;
        this.Nom_du_soin = Nom_du_soin;
        this.categorie = categorie;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.convention_id = convention_id;
    }

    public Offre(String Nom_du_soin, Categorie categorie, float prix, Date date_debut, Date date_fin, String image, int convention_id) {
        this.Nom_du_soin = Nom_du_soin;
        this.categorie = categorie;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.image = image;
        this.convention_id = convention_id;
    }

    public Offre() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom_du_soin() {
        return Nom_du_soin;
    }

    public void setNom_du_soin(String Nom_du_soin) {
        this.Nom_du_soin = Nom_du_soin;
    }

    public String  getCategorie() {
        return categorie.name();
    }

    public void setCategorie(String  categorie) {
        this.categorie = Categorie.valueOf(categorie);
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getConvention_id() {
        return convention_id;
    }

    public void setConvention_id(int convention_id) {
        this.convention_id = convention_id;
    }

    public String getConvention_sosciete() {
        return convention_sosciete;
    }

    public void setConvention_sosciete(String convention_sosciete) {
        this.convention_sosciete = convention_sosciete;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", Nom_du_soin=" + Nom_du_soin + ", categorie=" + categorie + ", prix=" + prix + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", image=" + image + ", convention_id=" + convention_id + ", convention_sosciete=" + convention_sosciete + '}';
    }

   




  
  


    
    
    
}
