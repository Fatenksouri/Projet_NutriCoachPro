/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Offre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author omnia
 */
public class FXMLgridController implements Initializable {
    @FXML
    private ImageView imagew;

    @FXML
    private Label nom;

    
        @FXML
    private Label type;
    @FXML
    private Label date;
    
    
    @FXML
    private Label date2;
    

    @FXML
    private Label sosciete;

    @FXML
    private Label prix;

    
    
    private Offre ev;
    
       // private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
           
    
    }    
    public void setData(Offre ev) throws FileNotFoundException{
              
        this.ev=ev;
        nom.setText(ev.getNom_du_soin());
        type.setText("Type : "+ev.getCategorie());
        date.setText(String.valueOf("From "+ev.getDate_debut()));
        date2.setText(String.valueOf(" To "+ev.getDate_fin()));
        sosciete.setText(String.valueOf(ev.getConvention_sosciete()));
        prix.setText(String.valueOf(ev.getPrix())+" DT");

     
    try {
                  InputStream stream = new FileInputStream("C:\\Users\\omnia\\Downloads\\convention_finale\\uploads\\"+ev.getImage());
                   Image image = new Image(stream);
             
                      imagew.setImage(image);

              } catch (FileNotFoundException ex) {
                  System.out.println(ex.getMessage());
              }
        
    
    
    }
    @FXML
 private void reserver(ActionEvent event) {
          

             this.ev=ev;
            

  
 }
    
}
