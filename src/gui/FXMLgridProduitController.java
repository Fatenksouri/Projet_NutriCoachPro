/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Panier;
import entity.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ServicePanier;


/**
 * FXML Controller class
 *
 * @author ksouri
 */
public class FXMLgridProduitController implements Initializable {
    @FXML
    private ImageView imagew;

    @FXML
    private Label nom;

    
        @FXML
    private Label type;
    @FXML
    private Label prix;
    

    

    @FXML
    private Label quantite;

    @FXML
    private Label details;
 
 @FXML
    private Button buy;

        
        private Produit ev;
        
        private Panier panier;
    
       // private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
           
    
    }    
    public void setData(Produit ev) throws FileNotFoundException{
        

       
   
        
        
        this.ev=ev;
        nom.setText(ev.getNom());
        type.setText("Catégorie : "+ev.getCategory_type());
        details.setText(ev.getDetails());
        prix.setText(String.valueOf(ev.getPrix())+" DT");
        quantite.setText(String.valueOf("Quantité :"+ev.getQuantite()));

     
    try {
                  InputStream stream = new FileInputStream("C:\\Users\\faten\\Downloads\\finale_faten - changé\\finale_faten - changé\\Faten_finale\\uploads\\"+ev.getImage());
                   Image image = new Image(stream);
             
                      imagew.setImage(image);

              } catch (FileNotFoundException ex) {
                  System.out.println(ex.getMessage());
              }
        
    }
    
public void setPanier(Panier panier) throws FileNotFoundException{
    this.panier = panier; 
    nom.setText(panier.getId_produit().getNom());
    type.setVisible(false); 
    buy.setVisible(false);      
    details.setText(panier.getId_produit().getDetails());
    prix.setText(String.valueOf(panier.getPrix_total())+" DT");
    quantite.setText(String.valueOf("Quantité :"+panier.getQuantite()));

    try {
        InputStream stream = new FileInputStream("C:\\Users\\faten\\Downloads\\finale_faten - changé\\finale_faten - changé\\Faten_finale\\uploads\\"+panier.getId_produit().getImage());
        Image image = new Image(stream);
        imagew.setImage(image);
    } catch (FileNotFoundException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    
            @FXML
private void addToPanier(ActionEvent event) {
    
    ServicePanier sp = new ServicePanier();
    Notification("NutriCoach Pro","Produit ajouté au panier");
   Panier e = new Panier(ev, 1);
    sp.ajouter(e);

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
