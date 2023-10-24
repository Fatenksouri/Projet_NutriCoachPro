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
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaafr
 */
public class offre {
     private  int id_offre;
    private  String nom_offre;
    private String date_offre  ;
    private String heure_debut  ;
    private String heure_fin ;
    private  int prix;
    private  String description;
    private typeoffre type;

    public offre() {
    }

    public offre(int id_offre, String nom_offre, String date_offre, String heure_debut, String heure_fin, int prix, String description, typeoffre type) {
        this.id_offre = id_offre;
        this.nom_offre = nom_offre;
        this.date_offre = date_offre;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }

    public offre(String nom_offre, String date_offre, String heure_debut, String heure_fin, int prix, String description, typeoffre type) {
        this.nom_offre = nom_offre;
        this.date_offre = date_offre;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getNom_offre() {
        return nom_offre;
    }

    public void setNom_offre(String nom_offre) {
        this.nom_offre = nom_offre;
    }

    public String getDate_offre() {
        return date_offre;
    }

    public void setDate_offre(String date_offre) {
        this.date_offre = date_offre;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public typeoffre getType() {
        return type;
    }

    public void setType(typeoffre type) {
        this.type = type;
    }

   
    @Override
    public String toString() {
        return "ID : " + id_offre + "\n" +
               "Nom : " + nom_offre + "\n" +
               "Date : " + date_offre + "\n" +
               "Heure de début : " + heure_debut + "\n" +
               "Heure de fin : " + heure_fin + "\n" +
               "Type : " + type + "\n" +
               "Prix : " + prix + "\n" +
               "Description : " + description;
    }

    
}


