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
public class Regime {
    
    private int idreg;
    private String nomreg;
    private String descriptionreg;
    private Objecttype objectifreg;
    private String dureereg;
    private Date datecreationreg;
    
    private double imcMin;
    private double imcMax;
   


    
    public Regime() {
    }

    public Regime( String nomreg, String descriptionreg, Objecttype objectifreg, String dureereg, Date datecreationreg, double imcMin,double imcMax) {
       
        this.nomreg = nomreg;
        this.descriptionreg = descriptionreg;
        this.objectifreg = objectifreg;
        this.dureereg = dureereg;
        this.datecreationreg = datecreationreg;
        this.imcMax=imcMax;
        this.imcMin=imcMin;
    }
  
    public Regime( int idreg ,String nomreg,
            String descriptionreg,Objecttype objectifreg,String dureereg,  Date datecreationreg,double imcMin,double imcMax) {
        
        this.idreg = idreg;
        this.nomreg = nomreg;
        this.descriptionreg = descriptionreg;
        this.objectifreg = objectifreg;
        this.dureereg = dureereg;
        this.datecreationreg = datecreationreg;
        this.imcMax=imcMax;
        this.imcMin=imcMin;
       
    }

    public double getImcMin() {
        return imcMin;
    }

    public void setImcMin(double imcMin) {
        this.imcMin = imcMin;
    }

    public double getImcMax() {
        return imcMax;
    }

    public void setImcMax(double imcMax) {
        this.imcMax = imcMax;
    }

    
    

    public int getIdreg() {
        return idreg;
    }

  
   

    public String getNomreg() {
        return nomreg;
    }

    public void setNomreg(String nomreg) {
        this.nomreg = nomreg;
    }

    public String getDescriptionreg() {
        return descriptionreg;
    }

    public void setDescriptionreg(String descriptionreg) {
        this.descriptionreg = descriptionreg;
    }

    public Objecttype getObjectifreg() {
        return objectifreg;
    }

    public void setObjectifreg(Objecttype objectif) {
        this.objectifreg = objectif;
    }

    public String getDureereg() {
        return dureereg;
    }

    public void setDureereg(String dureereg) {
        this.dureereg= dureereg;
    }

    
    public Date getDatecreationreg() {
        return datecreationreg;
    }

    public void setDateCreationreg(Date datecreationreg) {
        this.datecreationreg = datecreationreg;
    }

    @Override
    public String toString() {
        return "Regime{" + "idreg=" + idreg + ", nomreg=" + nomreg + ", descriptionreg=" + descriptionreg + ", objectifreg=" + objectifreg + ", dureereg=" + dureereg + ", datecreationreg=" + datecreationreg + ", imcMin=" + imcMin + ", imcMax=" + imcMax + '}';
    }


  
}


