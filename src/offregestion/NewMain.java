/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion;

import java.util.List;

/**
 *
 * @author jaafr
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // offre o1 = new offre(14, "seance coaching", "2030/12/2", "14:00", "15:00", 30, "hhh", typeoffre.coach);
   // System.out.println(o1);
   
   serviceoffre so = new serviceoffre(); // Créez une instance de serviceoffre
    //  so.modifier(o1);

    List<offre> listeOffres = so.getAll(); // Appelez la méthode getAll() à partir de l'instance
 reservation r1 = new reservation(3,"lili",2,1,"07/06/2007");
   System.out.println(r1);
   
   servicereservation sr = new servicereservation(); // Créez une instance de serviceoffre
   
  //sr.ajouter(r1); 
  //List<reservation> reservations = sr.getAll();
   //int nombreOffres = so.getNombreOffres();
//System.out.println("Nombre d'offres : " + nombreOffres);

     //Appelez la méthode getAll pour récupérer toutes les réservations
   List<reservation> reservations = sr.rechercherParUserId(1);

    // Parcourez la liste des réservations et affichez-les de manière lisible
   /* for (reservation reservation : reservations) {
        System.out.println("ID Reservation : " + reservation.getId_res());
        System.out.println("Nom de l'utilisateur : " + reservation.getUserName());
        System.out.println("ID Offre : " + reservation.getId_offre());
        System.out.println("ID Utilisateur : " + reservation.getUserId());
        System.out.println("Date de l'offre : " + reservation.getDate_offre());
        System.out.println("----------------------------------"); }// Ligne de séparation*/
  // Supposons que vous souhaitiez rechercher des offres de type "typeoffre.EXEMPLE"
//typeoffre typeRecherche = typeoffre.coach; // Remplacez par le type que vous recherchez
//so.chercherParType(typeRecherche);
}
    
}