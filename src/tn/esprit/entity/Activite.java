/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Time;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Activite {
    
    private int id_ac;
    private float duree_ac;
    private String nom_ac;
    private int repetition_ac ;
    private String description_ac ;
    private TypeActivite type_ac;
    private Objectif objectif_ac;
    private String imagePath;
    private int rating_ac;

    public Activite(int id_ac, float duree_ac, String nom_ac, int repetition_ac, String description_ac, TypeActivite type_ac, Objectif objectif_ac, String imagePath) {
        this.id_ac = id_ac;
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.repetition_ac = repetition_ac;
        this.description_ac = description_ac;
        this.type_ac = type_ac;
        this.objectif_ac = objectif_ac;
        this.imagePath = imagePath;
    }

    public Activite(int id_ac, float duree_ac, String nom_ac, int repetition_ac, String description_ac, TypeActivite type_ac, Objectif objectif_ac, String imagePath, int rating_ac) {
        this.id_ac = id_ac;
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.repetition_ac = repetition_ac;
        this.description_ac = description_ac;
        this.type_ac = type_ac;
        this.objectif_ac = objectif_ac;
        this.imagePath = imagePath;
        this.rating_ac = rating_ac;
    }
   

    public Activite() {
    }

    public Activite(int id_ac, float duree_ac, String nom_ac, int repetition_ac, String description_ac, TypeActivite type_ac, Objectif obj_ac) {
        this.id_ac = id_ac;
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.repetition_ac = repetition_ac;
        this.description_ac = description_ac;
        this.type_ac = type_ac;
        this.objectif_ac = obj_ac;
  
    }

    public Activite(float duree_ac, String nom_ac, int repetition_ac, String description_ac, TypeActivite type_ac, Objectif obj_ac) {
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.repetition_ac = repetition_ac;
        this.description_ac = description_ac;
        this.type_ac = type_ac;
        this.objectif_ac = obj_ac;
    
    }

    public Activite(float duree_ac, String nom_ac, TypeActivite type_ac) {
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.type_ac = type_ac;
    }

   

   

    public void setId_ac(int id_ac) {
        this.id_ac = id_ac;
    }

    public void setObjectif_ac(Objectif objectif_ac) {
        this.objectif_ac = objectif_ac;
    }

    public int getRating_ac() {
        return rating_ac;
    }

    public void setRating_ac(int rating_ac) {
        this.rating_ac = rating_ac;
    }

    /*public Activite(int id_ac, float duree_ac, String nom_ac, int repetition_ac, String description_ac) {
        this.id_ac = id_ac;
        this.duree_ac = duree_ac;
        this.nom_ac = nom_ac;
        this.repetition_ac = repetition_ac;
        this.description_ac = description_ac;
    }
    
     */
    public int getId_ac() {
        return id_ac;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getDuree_ac() {
        return duree_ac;
    }

    public void setDuree_ac(float duree_ac) {
        this.duree_ac = duree_ac;
    }

    public String getNom_ac() {
        return nom_ac;
    }

    public void setNom_ac(String nom_ac) {
        this.nom_ac = nom_ac;
    }

    public int getRepetition_ac() {
        return repetition_ac;
    }

    public void setRepetition_ac(int repetition_ac) {
        this.repetition_ac = repetition_ac;
    }

    public String getDescription_ac() {
        return description_ac;
    }

    public void setDescription_ac(String description_ac) {
        this.description_ac = description_ac;
    }

    public TypeActivite getType_ac() {
        return type_ac;
    }

    public void setType_ac(TypeActivite type_ac) {
        this.type_ac = type_ac;
    }

    public Objectif getObj_ac() {
        return objectif_ac;
    }

    public void setObj_ac(Objectif obj_ac) {
        this.objectif_ac = obj_ac;
    }

    
  
    
}
