package offregestion.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import offregestion.reservation;
import offregestion.servicereservation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import offregestion.offre;
import offregestion.serviceoffre;

public class ReservationController implements Initializable {
    @FXML
    private TextField iduser;

    @FXML
    private TableView<reservation> tabledetail;

    @FXML
    private TableColumn<reservation, String> username;

    @FXML
    private TableColumn<reservation, String> nomOffre;

    @FXML
    private TableColumn<reservation, String> date1;
    @FXML
    private AnchorPane reservation;
    @FXML
    private Button chercherParId;
    @FXML
    private VBox vbox;
    @FXML
    private TableColumn<offre, String> tbnom;
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
    @FXML
    private TableView<offre> tableoffre;

    public ReservationController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        nomOffre.setCellValueFactory(new PropertyValueFactory<>("nomOffre"));
        date1.setCellValueFactory(new PropertyValueFactory<>("date_offre"));
    }

    @FXML
    private void chercherParId(ActionEvent event) {
        String userIdText = iduser.getText();
        servicereservation serviceReservation = new servicereservation();

        try {
            int userId = Integer.parseInt(userIdText);
            List<reservation> reservations = serviceReservation.rechercherParUserId(userId);

            // Mettez à jour la liste observable avec les réservations trouvées
            tabledetail.getItems().setAll(reservations);
        } catch (NumberFormatException e) {
            afficherMessageErreur("L'ID de l'utilisateur n'est pas valide.");
        }
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
private void afficherOffresNutritionniste() {
    // Créez un service ou un gestionnaire d'offres
    serviceoffre serviceOffre = new serviceoffre();

    // Obtenez la liste des offres de type "nutritionniste"
    List<offre> offresNutritionniste = serviceOffre.getOffresByType("nutritionniste");

    // Mettez à jour la liste observable de la TableView
    tableoffre.getItems().setAll(offresNutritionniste);
}
}
