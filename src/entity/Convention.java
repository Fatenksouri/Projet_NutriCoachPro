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
public class Convention {
  
            private int id ;
            private String societe ;
            private String adresse ;
            private String email;
            private String telephone;
            private String status ;

    public Convention(String societe, String adresse, String email, String telephone) {
        this.societe = societe;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }

    public Convention(int id, String societe, String adresse, String email, String telephone, String status) {
        this.id = id;
        this.societe = societe;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
    public Convention() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }



    @Override
    public String toString() {
        return societe;
    }
            
            
            
            




}
