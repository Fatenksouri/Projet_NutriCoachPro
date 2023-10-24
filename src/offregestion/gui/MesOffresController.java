/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offregestion.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import offregestion.offre;
import offregestion.reservation;
import offregestion.serviceoffre;
import offregestion.servicereservation;
import offregestion.typeoffre;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PageLayout;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;*/
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
/**
 * FXML Controller class
 *
 * @author jaafr
 */
public class MesOffresController implements Initializable {

    @FXML
    private TableView<offre> tablemesoffres;
    @FXML
    private TableColumn<offre , String> txtnom;
    @FXML
    private TableColumn<offre, String> txtdate;
    @FXML
    private TableColumn<offre, String> txtheure_debut;
    @FXML
    private TableColumn<offre, String> txtheure_fin;
    @FXML
    private TableColumn<offre, Double> txtprix;
    @FXML
    private TableColumn<offre, String> txtdescription;
    @FXML
    private TableColumn<offre, String> txttype;
    @FXML
    private Button btnchercher;
    @FXML
    private TextField txachecher;
    @FXML
    private Button btnreserver;
    private int userId; // Vous devrez définir cet ID d'utilisateur
    
    private serviceoffre serviceOffre;
    private servicereservation serviceReservation;
    private String selectedUserName;
    private String selectedDateOffre ;

