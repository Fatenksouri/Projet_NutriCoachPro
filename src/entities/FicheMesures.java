/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.Date;

/**
 *
 * @author Kardo
 */
   public class FicheMesures {
     
    private int idfich ;
    String pseudofich;
    private double taille ,
            poids ,tourDeTaille ,
            tourDeHanches ,
            tourDePoitrine 
            ,masseGrasse ,
            masseMusculaire;
    private Typeactv niveauActivite;
    private Date datecreationfich;
    //private User user;
    
   
    private Double IMC=0.0;
 





    public FicheMesures() {
    }

    public FicheMesures(int idfich,String pseudofich, double taille, double poids, double tourDeTaille, double tourDeHanches, double tourDePoitrine, double masseGrasse, double masseMusculaire, Typeactv niveauActivite, Date datecreationfich, Double IMC )
    {
        this.idfich = idfich;
        this.pseudofich=pseudofich;
        this.taille = taille;
        this.poids = poids;
        this.tourDeTaille = tourDeTaille;
        this.tourDeHanches = tourDeHanches;
        this.tourDePoitrine = tourDePoitrine;
        this.masseGrasse = masseGrasse;
        this.masseMusculaire = masseMusculaire;
        this.niveauActivite = niveauActivite;
        this.datecreationfich = datecreationfich;
        this.IMC = IMC;
    }

   public FicheMesures(String pseudofich,double taille, double poids, double tourDeTaille, double tourDeHanches, double tourDePoitrine, double masseGrasse, double masseMusculaire, Typeactv niveauActivite, Date datecreationfich, Double IMC)
    {   this.pseudofich=pseudofich;
        this.taille = taille;
        this.poids = poids;
        this.tourDeTaille = tourDeTaille;
        this.tourDeHanches = tourDeHanches;
        this.tourDePoitrine = tourDePoitrine;
        this.masseGrasse = masseGrasse;
        this.masseMusculaire = masseMusculaire;
        this.niveauActivite = niveauActivite;
        this.datecreationfich = datecreationfich;
        this.IMC = IMC;
    }

    public String getPseudofich() {
        return pseudofich;
    }

    public void setPseudofich(String pseudofich) {
        this.pseudofich = pseudofich;
    }

   /* public void setUser(User user) {
        this.user = user;
    }

  
    
    public User getUser() {
        return user;
    }
*/
    public Double getIMC() {
       return IMC;
    }
    public void setIMC(Double IMC) {
      this.IMC = IMC;
    }
   

    public int getIdfich() {
        return idfich;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTourDeTaille() {
        return tourDeTaille;
    }

    public void setTourDeTaille(double tourDeTaille) {
        this.tourDeTaille = tourDeTaille;
    }

    public double getTourDeHanches() {
        return tourDeHanches;
    }

    public void setTourDeHanches(double tourDeHanches) {
        this.tourDeHanches = tourDeHanches;
    }

    public double getTourDePoitrine() {
        return tourDePoitrine;
    }

    public void setTourDePoitrine(double tourDePoitrine) {
        this.tourDePoitrine = tourDePoitrine;
    }

    public double getMasseGrasse() {
        return masseGrasse;
    }

    public void setMasseGrasse(double masseGrasse) {
        this.masseGrasse = masseGrasse;
    }

    public double getMasseMusculaire() {
        return masseMusculaire;
    }

    public void setMasseMusculaire(double masseMusculaire) {
        this.masseMusculaire = masseMusculaire;
    }

    

    public Typeactv getNiveauActivite() {
        return niveauActivite;
    }

    public void setNiveauActivite(Typeactv niveauActivite) {
        this.niveauActivite = niveauActivite;
    }

    public Date getDatecreationfich() {
        return datecreationfich;
    }

    public void setDatecreationfich(Date datecreationreg) {
        this.datecreationfich = datecreationreg;
    }

    

    @Override
    public String toString() {
        return "FicheMesures{" + "idfich=" + idfich + ", taille=" + taille + ", poids=" + poids + ", tourDeTaille=" + tourDeTaille + ", tourDeHanches=" + tourDeHanches + ", tourDePoitrine=" + tourDePoitrine + ", masseGrasse=" + masseGrasse + ", masseMusculaire=" + masseMusculaire + ", niveauActivite=" + niveauActivite + ", datecreationfich=" + datecreationfich //+ ", IMC=" + IMC 
                + '}';
    }
    
    

}
  


