/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import tn.esprit.entity.Activite;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Planning {
    private int id_pl;
    private String nom_pl;
    private float dure_pl;
    private int nb_like;
    private int nb_dislike;

    public int getNb_like() {
        return nb_like;
    }

    public void setNb_like(int nb_like) {
        this.nb_like = nb_like;
    }

    public int getNb_dislike() {
        return nb_dislike;
    }

    public void setNb_dislike(int nb_dislike) {
        this.nb_dislike = nb_dislike;
    }

    public Planning(int id_pl, String nom_pl, float dure_pl, int nb_like, int nb_dislike) {
        this.id_pl = id_pl;
        this.nom_pl = nom_pl;
        this.dure_pl = dure_pl;
        this.nb_like = nb_like;
        this.nb_dislike = nb_dislike;
    }

    public Planning() {
    }
    
    public Planning( String nom_pl, float dure_pl) {
       
        this.nom_pl = nom_pl;
        this.dure_pl = dure_pl;
       
    }
    
    public Planning(int id_pl, String nom_pl, float dure_pl) {
        this.id_pl = id_pl;
        this.nom_pl = nom_pl;
        this.dure_pl = dure_pl;
       
    }

    public int getId_pl() {
        return id_pl;
    }

    public void setId_pl(int id_pl) {
        this.id_pl = id_pl;
    }

   


    public String getNom_pl() {
        return nom_pl;
    }

    public void setNom_pl(String nom_pl) {
        this.nom_pl = nom_pl;
    }

    public float getDure_pl() {
        return dure_pl;
    }

    public void setDure_pl(float dure_pl) {
        this.dure_pl = dure_pl;
    }

     
}
