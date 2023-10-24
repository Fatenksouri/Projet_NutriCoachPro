/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Chunk;
import entity.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.ServicePanier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author ksouri
 *
 */
public class PanierController implements Initializable {

   @FXML 
   private Label prixtot;
            
    @FXML
    private GridPane grid;


    private float prix_total = 0;
    
 private List<Panier> o = new ArrayList<>();   
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServicePanier p = new ServicePanier();
       
        o.addAll(p.getPaniersByUserId(1));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < o.size(); i++) {
                prix_total+=o.get(i).getPrix_total();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLgridProduit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                FXMLgridProduitController FXMLgrid = fxmlLoader.getController();
                FXMLgrid.setPanier(o.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(7));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         
        
prixtot.setText(String.valueOf(prix_total)+" DT");
     
    
    
    }

@FXML
private void OnClickedPrint(ActionEvent event) throws FileNotFoundException {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("product_list.pdf"));
        document.open();

       
        Paragraph title = new Paragraph("Facture");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        for (Panier panier : o) {
            document.add(new Paragraph("Produit : " + panier.getId_produit().getNom()));
            document.add(new Paragraph("Total : " + panier.getPrix_total() + " DT"));
      
            document.add(Chunk.NEWLINE); 
        }
    document.add(new Paragraph("Total : " + prix_total + " DT"));    
    Notification("NutriCoach Pro","Panier téléchargé avec succès");
    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
    } finally {
        document.close();
    }
}  


                public void Notification(String header,String msg){
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			Notifications notify = Notifications.create().title(header)
					.text(msg)
					.hideAfter(javafx.util.Duration.seconds(4))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showInformation();
		}
	}); 


}
}
