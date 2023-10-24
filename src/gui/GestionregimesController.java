/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Objecttype;
import entities.Regime;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import services.Serviceregime;

import tools.Notificationreg;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import tools.Twilosmsreg;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * FXML Controller class
 *
 * @author Kardo
 */
public class GestionregimesController implements Initializable {

    private Serviceregime serviceRegime = new Serviceregime();

    
    @FXML
    private TableView<Regime> regimetv;
    @FXML
    private TableColumn<Regime, String> nomregcol;
    @FXML
    private TableColumn<Regime, String> descregcol;
    @FXML
    private TableColumn<Regime,  Objecttype> objregcol;
    @FXML
    private TableColumn<Regime, String> dureeregcol;
    @FXML
    private TableColumn<Regime, Date> dateregcol;
    @FXML
    private TableColumn<Regime, Double> imcmincol;
    @FXML
    private TableColumn<Regime, Double> imcmaxcol;
    @FXML
    private TextField txtnomreg;
    @FXML
    private TextField txtdescreg;
    @FXML
    private ChoiceBox<Objecttype> choiceobj;
    @FXML
    private TextField txtduree;
    @FXML
    private TextField txtimcmin;
    @FXML
    private TextField txtimcmax;
    @FXML
    private DatePicker datepickreg;
    @FXML
    private TextField filtretxtreg;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    choiceobj.getItems().addAll(Objecttype.values());
    choiceobj.setValue(Objecttype.PERTEPOIDS);
    List<Regime> listeRegimes = serviceRegime.affihcerregime();
    ObservableList<Regime> data = FXCollections.observableArrayList(listeRegimes);
    nomregcol.setCellValueFactory(new PropertyValueFactory<>("nomreg"));
    descregcol.setCellValueFactory(new PropertyValueFactory<>("descriptionreg"));
    objregcol.setCellValueFactory(new PropertyValueFactory<>("objectifreg"));
    dureeregcol.setCellValueFactory(new PropertyValueFactory<>("dureereg"));
    dateregcol.setCellValueFactory(new PropertyValueFactory<>("datecreationreg"));
    imcmincol.setCellValueFactory(new PropertyValueFactory<>("imcMin"));
    imcmaxcol.setCellValueFactory(new PropertyValueFactory<>("imcMax"));
    regimetv.setItems(data);
    //////////////////
 // Créez un gestionnaire d'événements pour les lignes de la table
    regimetv.setRowFactory(tv -> {
        TableRow<Regime> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            // Assurez-vous que l'événement provient d'une ligne de TableView
            if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                // Récupérez le régime sélectionné
                Regime selectedRegime = row.getItem();

                if (selectedRegime != null) {
                    // Remplissez les champs de formulaire avec les informations de l'élément sélectionné
                    txtnomreg.setText(selectedRegime.getNomreg());
                    txtdescreg.setText(selectedRegime.getDescriptionreg());
                    choiceobj.setValue(selectedRegime.getObjectifreg());
                    txtduree.setText(selectedRegime.getDureereg());
                java.util.Date date = selectedRegime.getDatecreationreg();
                Instant instant = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault(); 
                LocalDate localDate = instant.atZone(zoneId).toLocalDate();
                datepickreg.setValue(localDate);
                    
                    txtimcmin.setText(String.valueOf(selectedRegime.getImcMin()));
                    txtimcmax.setText(String.valueOf(selectedRegime.getImcMax()));
                }
            }
        });
        return row;
    });
}

   ////////////////////////////
      
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void   afficherRegimes(){
    List<Regime> listeRegimes = serviceRegime.affihcerregime();
    ObservableList<Regime> data = FXCollections.observableArrayList(listeRegimes);
    nomregcol.setCellValueFactory(new PropertyValueFactory<>("nomreg"));
    descregcol.setCellValueFactory(new PropertyValueFactory<>("descriptionreg"));
    objregcol.setCellValueFactory(new PropertyValueFactory<>("objectifreg"));
    dureeregcol.setCellValueFactory(new PropertyValueFactory<>("dureereg"));
    dateregcol.setCellValueFactory(new PropertyValueFactory<>("datecreationreg"));
    imcmincol.setCellValueFactory(new PropertyValueFactory<>("imcMin"));
    imcmaxcol.setCellValueFactory(new PropertyValueFactory<>("imcMax"));
    regimetv.setItems(data);}
    @FXML
    private void Ajouterreg(ActionEvent event) {
        
    String nom = txtnomreg.getText();
    String description = txtdescreg.getText();
    String imcMinStr = txtimcmin.getText();
    String imcMaxStr = txtimcmax.getText();
    if (nom.isEmpty() || description.isEmpty() || imcMinStr.isEmpty() || imcMaxStr.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs obligatoires");
        alert.setHeaderText(null);
        alert.setContentText("Les champs 'Nom', 'Description', 'IMC min' et 'IMC max' sont obligatoires.");
        alert.showAndWait();}
     else {
    Objecttype objectType = choiceobj.getValue();
    String duree = txtduree.getText();
    java.util.Date date = java.sql.Date.valueOf(datepickreg.getValue());
    
    double imcMin = Double.parseDouble(txtimcmin.getText());
    double imcMax = Double.parseDouble(txtimcmax.getText());
  
    Regime regime = new Regime( nom, description, objectType, duree, date, imcMin, imcMax);
    serviceRegime.ajouterregime(regime);
   
     Notificationreg n=new Notificationreg();
     n.notificationreg();
     String phoneNumber = "+21694912442"; 
     String message = "Nouveau régime ajouté : " + nom; 
     Twilosmsreg.sendSMS(phoneNumber, message);
      afficherRegimes();
    
    } }
    @FXML
    private void modifierreg(ActionEvent event) {
      
    // Récupérez l'indice de la ligne sélectionnée dans votre TableView
    int selectedIndex = regimetv.getSelectionModel().getSelectedIndex();
    
    // Assurez-vous qu'une ligne est sélectionnée
    if (selectedIndex >= 0) {
        // Récupérez le régime existant à partir de votre TableView
        Regime regimeExistante = regimetv.getItems().get(selectedIndex);

        // Récupérez les nouvelles valeurs depuis les champs de saisie
        String nom = txtnomreg.getText();
        String description = txtdescreg.getText();
        Objecttype objectType = choiceobj.getValue();
        String duree = txtduree.getText();
        java.util.Date date = java.sql.Date.valueOf(datepickreg.getValue());
        double imcMin = Double.parseDouble(txtimcmin.getText());
        double imcMax = Double.parseDouble(txtimcmax.getText());
        
        // Mettez à jour la régime existante avec les nouvelles valeurs
        regimeExistante.setNomreg(nom);
        regimeExistante.setDescriptionreg(description);
        regimeExistante.setObjectifreg(objectType);
        regimeExistante.setDureereg(duree);
        regimeExistante.setDateCreationreg(date);
        regimeExistante.setImcMin(imcMin);
        regimeExistante.setImcMax(imcMax);

        // Mettez à jour le régime via le service ou la base de données
        serviceRegime.modifierregime(regimeExistante);

        // Rafraîchissez votre TableView si nécessaire
        regimetv.refresh();
       
        Notificationreg n=new Notificationreg();
        n.notificationreg();
        // Effacez les champs de saisie
        effacerChampsSaisie();

    } else {
        // Aucune ligne sélectionnée pour la modification
        afficherMessageErreur("Aucun régime sélectionné pour la modification.");
    }
    afficherRegimes();
}
   