    private int selectedOfferId;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnmail;
    
    
    /**
     * Initializes the controller class.
     */
     public void afficherOffres(List<offre> offres) {
    tablemesoffres.getItems().clear(); // Effacez toutes les entrées existantes dans la table

    for (offre o : offres) {
        tablemesoffres.getItems().add(o);
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          serviceoffre so = new serviceoffre();
        servicereservation  sr= new servicereservation();
        // Initialisez les services
    serviceOffre = new serviceoffre();
    serviceReservation = new servicereservation();

    // Supposons que vous avez un mécanisme d'authentification pour obtenir l'utilisateur actuel
   // userId = getCurrentUserId(); // Obtenez l'ID de l'utilisateur actuel
   // selectedUserName = getCurrentUserName(); // Obtenez le nom d'utilisateur actuel

    // ... Le reste de votre code d'initialisation
        // Associez les colonnes de la table aux propriétés de l'objet offre
       txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        txtdate.setCellValueFactory(new PropertyValueFactory<>("date_offre"));
        txtheure_debut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
        txtheure_fin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
        txtprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        txttype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        // Gérez le clic sur la table des offres
       // Gérez le clic sur la table des offres
      tablemesoffres.setOnMouseClicked(event -> {
    offre selectedOffer = tablemesoffres.getSelectionModel().getSelectedItem();
    if (selectedOffer != null) {
        selectedOfferId = selectedOffer.getId_offre();
        selectedDateOffre = selectedOffer.getDate_offre(); // Mettez à jour selectedDateOffre
    }
});

            // Gérez le clic sur le bouton de recherche
     btnchercher.setOnAction(event -> {
    String typeRecherche = txachecher.getText();
    typeoffre typeRechercheEnum = typeoffre.valueOf(typeRecherche);

    List<offre> offresFiltrees = serviceOffre.chercherParType(typeRechercheEnum);

    // Triez les offres filtrées par date
    offresFiltrees.sort(Comparator.comparing(offre::getDate_offre));

    // Affichez les offres filtrées dans la table
    tablemesoffres.getItems().setAll(offresFiltrees);
});

     

       
       
        
        // Gérez le clic sur le bouton de réservation
btnreserver.setOnAction(event -> {
   if (selectedOfferId != 0) {
                // Créez une nouvelle réservation
         reservation newReservation = new reservation();
         newReservation.setUserId(userId); // Utilisez l'ID de l'utilisateur actuel
         newReservation.setId_offre(selectedOfferId);
         newReservation.setUserName(selectedUserName);
         newReservation.setDate_offre(selectedDateOffre);

                // Ajoutez d'autres détails de réservation (date, etc.)
                
                // Ajoutez la réservation à la base de données
         serviceReservation.ajouter(newReservation);
         }
        });
        
    
    }  
/*  @FXML
private void handlePdfButtonAction(ActionEvent event) {
    offre selectedOffer = tablemesoffres.getSelectionModel().getSelectedItem();
    if (selectedOffer != null) {
        String offerDetails = "Nom de l'offre : " + selectedOffer.getNom_offre() + "\n"
                + "Date de l'offre : " + selectedOffer.getDate_offre() + "\n"
                + "Heure de début : " + selectedOffer.getHeure_debut() + "\n"
                + "Heure de fin : " + selectedOffer.getHeure_fin() + "\n"
                + "Prix : " + selectedOffer.getPrix() + "\n"
                + "Description : " + selectedOffer.getDescription() + "\n"
                + "Type : " + selectedOffer.getType();

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            //contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Détails de l'offre :");
            contentStream.endText();

            //contentStream.setFont(PDType1Font.HELVETICA, 12);

            String[] lines = offerDetails.split("\n");
            float y = 680;
            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.showText(line);
                contentStream.endText();
                y -= 20;
            }

            contentStream.close();

            File file = new File("offre_details.pdf");
            document.save(file);
            document.close();

            System.out.println("Fichier PDF généré avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Aucune offre sélectionnée.");
    }
}*/
/*@FXML
private void handlePdfButtonAction(ActionEvent event) {
    PrinterJob job = PrinterJob.createPrinterJob();

    if (job != null) {
        job.showPrintDialog(this.tablemesoffres.getScene().getWindow()); // Utilisez la fenêtre principale de la TableView

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

    if (job != null) {
    job.showPrintDialog(this.tablemesoffres.getScene().getWindow());

    if (job.printPage(pageLayout, this.tablemesoffres)) {
        job.endJob();
    }
}

        if (job.printPage(pageLayout, this.tablemesoffres)) {
            job.endJob();
        }
    }
}*/
/*offre Offre = new offre() ;
   public class PDFExporter {
    public static void handlePdfButtonAction(tableview<Offre> tablemesoffres) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.showPrintDialog(tablemesoffres.getScene().getWindow());
            if (success) {
                Printer printer = job.getPrinter();
                PageOrientation pageOrientation = PageOrientation.PORTRAIT; // Portrait or Landscape
                Paper paper = PrintUtil.createPaper("A4"); // Change paper size as needed
                double width = paper.getWidth();
                double height = paper.getHeight();

                Printer.MarginType marginType = Printer.MarginType.HARDWARE_MINIMUM; // You can adjust margins

                javafx.print.PageLayout pageLayout = printer.createPageLayout(paper, pageOrientation, marginType);

                if (job.printPage(pageLayout, tablemesoffres)) {
                    job.endJob();
                }
            }
        }
    }} */
  /* @FXML
private void handlePdfButtonAction(ActionEvent event) {
    // ... Votre code précédent

    try {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Chargez les polices Helvetica et Helvetica-Bold à partir des fichiers TTF
        PDType0Font fontHelvetica = PDType0Font.load(document, new File("chemin_vers_helvetica.ttf"));
        PDType0Font fontHelveticaBold = PDType0Font.load(document, new File("chemin_vers_helvetica_bold.ttf"));

        contentStream.setFont(fontHelvetica, 12);

        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Détails de l'offre :");
        contentStream.endText();

        contentStream.setFont(fontHelvetica, 12);

        // ... Le reste de votre code

        contentStream.close();

        File file = new File("offre_details.pdf");
        document.save(file);
        document.close();

        System.out.println("Fichier PDF généré avec succès.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}*/

@FXML
private void handleReserverButtonAction(ActionEvent event) {
    offre selectedOffer = tablemesoffres.getSelectionModel().getSelectedItem();
    if (selectedOffer != null) {
        int selectedOfferId = selectedOffer.getId_offre(); // Obtenez l'ID de l'offre sélectionnée

        // Vous devez implémenter getCurrentUserId() pour obtenir l'ID de l'utilisateur actuel
       // int userId = getCurrentUserId(); 

        // Vous devez implémenter getCurrentUserName() pour obtenir le nom de l'utilisateur actuel
       // String userName = getCurrentUserName();

        // Obtenez la date de réservation actuelle
     //   date reservationDate = new date(); // Vous pouvez personnaliser la date selon vos besoins

        // Créez une nouvelle réservation
        reservation newReservation = new reservation();
        newReservation.setUserId(userId);
      //  newReservation.setid_offre(selectedOfferId);
      //  newReservation.setUserName(userName);
       // newReservation.setReservationDate(reservationDate);

        // Ajoutez la réservation à la base de données en utilisant votre service de réservation
        serviceReservation.ajouter(newReservation);

        // Vous pouvez également mettre à jour votre interface utilisateur pour refléter la réservation
        // Par exemple, désactiver le bouton de réservation ou afficher un message de confirmation
    } else {
        // Cas où aucune offre n'est sélectionnée
        // Affichez un message d'erreur à l'utilisateur (par exemple, utilisez une boîte de dialogue)
        showAlert("Aucune offre sélectionnée", "Veuillez sélectionner une offre avant de réserver.");
    }
}

// Méthode pour afficher une boîte de dialogue d'alerte
private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
    @FXML
private void handlePdfButtonAction(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
        PDFExporter pdfExporter = new PDFExporter(); // Instanciez la classe PDFExporter
        pdfExporter.exportTableViewToPDF(tablemesoffres, file); // Appelez la méthode sur l'instance
        showInformationDialog("Export PDF", "Table View exportée en PDF avec succès.", "Le fichier PDF a été enregistré avec succès.");
    } else {
        showInformationDialog("Export PDF", "Export PDF annulé", "Aucun fichier PDF n'a été enregistré.");
    }
}

private void showInformationDialog(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}
public class PDFExporter {
    
    public void exportTableViewToPDF(TableView<offre> tablemesoffres, File file) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            PdfPTable pdfPTable = new PdfPTable(tablemesoffres.getColumns().size());
            pdfPTable.setWidthPercentage(100);

            // Adding headers
            for (int i = 0; i < tablemesoffres.getColumns().size(); i++) {
                pdfPTable.addCell(new Paragraph(tablemesoffres.getColumns().get(i).getText()));
            }

            // Adding data
            ObservableList<offre> items = tablemesoffres.getItems();
            for (offre item : items) {
    for (int i = 0; i < tablemesoffres.getColumns().size(); i++) {
        pdfPTable.addCell(new PdfPCell(new Paragraph(tablemesoffres.getColumns().get(i).getCellData(item).toString())));
    }
}

            document.add(pdfPTable);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }} }
  