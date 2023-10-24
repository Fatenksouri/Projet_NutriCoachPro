/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.FicheMesures;
import entities.Objecttype;
import entities.Regime;
import entities.Typeactv;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.Servicefichemesures;

import tools.Notificationreg;
 import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Serviceregime;
import tools.Twilosmsreg;


/**
 * FXML Controller class
 *
 * @author Kardo
 */
public class AjouterfichemesController implements Initializable {

    @FXML
    private TextField txttaille;
    @FXML
    private TextField txtpoids;
    @FXML
    private TextField txttourdetaille;
    @FXML
    private TextField txttourdehanches;
    @FXML
    private TextField txttourdepoitrine;
    @FXML
    private TextField txtmassegrasse;
    @FXML
    private TextField txtmassemusculaire;
  
    @FXML
    private DatePicker datepickfich;
    @FXML
    private ChoiceBox<Typeactv> choicenivactiv;
    @FXML
    private Label controletaille;
    @FXML
    private Label controlepoids;
    
    @FXML
    private TableView<Regime> regimetvfich;
    @FXML
    private TableColumn<Regime, String> nomregcolfich;
    @FXML
    private TableColumn<Regime, String> descregcolfich;
    @FXML
    private TableColumn<Regime,  Objecttype> objregcolfich;
    @FXML
    private TableColumn<Regime, String> dureeregcolfich;
    @FXML
    private TableColumn<Regime, Date> dateregcolfich;
    @FXML
    private TableColumn<Regime, Double> imcmincolfich;
    @FXML
    private TableColumn<Regime, Double> imcmaxcolfich;
   
     @FXML
    private TextField txtpseudofich;
    Servicefichemesures serviceFicheMesures = new Servicefichemesures();
     private Serviceregime serviceRegime = new Serviceregime();
    @FXML
    private Label resultatimc;
    @FXML
    private Label nom;
   
   
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicenivactiv.getItems().addAll(Typeactv.values());
        choicenivactiv.setValue(Typeactv.SEDENTAIRE);
        
    }   
  
 @FXML
private void ajouterfich(ActionEvent event) {
    String tailleText = txttaille.getText();
    String poidsText = txtpoids.getText();
     String pseudofich = txtpseudofich.getText();
    if (pseudofich.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ obligatoire");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'pseudofich' est obligatoire.");
        alert.showAndWait();
    }
    else if (tailleText.isEmpty() || poidsText.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs obligatoires");
        alert.setHeaderText(null);
        alert.setContentText("Les champs 'Taille' et 'Poids' sont obligatoires.");
        alert.showAndWait();
    } 
     else {
        

        
        if (pseudofichAlreadyExists(pseudofich)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("pseudo fiche déjà utilisé");
            alert.setHeaderText(null);
            alert.setContentText("pseudo fiche déjà utilisé ");
            alert.showAndWait();
        } else {
            double taille = Double.parseDouble(txttaille.getText());
            double poids = Double.parseDouble(txtpoids.getText());
            double tourDeTaille = Double.parseDouble(txttourdetaille.getText());
            double tourDeHanches = Double.parseDouble(txttourdehanches.getText());
            double tourDePoitrine = Double.parseDouble(txttourdepoitrine.getText());
            double masseGrasse = Double.parseDouble(txtmassegrasse.getText());
            double masseMusculaire = Double.parseDouble(txtmassemusculaire.getText());
            Typeactv niveauActivite = choicenivactiv.getValue();
          

            FicheMesures ficheMesures = new FicheMesures(pseudofich,taille, poids, tourDeTaille, tourDeHanches, tourDePoitrine, masseGrasse, masseMusculaire, niveauActivite, java.sql.Date.valueOf(datepickfich.getValue()),0.0);
           //
                serviceFicheMesures.ajouterfichmes(ficheMesures);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Fiche ajoutée avec succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La fiche a été ajoutée avec succès.");
                successAlert.showAndWait();
              
      String phoneNumber = "+21694912442"; 
     String message = "Fiche mesures ajoutée avec succées  : " + nom; 
     Twilosmsreg.sendSMS(phoneNumber, message);
            
        }
    }
}


private boolean pseudofichAlreadyExists(String pseudofich) {
    List<FicheMesures> fiches = serviceFicheMesures.affihcerfichemes();
    for (FicheMesures fiche : fiches) {
        if (fiche.getPseudofich().equals(pseudofich)) { // Utilisez equals pour comparer les chaînes
            return true;
        }
    }
    return false;
}


