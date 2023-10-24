package offregestion.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import offregestion.offre;
import offregestion.typeoffre;
import java.util.List;
import offregestion.reservation;
import offregestion.serviceoffre;
import offregestion.servicereservation;

public class RecommandationController implements Initializable {

    @FXML
    private TableView<offre> tableOffres;

    @FXML
    private TableColumn<offre, String> tbnomOffre;

    @FXML
    private TableColumn<offre, String> tbdate;
 
    @FXML
    private TableColumn<offre, String> tbheured;

    @FXML
    private TableColumn<offre, String> tbheuref;

    @FXML
    private TableColumn<offre, Double> tbprix;

    @FXML
    private TableColumn<offre, String> tbdesc;

    @FXML
    private TableColumn<offre, String> tbtype;

  private int userId; // Store the authenticated user's ID here

    public void setUserId(int userId) {
        this.userId = userId;
    }
    private serviceoffre offreService;
    private servicereservation reservationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Call the method to load and display the offers based on reservations
        loadOffersBasedOnReservations();
    }
    
    public RecommandationController(serviceoffre offreService, servicereservation reservationService) {
    this.offreService = offreService;
    this.reservationService = reservationService;
}
private int countReservationsByType(List<reservation> reservations, typeoffre type) {
        // Count the number of reservations of the specified type
        int count = 0;
        for (reservation r : reservations) {
            if (r.getType() == type) {
                count++;
            }
        }
        return count;
    }
 private List<offre> chercherParType(typeoffre type) {
        List<offre> offresFiltrees = new ArrayList<>();
    
    for (offre offre : getAll()) {
        if (offre.getType() == type) {
            offresFiltrees.add(offre);
        }
    }
    
    return offresFiltrees;
    }
 
    private List<reservation> rechercherParUserId(int userId) {
    // Utilisez reservationService pour récupérer les réservations de l'utilisateur
    return reservationService.rechercherParUserId(userId);
}

private List<offre> getAll() {
    // Utilisez offreService pour récupérer toutes les offres
    return offreService.getAll();
}

    // Implement this method to count reservations and load offers accordingly
    private void loadOffersBasedOnReservations() {
        // Retrieve reservations for the authenticated user
        List<reservation> reservations = rechercherParUserId(userId);

        // Count the number of "coach" and "nutritionniste" reservations
        int coachReservations = countReservationsByType(reservations, typeoffre.coach);
        int nutritionnisteReservations = countReservationsByType(reservations, typeoffre.nutritionniste);

        // Determine which offers to display based on the reservation counts
        if (coachReservations > nutritionnisteReservations) {
            // Display "coach" offers
            List<offre> coachOffers = chercherParType(typeoffre.coach);
            displayOffers(coachOffers);
        } else if (coachReservations < nutritionnisteReservations) {
            // Display "nutritionniste" offers
            List<offre> nutritionnisteOffers = chercherParType(typeoffre.nutritionniste);
            displayOffers(nutritionnisteOffers);
        } else {
            // Display all offers
            List<offre> allOffers = getAll();
            displayOffers(allOffers);
        }
    }

    private void displayOffers(List<offre> offers) {
     tableOffres.getItems().clear();
     tableOffres.getItems().addAll(offers);

    }
    public RecommandationController() {
    // Constructeur par défaut sans paramètres
    // Vous n'avez pas besoin d'ajouter de code ici, sauf si nécessaire
}
}
