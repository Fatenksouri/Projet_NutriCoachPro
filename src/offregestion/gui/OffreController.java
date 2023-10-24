package offregestion.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import offregestion.NotificationUtil;
import offregestion.offre;
import offregestion.serviceoffre;
import offregestion.typeoffre;
import org.controlsfx.control.Notifications;
import tray.notification.NotificationType;
import tray.notification.NotificationType;
import tray.notification.NotificationAnimationType;


public class OffreController implements Initializable {

    @FXML
    private TextField nomm;
    @FXML
    private TextField datee;
    @FXML
    private TextField heure_debut_offre;
    @FXML
    private TextField prix_offre;
    @FXML
    private TextField desc;
    @FXML
    private TextField heure_fin_offre;
    @FXML
    private ChoiceBox<typeoffre> ch_box_ty_offre;
    @FXML
    private TableView<offre> OffreTableModel;

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    private ObservableList<offre> listoffre = FXCollections.observableArrayList();
    private serviceoffre serviceOffre = new serviceoffre();

    @FXML
    private TableColumn<offre, String> nomOffre;
    @FXML
    private TableColumn<offre, String> dateOffre;
    @FXML
    private TableColumn<offre, String> heureDebut;
    @FXML
    private TableColumn<offre, String> heureFin;
    @FXML
    private TableColumn<offre, Double> prixOffre;
    @FXML
    private TableColumn<offre, String> descriptionOffre;
    @FXML
    private TableColumn<offre, String> typeOffre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInitialDataFromDatabase();
        nomOffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        dateOffre.setCellValueFactory(new PropertyValueFactory<>("date_offre"));
        heureDebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        heureFin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
        prixOffre.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descriptionOffre.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeOffre.setCellValueFactory(new PropertyValueFactory<>("type"));
        OffreTableModel.setItems(listoffre);
        
        OffreTableModel.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            afficherDetailsOffre(newSelection);
        }
    });
    }
private void afficherDetailsOffre(offre selectedOffre) {
    nomm.setText(selectedOffre.getNom_offre());
    datee.setText(selectedOffre.getDate_offre());
    heure_debut_offre.setText(selectedOffre.getHeure_debut());
    heure_fin_offre.setText(selectedOffre.getHeure_fin());
    prix_offre.setText(String.valueOf(selectedOffre.getPrix()));
    desc.setText(selectedOffre.getDescription());
    ch_box_ty_offre.setValue(selectedOffre.getType());
}

    private void loadInitialDataFromDatabase() {
        List<offre> initialoffre = serviceOffre.getAll();
        listoffre.clear();
        listoffre.addAll(initialoffre);
        ch_box_ty_offre.setItems(FXCollections.observableArrayList(typeoffre.values()));
    }

    private void afficherOffres() {
        listoffre.setAll(serviceOffre.getAll());
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean estPrixValide(String prix) {
        try {
            double prixText = Double.parseDouble(prix);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void handleAjouterOffre(ActionEvent event) {
        String nom_offre = nomm.getText();
        String date = datee.getText();
        String heure_debut = heure_debut_offre.getText();
        String heure_fin = heure_fin_offre.getText();
        String prixText = prix_offre.getText();
        String description = desc.getText();
        typeoffre type = ch_box_ty_offre.getValue();

        try {
            double prix = Double.parseDouble(prixText);
            offre nouvelleOffre = new offre(nom_offre, date, heure_debut, heure_fin, (int) prix, description, type);
            serviceOffre.ajouter(nouvelleOffre);
            afficherOffres();
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le prix n'est pas valide. Veuillez entrer un nombre valide.");
        }
Notifications.create()
    .title("Confirmation").text("offre ajoutée avec succes").showWarning();
    }

    @FXML
    private void handleSupprimerOffre(ActionEvent event) {
        int selectedIndex = OffreTableModel.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            offre offreASupprimer = listoffre.get(selectedIndex);
            serviceOffre.supprimer(offreASupprimer);
            afficherOffres();
        } else {
            afficherMessageErreur("Aucune offre sélectionnée pour la suppression.");
        }
    }

    @FXML
    private void handleModifierOffre(ActionEvent event) {
        int selectedIndex = OffreTableModel.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            offre offreExistante = OffreTableModel.getItems().get(selectedIndex);
            String nom_offre = nomm.getText();
            String date = datee.getText();
            String heure_debut = heure_debut_offre.getText();
            String heure_fin = heure_fin_offre.getText();
            String prixText = prix_offre.getText();
            String description = desc.getText();
            typeoffre type = ch_box_ty_offre.getValue();

            try {
                double prix = Double.parseDouble(prixText);
                offreExistante.setNom_offre(nom_offre);
                offreExistante.setDate_offre(date);
                offreExistante.setHeure_debut(heure_debut);
                offreExistante.setHeure_fin(heure_fin);
                offreExistante.setPrix((int) prix);
                offreExistante.setDescription(description);
                offreExistante.setType(type);
                serviceOffre.modifier(offreExistante);
                afficherOffres();
            } catch (NumberFormatException e) {
                afficherMessageErreur("Le prix n'est pas valide. Veuillez entrer un nombre valide.");
            }
        } else {
            afficherMessageErreur("Aucune offre sélectionnée pour la modification.");
        }
    }
}