////////////////
    @FXML
    private void chercherfich(ActionEvent event) {
       
   // Obtenir la valeur du champ userId
String pseudofich = txtpseudofich.getText();
    if (pseudofich.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ obligatoire");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'pseudofich' est obligatoire.");
        alert.showAndWait();
    } else {
       
        if ( pseudofichAlreadyExists(  pseudofich)) {
            
            FicheMesures ficheMesures = serviceFicheMesures.getFicheMesuresParpseudo(pseudofich);

            if (ficheMesures != null) {
                // Remplir les champs de texte avec les valeurs de la fiche
                txttaille.setText(String.valueOf(ficheMesures.getTaille()));
                txtpoids.setText(String.valueOf(ficheMesures.getPoids()));
                txttourdetaille.setText(String.valueOf(ficheMesures.getTourDeTaille()));
                txttourdehanches.setText(String.valueOf(ficheMesures.getTourDeHanches()));
                txttourdepoitrine.setText(String.valueOf(ficheMesures.getTourDePoitrine()));
                txtmassegrasse.setText(String.valueOf(ficheMesures.getMasseGrasse()));
                txtmassemusculaire.setText(String.valueOf(ficheMesures.getMasseMusculaire()));
                choicenivactiv.setValue(ficheMesures.getNiveauActivite());
                  java.util.Date date = ficheMesures.getDatecreationfich();
                Instant instant = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault(); 
                 LocalDate localDate = instant.atZone(zoneId).toLocalDate();
                datepickfich.setValue(localDate);
                resultatimc.setText("IMC : " + ficheMesures.getIMC());
}

             
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pseudofiiche introuvable");
                alert.setHeaderText(null);
                alert.setContentText("Aucune fiche associée à ce pseudofiche");
                alert.showAndWait();
            }
        }
    }


    }

    @FXML
    private void modifiererfich(ActionEvent event) {
      
   String pseudofich = txtpseudofich.getText();

    if (pseudofich.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ obligatoire");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'pseudofich' est obligatoire.");
        alert.showAndWait();
    } else {
       if (pseudofichAlreadyExists(  pseudofich)) {
            
            FicheMesures ficheMesures = serviceFicheMesures.getFicheMesuresParpseudo(pseudofich);

            if (ficheMesures != null) {
                // Récupérer les données des champs de texte
                double taille = Double.parseDouble(txttaille.getText());
                double poids = Double.parseDouble(txtpoids.getText());
                double tourDeTaille = Double.parseDouble(txttourdetaille.getText());
                double tourDeHanches = Double.parseDouble(txttourdehanches.getText());
                double tourDePoitrine = Double.parseDouble(txttourdepoitrine.getText());
                double masseGrasse = Double.parseDouble(txtmassegrasse.getText());
                double masseMusculaire = Double.parseDouble(txtmassemusculaire.getText());
                Typeactv niveauActivite = choicenivactiv.getValue();
                java.util.Date date = java.sql.Date.valueOf(datepickfich.getValue());
                 
                // Mettre à jour l'objet FicheMesures
                ficheMesures.setTaille(taille);
                ficheMesures.setPoids(poids);
                ficheMesures.setTourDeTaille(tourDeTaille);
                ficheMesures.setTourDeHanches(tourDeHanches);
                ficheMesures.setTourDePoitrine(tourDePoitrine);
                ficheMesures.setMasseGrasse(masseGrasse);
                ficheMesures.setMasseMusculaire(masseMusculaire);
                ficheMesures.setNiveauActivite(niveauActivite);
               ficheMesures.setDatecreationfich(date);
               
               double imc = serviceFicheMesures.calculer_imc(ficheMesures);
                ficheMesures.setIMC(imc);
                // Appeler la méthode de service pour modifier la fiche
                serviceFicheMesures.modifierfichmes(ficheMesures);
                // Calculer le nouvel IMC
              
             

              // Mettre à jour le label resultatimc
              resultatimc.setText("IMC : " + imc);

               Notificationreg n=new Notificationreg();
               n.notificationreg();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pseudofiche introuvable");
                alert.setHeaderText(null);
                alert.setContentText("Aucune fiche associée à ce pseudofich");
                alert.showAndWait();
            }
        } 
    }
}

    

    @FXML
    private void supprimererfich(ActionEvent event) {
        
    // Obtenir la valeur du champ pseudofich
    String pseudofich = txtpseudofich.getText();
    if (pseudofich.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ obligatoire");
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'pseudofich' est obligatoire.");
        alert.showAndWait();
    } else {
        if (pseudofichAlreadyExists(pseudofich)) {
            // Récupérer la fiche à supprimer
            FicheMesures ficheMesures = serviceFicheMesures.getFicheMesuresParpseudo(pseudofich);
            if (ficheMesures != null) {
                // Appeler la méthode de service pour supprimer la fiche
                serviceFicheMesures.supprimerfichmes(ficheMesures);

                // Effacer les champs de texte
                txttaille.clear();
                txtpoids.clear();
                txttourdetaille.clear();
                txttourdehanches.clear();
                txttourdepoitrine.clear();
                txtmassegrasse.clear();
                txtmassemusculaire.clear();
                datepickfich.getEditor().clear();
                choicenivactiv.setValue(Typeactv.SEDENTAIRE);

                // Effacer le label resultatimc
                resultatimc.setText("");

                // Afficher un message de succès
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Fiche supprimée avec succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La fiche a été supprimée avec succès.");
                successAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pseudofiche introuvable");
                alert.setHeaderText(null);
                alert.setContentText("Aucune fiche associée à ce pseudofiche");
                alert.showAndWait();
            }
        }
    }
}

  

 