// Méthode pour effacer les champs de saisie
private void effacerChampsSaisie() {
    txtnomreg.clear();
    txtdescreg.clear();
    choiceobj.setValue(Objecttype.PERTEPOIDS);
    txtduree.clear();
    datepickreg.setValue(null);
    txtimcmin.clear();
    txtimcmax.clear();
}

      @FXML
    private void supprimereg(ActionEvent event) {
       
    // Récupérez l'indice de la ligne sélectionnée dans votre TableView
    int selectedIndex = regimetv.getSelectionModel().getSelectedIndex();

    // Assurez-vous qu'une ligne est sélectionnée
    if (selectedIndex >= 0) {
        // Récupérez le régime existant à partir de votre TableView
        Regime regimeASupprimer = regimetv.getItems().get(selectedIndex);

        // Affichez une boîte de dialogue de confirmation pour la suppression
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation de suppression");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Êtes-vous sûr de vouloir supprimer ce régime ?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimez le régime via le service ou la base de données
            serviceRegime.supprimerregime(regimeASupprimer);
            Notificationreg n=new Notificationreg();
            n.notificationreg();
            // Rafraîchissez votre TableView après la suppression
            afficherRegimes();
        }
    } else {
        // Aucune ligne sélectionnée pour la suppression
        afficherMessageErreur("Aucun régime sélectionné pour la suppression.");
    }
}

    @FXML
    private void pdffiche(ActionEvent event) {
       


    Regime selectedRegime = regimetv.getSelectionModel().getSelectedItem();

    if (selectedRegime != null) {
        // Obtenez les informations du régime actuellement sélectionné
        String nomRegime = selectedRegime.getNomreg();
        String description = selectedRegime.getDescriptionreg();
        Objecttype objectif = selectedRegime.getObjectifreg();
        String duree = selectedRegime.getDureereg();
        Date datedeCreation = java.sql.Date.valueOf(datepickreg.getValue());
        double imcMin = selectedRegime.getImcMin();
        double imcMax = selectedRegime.getImcMax();

        // Générez le PDF avec les informations du régime
        generatePDF(nomRegime, description, objectif, duree, datedeCreation, imcMin, imcMax);
    }
}

