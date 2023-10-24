/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.FicheMesures;
import entities.Typeactv;
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

    public class Servicefichemesures implements Ifichemesures<FicheMesures> {
    
    Connection con; 
    Statement ste;
    

    public Servicefichemesures () {
        con = MyDB.getinstance().getCon();    }
    
    

    @Override
    public void ajouterfichmes(FicheMesures f) {
        try {
           String req = "INSERT INTO fichemesures(pseudofich,taille,poids,tourDeTaille,tourDeHanches,tourDePoitrine,masseGrasse,masseMusculaire,niveauActivite,datecreationfich,IMC)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, f.getPseudofich());
            pre.setDouble(2, f.getTaille());
            pre.setDouble(3, f.getPoids());
            pre.setDouble(4, f.getTourDeTaille()); 
            pre.setDouble(5, f.getTourDeHanches());
            pre.setDouble(6, f.getTourDePoitrine());
            pre.setDouble(7, f.getMasseGrasse());
            pre.setDouble(8, f.getMasseMusculaire()); 
            pre.setString(9,f.getNiveauActivite().name() );
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pre.setDate(10, sqlDate);
            pre.setDouble(11, f.getIMC());
            pre.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
             }
    
    @Override
    public FicheMesures getFicheMesuresParpseudo(String pseudofich) {
    FicheMesures ficheMesures = null;
    String sql = "SELECT * FROM fichemesures WHERE pseudofich = ?";
    
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, pseudofich);
        ResultSet rs = pre.executeQuery();
        
        if (rs.next()) {
            Typeactv niveauActivite = Typeactv.valueOf(rs.getString("niveauActivite"));
            java.sql.Date sqlDate = rs.getDate("datecreationfich");
            java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
            
            ficheMesures = new FicheMesures(
                rs.getInt("idfich"),    
                rs.getString("pseudofich"),
                rs.getDouble("taille"),
                rs.getDouble("poids"),
                rs.getDouble("tourDeTaille"),
                rs.getDouble("tourDeHanches"),
                rs.getDouble("tourDePoitrine"),
                rs.getDouble("masseGrasse"),
                rs.getDouble("masseMusculaire"),
                niveauActivite,
                utilDate,
                rs.getDouble("IMC") 
                    
            );
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return ficheMesures;
}


    @Override
  
public void supprimerfichmes(FicheMesures f) {
    try {
        String req = "DELETE FROM fichemesures WHERE idfich=?";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, f.getIdfich()); 

        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
    @Override
public void modifierfichmes(FicheMesures f) {
    try {
        String req = "UPDATE fichemesures f SET taille=?, poids=?, tourDeTaille=?, tourDeHanches=?, tourDePoitrine=?, masseGrasse=?, masseMusculaire=?, niveauActivite=?, datecreationfich=?,IMC=?  WHERE pseudofich=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDouble(1, f.getTaille());
        pre.setDouble(2, f.getPoids());
        pre.setDouble(3, f.getTourDeTaille());
        pre.setDouble(4, f.getTourDeHanches());
        pre.setDouble(5, f.getTourDePoitrine());
        pre.setDouble(6, f.getMasseGrasse());
        pre.setDouble(7, f.getMasseMusculaire());
        pre.setString(8, f.getNiveauActivite().name());
        java.util.Date utilDate = f.getDatecreationfich();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        pre.setDate(9, sqlDate);
        pre.setDouble(10, f.getIMC());
        pre.setString(11, f.getPseudofich());
    
      

        pre.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}


    @Override
    public List<FicheMesures> affihcerfichemes() {
        List<FicheMesures > fichemes = new ArrayList<>();
        String sql ="select * from fichemesures ";
        try {
            ste= con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                  Typeactv niveauActivite =Typeactv.valueOf(rs.getString("niveauActivite"));
                  java.sql.Date sqlDate = rs.getDate("datecreationfich");
                  java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                  FicheMesures f = new FicheMesures(rs.getInt("idfich" ),rs.getString("pseudofich") ,rs.getDouble("taille"),
                          rs.getDouble("poids"),rs.getDouble("tourDeTaille"),
                  rs.getDouble("tourDeHanches"),rs.getDouble("tourDePoitrine"),rs.getDouble("masseGrasse"),
                          rs.getDouble("masseMusculaire"),niveauActivite ,utilDate,rs.getDouble("IMC"));
                fichemes.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return fichemes;
    }
    
    @Override
    public double calculer_imc(FicheMesures ficheMesures) {
    double imc = 0.0;

   
    String sql = "SELECT taille, poids FROM fichemesures WHERE idfich = ?";
    try {
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, ficheMesures.getIdfich());
        ResultSet rs = pre.executeQuery();

       if (rs.next()) {
            double taille = rs.getDouble("taille");
            double poids = rs.getDouble("poids");
           
            imc = poids / (taille * taille);
          
            if (ficheMesures != null) {
                ficheMesures.setIMC(imc); 
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return imc;
}

 

 
    }

    