@FXML
private void calculerimcdecettefiche(ActionEvent event) {
    // Obtenez la fiche associée au pseudofich actuel
    String pseudofich = txtpseudofich.getText();
    if (!pseudofich.isEmpty()) {
        if (pseudofichAlreadyExists(pseudofich)) {
            FicheMesures ficheMesures = serviceFicheMesures.getFicheMesuresParpseudo(pseudofich);

            if (ficheMesures != null) {
                // Appelez la méthode de service pour calculer l'IMC
                double imc = serviceFicheMesures.calculer_imc(ficheMesures);

                // Mettez à jour l'attribut IMC de la fiche et sauvegardez-le dans la base de données
                ficheMesures.setIMC(imc);
                serviceFicheMesures.modifierfichmes(ficheMesures);

                // Mettez à jour l'affichage de l'IMC dans le label resultatimc
                resultatimc.setText("IMC : " + imc);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("pseudofiche introuvable");
                alert.setHeaderText(null);
                alert.setContentText("Aucune fiche associée à ce pseudofich");
                alert.showAndWait();
            }
        }
    }
}

@FXML
private void ajouterregimesàfich(ActionEvent event)  {
    // Utilisez la valeur de l'IMC calculé pour rechercher les régimes correspondants
    double imc = Double.parseDouble(resultatimc.getText().replace("IMC : ", ""));

    // Maintenant, vous pouvez rechercher les régimes correspondants
    List<Regime> regimesCorrespondants = rechercherRegimesParIMC(imc);

    // Affichez les régimes dans la TableView
    afficherRegimesDansTableView(regimesCorrespondants);
}

private List<Regime> rechercherRegimesParIMC(double imc) {
    List<Regime> tousLesRegimes = serviceRegime.affihcerregime();

    // Utilisez Stream API pour filtrer les régimes en fonction de l'IMC
    return tousLesRegimes.stream()
        .filter(regime -> imc >= regime.getImcMin() && imc <= regime.getImcMax())
        .collect(Collectors.toList());
}

private void afficherRegimesDansTableView(List<Regime> regimes) {
    // Créez un ObservableList à partir de la liste de régimes
    ObservableList<Regime> observableRegimes = FXCollections.observableArrayList(regimes);

    // Configurez la TableView pour afficher les régimes
    regimetvfich.getColumns().clear();
    
    TableColumn<Regime, String> nomRegCol = new TableColumn<>("Nom");
    nomRegCol.setCellValueFactory(new PropertyValueFactory<>("nomreg"));
    
    TableColumn<Regime, String> descRegCol = new TableColumn<>("Description");
    descRegCol.setCellValueFactory(new PropertyValueFactory<>("descriptionreg"));
    
    TableColumn<Regime, String> objectifRegCol = new TableColumn<>("Objectif");
    objectifRegCol.setCellValueFactory(new PropertyValueFactory<>("objectifreg"));
    
    TableColumn<Regime, String> dureeRegCol = new TableColumn<>("Durée");
    dureeRegCol.setCellValueFactory(new PropertyValueFactory<>("dureereg"));
    
    // Ajoutez les colonnes à la TableView
    regimetvfich.getColumns().addAll(nomRegCol, descRegCol, objectifRegCol, dureeRegCol);
    
    // Configurez la TableView pour afficher les régimes
    regimetvfich.setItems(observableRegimes);
}
     
}
        
        
        
        
    
    
