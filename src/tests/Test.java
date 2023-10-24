/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/*import entities.FicheMesures;
import entities.Objecttype;
import entities.Regime;
import entities.Typeactv;
import java.util.List;
import services.Servicefichemesures;
import services.Serviceregime;
import tools.MyDB;*/

/**
 *
 * @author Kardo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* MyDB myDB = MyDB.getinstance();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Serviceregime serviceRegime = new Serviceregime();
        
         Regime regime1 = new Regime(1, "Regime Keto", "Un régime cétogène axé sur la"
                 + " réduction des glucides et la consommation de graisses saines.", 
                 Objecttype.PERTE_DE_POIDS, "3 mois", sqlDate, 18.5, 24.9);
         serviceRegime.ajouterregime(regime1);
        System.out.println("Régime ajouté avec succès.");
//
         List <Regime> regimes = serviceRegime.affihcerregime();
    for (Regime regime : regimes) {
        System.out.println(regime.toString());
    }
//
 Regime regimeASupprimer = serviceRegime.getRegimeParId(27); 
    if (regimeASupprimer != null) {
        serviceRegime.supprimerregime(regimeASupprimer);
       System.out.println("Régime supprimé avec succès.");
    } else {
        System.out.println("Le régime à supprimer n'existe pas.");
    }
//
Regime regimeAModifier = serviceRegime.getRegimeParId(24); 
    if (regimeAModifier != null) {
       regimeAModifier.setNomreg("Régime équilibrée");
            regimeAModifier.setDescriptionreg("Un régime alternatif pour une alimentation équilibrée.");
            serviceRegime.modifierregime(regimeAModifier);
       serviceRegime.modifierregime(regimeAModifier);
        System.out.println("Régime modifié avec succès.");
    } else {
        System.out.println("Le régime à modifier n'existe pas.");
    }
     ///////////////
     Servicefichemesures serviceFicheMesures = new Servicefichemesures();
     //   FicheMesures ficheMesures1 = new FicheMesures(1,180.0, 73.0, 81.0, 96.0, 70.0, 19.5, 50.0,Typeactv.SEDENTAIRE, sqlDate);
    //     FicheMesures ficheMesures2 = new FicheMesures(1,180.0, 60.0, 81.0, 96.0, 70.0, 19.5, 50.0,Typeactv.TRES_ACTIFS, sqlDate);
    //    serviceFicheMesures.ajouterfichmes(ficheMesures1);
    //    serviceFicheMesures.ajouterfichmes(ficheMesures2);
    //    System.out.println("Fiche de mesures ajoutée avec succès.");
     //  
   //  List<FicheMesures> fiches = serviceFicheMesures.affihcerfichemes();
   // for (FicheMesures fiche : fiches) {
      //  System.out.println(fiche.toString()); 
    //}
      //
      
       
       //
   
FicheMesures ficheASupprimer = serviceFicheMesures.getFicheMesuresParId(1);

if (ficheASupprimer != null) {

    serviceFicheMesures.supprimerfichmes(ficheASupprimer);
    System.out.println("Fiche de mesures supprimée avec succès.");
} else {
    System.out.println("La fiche de mesures à supprimer n'existe pas.");
}
//

FicheMesures ficheAModifier = serviceFicheMesures.getFicheMesuresParId(1); 

if (ficheAModifier != null) {
    
    ficheAModifier.setTaille(175.0);
    ficheAModifier.setPoids(75.0);
    serviceFicheMesures.modifierfichmes(ficheAModifier);
    System.out.println("Fiche de mesures modifiée avec succès.");
} else {
    System.out.println("La fiche de mesures à modifier n'existe pas.");
}
///
    
 int ficheId = 19; 

    FicheMesures ficheMesures = serviceFicheMesures.getFicheMesuresParId(ficheId);

    if (ficheMesures != null) {
        double imc = serviceFicheMesures.calculer_imc(ficheMesures);
        System.out.println("L'IMC " + imc);
    } else {
        System.out.println("La fiche de mesures n'a pas été trouvée.");
    }
///



serviceFicheMesures.ajouterRegimeAFiche(19,36);
List<Regime> regimesPourFiche = serviceFicheMesures.getRegimesPourFiche(8);
 for (Regime regime : regimesPourFiche) {
           System.out.println("Régime associé à la fiche :");
            System.out.println(regime.toString());
      }
    
    //
    
   
    
    
    
    
    */
    }
    }  
    
    


