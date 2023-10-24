/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaafr
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnoffres;
    @FXML
    private Button btnreservation;
    @FXML
    private Button btnstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void openmesOffres(ActionEvent event) throws IOException {
        // Charger la vue de l'interface MesOffres depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mesOffres.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir de n'importe quel élément de l'interface
        Stage stage = (Stage) btnoffres.getScene().getWindow();

        // Définir la nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openreservation(ActionEvent event) throws IOException {
        // Charger la vue de l'interface Reservation depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir de n'importe quel élément de l'interface
        Stage stage = (Stage) btnreservation.getScene().getWindow();

        // Définir la nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openstat(ActionEvent event) throws IOException {
        // Charger la vue de l'interface Stat depuis le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle à partir de n'importe quel élément de l'interface
        Stage stage = (Stage) btnstat.getScene().getWindow();

        // Définir la nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    
}
