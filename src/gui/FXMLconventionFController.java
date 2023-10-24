/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Convention;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceConvention;

/**
 * FXML Controller class
 *
 * @author omnia
 */
public class FXMLconventionFController implements Initializable {
  
    @FXML
    private TextField tfsociete;
    
    @FXML
    private TextField tfadresse;
    
    @FXML
    private TextField tfemail;
    
    @FXML
    private TextField tftelephone;

    @FXML
    private Button btnInsert;
      
    @FXML
    private Button btnclear;
    
        @FXML
    private void Clear(ActionEvent event) {
        clear();
    }
    
    private void clear() {
        tfsociete.clear();
        tfadresse.clear();
        tfemail.clear();
        tftelephone.clear();
    }
    
    
    
@FXML
private void Ajout(ActionEvent event) {
    if (tfsociete.getText() == null || tfsociete.getText().trim().isEmpty()) {
        showAlert("Veuillez remplir le champ de la société.");
    } else if (tfadresse.getText() == null || tfadresse.getText().trim().isEmpty()) {
        showAlert("Veuillez remplir le champ de l'adresse.");
    } else if (tfemail.getText() == null || tfemail.getText().trim().isEmpty()) {
        showAlert("Veuillez remplir le champ d'email.");
    } else if (!isEmailValid(tfemail.getText())) {
        showAlert("Veuillez saisir une adresse email valide.");
    } else if (tftelephone.getText() == null || tftelephone.getText().trim().isEmpty()) {
        showAlert("Veuillez remplir le champ de téléphone.");
    } else if (!isTunisianPhoneNumber(tftelephone.getText())) {
        showAlert("Veuillez saisir un numéro de 8 chiffres.");
    } else {
        ServiceConvention sc = new ServiceConvention();
        Convention c = new Convention(tfsociete.getText(), tfadresse.getText(), tfemail.getText(), tftelephone.getText());
        sc.ajouter(c);
        clear();
    }
}

private void showAlert(String message) {
    Alert dialogW = new Alert(Alert.AlertType.WARNING);
    dialogW.setTitle("A warning dialog-box");
    dialogW.setHeaderText(null); 
    dialogW.setContentText(message);
    dialogW.showAndWait();
}

private boolean isEmailValid(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(emailRegex);
}

private boolean isTunisianPhoneNumber(String phoneNumber) {
    String phoneRegex = "^\\d{8}$";
    return phoneNumber.matches(phoneRegex);
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
              @FXML
    private void NavigateToOffreF(MouseEvent event) {
                           try {
                Parent page = FXMLLoader.load(getClass().getResource("FXMLoffreF.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
}
