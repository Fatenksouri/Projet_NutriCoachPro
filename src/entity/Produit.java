/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
/**
 *
 * @author ksouri
 */
public class Produit {
    private int id ;
    private String nom;
    private String details;
    private float prix;
    private int quantite;
     private String ref;
    private String image ;    
    private int category_id;
    private String category_type;



    public Produit(int id, String nom, String details, float prix, int quantite, String image, int category_id,String ref) {
        this.id = id;
        this.nom = nom;
        this.details = details;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.category_id = category_id;
        this.ref = ref;
    }

    public Produit() {
    }

    public Produit(String nom, String details, float prix, int quantite, String image, int category_id,String ref) {
        this.nom = nom;
        this.details = details;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.category_id = category_id;
        this.ref = ref;
    }

    public Produit(String  nom, String details, float prix, String image) {
       this.nom = nom;
        this.details = details;
        this.prix = prix;
        this.image = image;    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", details=" + details + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + ", category_id=" + category_id + ", category_type=" + category_type + '}';
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Produit(int id) {
        this.id = id;
    }
    
    
    


   



}
