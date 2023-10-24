 package tn.esprit.tools;

import java.sql.Connection;
import java.sql.Time;
import java.util.List;
import tn.esprit.entity.Activite;
import tn.esprit.entity.Objectif;
import tn.esprit.entity.Planning;
import tn.esprit.entity.TypeActivite;
import tn.esprit.services.ServiceActivite;
import tn.esprit.services.ServicePlanning;
public class PiDev {

    public static void main(String[] args) {
        // Instanciez la classe MyBD pour obtenir la connexion à la base de données
        MyBD myBD = MyBD.getinstance();
        Connection connection = myBD.getCon();

        if (connection != null) {
            // Maintenant, vous pouvez utiliser la connexion pour effectuer des opérations sur la base de données
            

            //Activite A1 = new Activite(1, 3, "Yoga matinal", 10, "Exercices de yoga pour bien commencer la journée", TypeActivite.YOGA, Objectif.FESSES);
            //System.out.println(A1);
            // Activite A2 = new Activite(1, 5, "Yoga soir", 5, "Exercices de yoga pour bien commencer la journée", TypeActivite.YOGA, Objectif.VENTRE);
           // System.out.println(A1);
            Planning p1 = new Planning("P1", 6);
            Planning p2 = new Planning("P2",9 );
            ServiceActivite A = new ServiceActivite();
            ServicePlanning P = new ServicePlanning();
           // A.ajouter(A1);
           // A.ajouter(A2);
            P.ajouter(p1);
            
           
           // P.ajouterActiviteAPlanning(1,10);
            //P.ajouterActiviteAPlanning(1,11);
            
            
            //P.supprimerActiviteDePlanning(1,10);
             //A.supprimer("Yoga matinal");
             //List<Activite> activitesYoga = ServiceActivite.afficherParType(TypeActivite.YOGA);
             //List<Activite> activitesYogaPourVentre = serviceActivite.afficherParTypeEtObjectif(TypeActivite.YOGA, Objectif.VENTRE);


            // N'oubliez pas de fermer la connexion lorsque vous avez terminé
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
        
    }
    
}
