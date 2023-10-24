/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ksouri
 */
public class Panier {
    
            private int id ;
            private Produit id_produit;
            private int id_user;
            private int quantite;
            private float prix_total;

    public Panier(Produit id_produit, int id_user) {
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public Panier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getId_produit() {
        return id_produit;
    }

    public void setId_produit(Produit id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", id_produit=" + id_produit + ", id_user=" + id_user + ", quantite=" + quantite + ", prix_total=" + prix_total + '}';
    }
            
            
            
            
    
}