private void generatePDF(String nomRegime, String description, Objecttype objectif, String duree,
                         Date dateCreation, double imcMin, double imcMax) {
    Document document = new Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream("regime.pdf"));
        document.open();

        // Ajoutez les informations du régime au PDF
        document.add(new Paragraph("Nom du Régime: " + nomRegime));
        document.add(new Paragraph("Description: " + description));
        document.add(new Paragraph("Objectif: " + objectif));
        document.add(new Paragraph("Durée: " + duree));
        document.add(new Paragraph("Date de Création: " + dateCreation));
        document.add(new Paragraph("IMC Min: " + imcMin));
        document.add(new Paragraph("IMC Max: " + imcMax));

        document.close();
        System.out.println("Le fichier PDF a été généré avec succès.");
    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void excelfiche(ActionEvent event) {
        
        Regime selectedRegime = regimetv.getSelectionModel().getSelectedItem();

    if (selectedRegime != null) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Régime");

        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nom du Régime");
        header.createCell(1).setCellValue("Description");
        header.createCell(2).setCellValue("Objectif");
        header.createCell(3).setCellValue("Durée");
        header.createCell(4).setCellValue("Date de Création");
        header.createCell(5).setCellValue("IMC Min");
        header.createCell(6).setCellValue("IMC Max");

        HSSFRow row = sheet.createRow(1);
        row.createCell(0).setCellValue(selectedRegime.getNomreg());
        row.createCell(1).setCellValue(selectedRegime.getDescriptionreg());
        row.createCell(2).setCellValue(selectedRegime.getObjectifreg().name());
        row.createCell(3).setCellValue(selectedRegime.getDureereg());
        row.createCell(4).setCellValue(selectedRegime.getDatecreationreg().toString());
        row.createCell(5).setCellValue(selectedRegime.getImcMin());
        row.createCell(6).setCellValue(selectedRegime.getImcMax());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xls"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                fileOut.close();
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Le fichier Excel a été créé avec succès.");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de la création du fichier Excel.");
            }
        }
    } else {
        showAlert(Alert.AlertType.WARNING, "Sélectionnez un régime", "Veuillez sélectionner un régime à exporter vers Excel.");
    }
}

private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
        
    }
///////////
    @FXML
private void filtrerreg(ActionEvent event) {
    // Récupérez le critère de recherche saisi dans filtretxtreg
    String critereRecherche = filtretxtreg.getText().toLowerCase();

    // Récupérez tous les régimes
    List<Regime> listeRegimes = serviceRegime.affihcerregime();

    // Créez une liste pour stocker les régimes filtrés
    List<Regime> regimesFiltres = new ArrayList<>();

    // Parcourez tous les régimes pour appliquer le filtre
    for (Regime regime : listeRegimes) {
        if (regimeMatchesCritere(regime, critereRecherche)) {
            regimesFiltres.add(regime);
        }
    }

    // Affichez les régimes filtrés dans votre TableView
    ObservableList<Regime> data = FXCollections.observableArrayList(regimesFiltres);
    regimetv.setItems(data);
}

private boolean regimeMatchesCritere(Regime regime, String critere) {
    // Mettez en minuscules le critère de recherche et les champs de régime pour une correspondance insensible à la casse
    critere = critere.toLowerCase();
    String nomRegime = regime.getNomreg().toLowerCase();
    String objectifRegime = regime.getObjectifreg().name().toLowerCase();
    String dureeRegime = regime.getDureereg().toLowerCase();
    String imcMin = String.valueOf(regime.getImcMin()).toLowerCase();
    String imcMax = String.valueOf(regime.getImcMax()).toLowerCase();
    String dateCreation = regime.getDatecreationreg().toString().toLowerCase();

    // Vérifiez si le critère de recherche correspond à l'un des champs du régime
    return nomRegime.contains(critere) ||
           objectifRegime.contains(critere) ||
           dureeRegime.contains(critere) ||
           imcMin.contains(critere) ||
           imcMax.contains(critere) ||
           dateCreation.contains(critere);
}


    }



