/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Objecttype;
import entities.Regime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MyDB;

/**
 *
 * @author Kardo
 */
public class Serviceregime implements Iregime<Regime> {
    
    Connection con; 
    Statement ste;

    public  Serviceregime() {
        con = MyDB.getinstance().getCon();    }
    
    

    @Override
    public void ajouterregime(Regime r) {
        try {
            String req = "INSERT INTO regime( nomreg,descriptionreg,objectifreg,dureereg,datecreationreg,imcMin,imcmax)values(?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            
           
            pre.setString(1,r.getNomreg() );
            pre.setString(2,r.getDescriptionreg() );
            pre.setString(3,r.getObjectifreg().name() );
            pre.setString(4,r.getDureereg() );
            java.util.Date utilDate = r.getDatecreationreg();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pre.setDate(5, sqlDate);
            pre.setDouble(6, r.getImcMin());
            pre.setDouble(7, r.getImcMax());
            
            pre.executeUpdate();
            
            
            
            } catch (SQLException ex) {
                System.out.println(ex);
            
        }
       
    }
     
    @Override
    public Regime getRegimeParId(int id) {
    String sql = "SELECT * FROM regime WHERE idreg = ?";
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            
            Objecttype objectifreg = Objecttype.valueOf(rs.getString("objectifreg"));
            java.sql.Date sqlDate = rs.getDate("datecreationreg");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            return new Regime(
                rs.getInt("idreg"),
             
                rs.getString("nomreg"),
                rs.getString("descriptionreg"),
                objectifreg,
                rs.getString("dureereg"),
                utilDate,
                rs.getDouble("imcMin"),
                rs.getDouble("imcmax")
            );
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null; 
}


    @Override
    public void supprimerregime(Regime r) {
    
    try {
        String req = "DELETE FROM regime WHERE idreg=?";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, r.getIdreg()); 

        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}

    

    @Override
    public void modifierregime(Regime r) {
      try {
        String req = "UPDATE regime SET  nomreg=?, descriptionreg=?, objectifreg=?, dureereg=?, datecreationreg=?,imcMin=? ,imcmax=? WHERE idreg=?";
        PreparedStatement pre = con.prepareStatement(req);

        
        pre.setString(1, r.getNomreg());
        pre.setString(2, r.getDescriptionreg());
        pre.setString(3, r.getObjectifreg().name());
        pre.setString(4, r.getDureereg());
        java.util.Date utilDate = r.getDatecreationreg();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        pre.setDate(5, sqlDate);
        pre.setDouble(6, r.getImcMin());
        pre.setDouble(7, r.getImcMax());
        pre.setInt(8, r.getIdreg()); 

        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }
   

     @Override
     public List<Regime> affihcerregime(){
    
        List<Regime> regimes = new ArrayList<>();
      
        String sql ="select * from regime";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                Objecttype objectifreg=Objecttype.valueOf(rs.getString("objectifreg"));
                Regime r;
                java.sql.Date sqlDate = rs.getDate("datecreationreg");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                r = new Regime(
                        
                         rs.getInt("idreg"),
                        rs.getString("nomreg"),
                        rs.getString("descriptionreg") ,
                        objectifreg ,
                        rs.getString("dureereg") ,
                        utilDate,
                        rs.getDouble("imcMin"),
                        rs.getDouble("imcmax"));
                
                regimes.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return regimes;
    }
    
    @Override
    public List<Regime> getRegimeParobjectifreg(Objecttype objectifreg) {
    List<Regime> regimes = new ArrayList<>();
    String sql = "SELECT * FROM regime WHERE objectifreg = ?";
    
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, objectifreg.name());
        ResultSet rs = pre.executeQuery();
        
        while (rs.next()) {
            
            java.sql.Date sqlDate = rs.getDate("datecreationreg");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            
            Regime regime = new Regime(
                rs.getInt("idreg"),
              
                rs.getString("nomreg"),
                rs.getString("descriptionreg"),
                objectifreg,
                rs.getString("dureereg"),
                utilDate,
                rs.getDouble("imcMin"),
                rs.getDouble("imcmax")); 
            
            
            regimes.add(regime);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return regimes;
}

    
}


