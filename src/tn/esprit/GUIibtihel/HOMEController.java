/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.GUIibtihel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HOMEController implements Initializable {

    @FXML
    private Label lb_G_AC;
    @FXML
    private Label lb_G_prg;
    @FXML
    private Label lb_G_offre;
    @FXML
    private Label lb_G_RG;
    @FXML
    private Label lb_G_canv;
    @FXML
    private Label lb_G_stor;
    @FXML
    private Button exit_home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void direction_activite(MouseEvent event) {
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("PI.fxml"));
                 Parent root = loader.load();

            // Create a new stage
                 Stage newStage = new Stage();

            // Set the scene for the new stage
                 Scene scene = new Scene(root);
            newStage.setScene(scene);

            // Show the new stage
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void direction_Programmme(MouseEvent event) {
    }

    @FXML
    private void diection_offre(MouseEvent event) {
    }

    @FXML
    private void direction_Regime(MouseEvent event) {
    }

    @FXML
    private void direction_canvension(MouseEvent event) {
    }

    @FXML
    private void direction_str(MouseEvent event) {
        
    }

    @FXML
    private void retour_a_auth(ActionEvent event) {
        
    }
    
}
