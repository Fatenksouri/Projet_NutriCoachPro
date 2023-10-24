/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import javax.management.relation.Role;

/**
 *
 * @author jaafr
 */
public class user {
       private int userId ;
   private String firstName ; 
   private String lasttName ; 
   private String dateOfBirth ; 
   private String email ; 
   private int phoneNumber ;
   private String userName ; 
   private String password ; 
   private String diplome  ; 
   private String specialite  ; 
   private Role  role ;

    public user() {
    }

   
    public user(int userId, String firstName, String lasttName, String dateOfBirth, String email, int phoneNumber, String userNme, String password, String diplome, String specialte, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.diplome = diplome;
        this.specialite = specialite;
        this.role = role;
    }

    public user(String firstName, String lasttName, String dateOfBirth, String email, int phoneNumber, String userNme, String password, String diplome, String specialte, Role role) {
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.diplome = diplome;
        this.specialite = specialite;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
