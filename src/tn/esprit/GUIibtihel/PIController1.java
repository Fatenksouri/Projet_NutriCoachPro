
package tn.esprit.GUIibtihel;
import API.Notification_ac;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.entity.Activite;
import tn.esprit.entity.Objectif;
import tn.esprit.entity.Planning;

import tn.esprit.entity.TypeActivite;
import tn.esprit.services.ServiceActivite;

import tn.esprit.services.ServicePlanning;
import API.MAIL;
import API.SMS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PIController1 implements Initializable {

    @FXML
    private Button btnMenuActivite;
    @FXML
    private Button btnMenuPlanning;
    @FXML
    private TableColumn<Planning, Integer> col_id_pl;
    @FXML
    private TableColumn<Planning, String> col_nom_pl;
    @FXML
    private TableColumn<Planning,Integer> col_duree_pl;
    @FXML
    private Pane pnPlanning;
    @FXML
    private TableView<Planning> tvPlanning;
    @FXML
    private Pane pnActivite;
    @FXML
    private TableView<Activite> tvActivite;
    @FXML
    private Pane pnFormPlanning;
    @FXML
    private TextField tfnompl;
    @FXML
    private TextField tfdurepl;
    @FXML
    private Button btnconfAoutPL;
    @FXML
    private Label lbTitleAjoutPlanning;
    @FXML
    private Label lbTitleModifierPlanning;
    @FXML
    private Label lbidpl;
    @FXML
    private Button btnAjouterPlanning1;
    @FXML
    private TableColumn<Activite, Integer> col_id_AC;
    @FXML
    private TableColumn<Activite, String> col_nom_AC;
    @FXML
    private TableColumn<Activite, Float> col_duree_AC;
    @FXML
    private TableColumn<Activite, Integer> col_REP_AC;
    @FXML
    private TableColumn<Activite, String> col_DESC_AC;
    @FXML
    private TableColumn<Activite, String> col_OBJ_AC;
    @FXML
    private TableColumn<Activite,String > col_TYPE_AC;
    @FXML
    private Label lbidac;
 
    @FXML
    private ChoiceBox<TypeActivite> chR_typ_AC;
    @FXML
    private Button generer_ac;
    @FXML
    private ChoiceBox<Objectif> chR_obj_AC;
    @FXML
    private Button reset_ac;
    @FXML
    private Button ActivitePlanning;

@FXML
private ChoiceBox<Planning> chnom_pl; // Gardez ChoiceBox<Planning> comme type
    @FXML
    private ImageView imd_act;
   
  
 
    @FXML
    private TableColumn<Activite, String> COLNOMAC;
    @FXML
    private TableColumn<Activite, Integer> COLDUREEAC;
    @FXML
    private TableColumn<Activite, Integer> COLREPAC;
    @FXML
    private TableView<Activite> tv_Planning_to_Activite;
    @FXML
    private Label path_img;
    @FXML
    private Button mail_ac;
    @FXML
    private Pane pnmail_ac;
    @FXML
    private TextField toField_ac;
    @FXML
    private TextField subjectField_ac;
    @FXML
    private TextField messageArea_ac;
    @FXML
    private Button retour1_ac;
    @FXML
    private Button envoi_mail_ac;
    @FXML
    private Button evaluerA_ac;
    private HBox h_rating_ac;
    @FXML
    private Rating ratingA_ac;
    @FXML
    private Pane pn_rating_ac;
    @FXML
    private Button btn_rat_ac;
    @FXML
    private Button retour2_ac;
  
    @FXML
    private Button like_ac;
    @FXML
    private Button dislike_ac;
    @FXML
    private Label lb_like_ac;
    @FXML
    private Label lb_dislike_ac;
    @FXML
    private Button btnMenuHome_ac;
    @FXML
    private Button retour2_ac1;
    @FXML
    private Button btnAjouterPlanning11;
    @FXML
    private Label lb_acp;

/**
 * Initializes the controller class.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
    pnActivite.toFront();
    fnPlanningShow();
   
    fnActiviteShow();
    fnActivitepourplanningShow(0);
    
    ServicePlanning sr = new ServicePlanning();
    //ch_ob_ac.setItems(FXCollections.observableArrayList(Objectif.values()));
    //ch_ty_ac.setItems(FXCollections.observableArrayList(TypeActivite.values()));
   
    chR_obj_AC.setItems(FXCollections.observableArrayList(Objectif.values()));
    chR_typ_AC.setItems(FXCollections.observableArrayList(TypeActivite.values()));
    
 
    

    List<Planning> planningList = sr.affihcer();

    // Créez une ObservableList de Planning
    ObservableList<Planning> planningObservableList = FXCollections.observableArrayList(planningList);

    // Configurez un StringConverter pour afficher les noms de planning dans la ChoiceBox
    chnom_pl.setConverter(new StringConverter<Planning>() {
        @Override
        public String toString(Planning planning) {
            return planning.getNom_pl();
        }

        @Override
        public Planning fromString(String string) {
            // Cette méthode est nécessaire en raison des exigences de l'interface StringConverter
            return null;
        }
    });

    // Remplissez la ChoiceBox avec les objets Planning
    chnom_pl.setItems(planningObservableList);
    
      

    
}

    
    
    
    //////////////////////////Planning//////////////////////////////////////////////////
    
    
     public void fnPlanningShow(){
          ServicePlanning sr=new ServicePlanning();
         ObservableList<Planning> list = FXCollections.observableArrayList(sr.affihcer());;
    
     
        col_id_pl.setCellValueFactory(new PropertyValueFactory<>("id_pl")); 
        col_nom_pl.setCellValueFactory(new PropertyValueFactory<>("nom_pl"));       
        col_duree_pl.setCellValueFactory(new PropertyValueFactory<>("dure_pl"));        
        

            tvPlanning.setItems(list);
     }
     
   
     @FXML
    private void fnMenuPlanning(ActionEvent event) {
        pnPlanning.toFront();
    }
   
 
    @FXML

private void fnSelectedPlanning(MouseEvent event) {
    Planning rec = tvPlanning.getSelectionModel().getSelectedItem();
    if (rec != null) {
        int planningId = rec.getId_pl();
        
        // Mettez à jour les détails du planning.
        lbidpl.setText(Integer.toString(planningId));
        
        // Affichez les activités associées au planning sélectionné.
        fnActivitepourplanningShow(planningId);
    }
}


    @FXML
    private void fnAjoutPlanning(ActionEvent event) {
        pnFormPlanning.toFront();
        lbTitleAjoutPlanning.setVisible(true);
        lbTitleModifierPlanning.setVisible(false);
    }

    private void fnModifyPlanning(ActionEvent event) {
         pnFormPlanning.toFront();
        lbTitleAjoutPlanning.setVisible(false);
        lbTitleModifierPlanning.setVisible(true);
        ServicePlanning PL =new ServicePlanning();
        Planning P=PL.getById(Integer.parseInt(lbidpl.getText()));
        tfnompl.setText(P.getNom_pl());
        tfdurepl.setText(String.valueOf(P.getDure_pl()));;
     
    }

    private void fnSupprimerPlanning(ActionEvent event) {
        // Récupérez le texte de la Label lbidpl
        String idPlanningText = lbidpl.getText();

        // Assurez-vous que le texte n'est pas vide
        if (!idPlanningText.isEmpty()) {
            try {
                // Convertissez le texte en un entier (ID du planning)
                int planningId = Integer.parseInt(idPlanningText);

                ServicePlanning PL = new ServicePlanning();
                PL.supprimer(planningId);
                fnPlanningShow(); // Met à jour la liste des plannings après suppression
            } catch (NumberFormatException e) {
                // Gérez l'exception si la conversion échoue (par exemple, affichez un message d'erreur)
                System.err.println("Erreur de conversion de l'ID du planning : " + e.getMessage());
            }
        } else {
            // Gérez le cas où la Label est vide (par exemple, affichez un message d'erreur)
            System.err.println("L'ID du planning est vide.");
        }
        }
    
 

    
    
       @FXML
    private void fnConfPL(ActionEvent event) {
        ServicePlanning PL =new ServicePlanning();
        System.out.println(lbTitleAjoutPlanning.isVisible());
        System.out.println(lbidpl.getText());
        if(lbTitleAjoutPlanning.isVisible()){
            Planning P =new Planning();
            
            
            
            String ERROR_MSG="";
            if("".equals(tfnompl.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfdurepl.getText())){
                ERROR_MSG="Le champs duree de dois pas pas étre null";
            }
         
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormPlanning.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnFormPlanning.toFront();
        }}else{
            P.setNom_pl(tfnompl.getText());
            P.setDure_pl(Integer.parseInt(tfdurepl.getText()));
            
               PL.ajouter(P); 
             tfnompl.setText("");
            tfdurepl.setText("");
            
            pnPlanning.toFront();
            
             fnPlanningShow();
            }
            
            
            
            
        }else{
            Planning P =new Planning();
           
            String ERROR_MSG="";
            if("".equals(tfnompl.getText())){
                ERROR_MSG="Le champs nom de dois pas pas étre null";
            }
            if("".equals(tfdurepl.getText())){
                ERROR_MSG="Le champs description de dois pas pas étre null";
            }
            
            if(!"".equals(ERROR_MSG)){
            Stage window = (Stage)pnFormPlanning.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(window);
            
            alert.getDialogPane().setContentText(ERROR_MSG);
            alert.getDialogPane().setHeaderText("");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK) {
                
                pnPlanning.toFront();
        }}else{
               
           P.setNom_pl(tfnompl.getText());
           P.setDure_pl(Integer.parseInt(tfdurepl.getText()));
           P.setId_pl(Integer.parseInt(lbidpl.getText()));
               PL.modifier(P); 
              
               tfnompl.setText("");
               tfdurepl.setText("");
            
            pnPlanning.toFront();
            
        fnPlanningShow();
            }
            
        }
    }
    ////////////////////////////////ACTIVITE////////////////////////////////////////////
     public void fnActiviteShow() {
    ServiceActivite sr = new ServiceActivite();
    ObservableList<Activite> list = FXCollections.observableArrayList(sr.affihcer());

    col_id_AC.setCellValueFactory(new PropertyValueFactory<>("id_ac")); 
    col_nom_AC.setCellValueFactory(new PropertyValueFactory<>("nom_ac"));       
    col_duree_AC.setCellValueFactory(new PropertyValueFactory<>("duree_ac"));        
    col_REP_AC.setCellValueFactory(new PropertyValueFactory<>("repetition_ac"));   
    col_OBJ_AC.setCellValueFactory(new PropertyValueFactory<>("objectif_ac"));  
    col_TYPE_AC.setCellValueFactory(new PropertyValueFactory<>("type_ac"));
    col_DESC_AC.setCellValueFactory(new PropertyValueFactory<>("description_ac"));
    

    tvActivite.setItems(list);
}


 @FXML
    private void fnMenuActivite(ActionEvent event) {
       
        pnActivite.toFront();
    }
 @FXML
    private void fnSelectedActivite(MouseEvent event) {
    Activite selectedActivity = tvActivite.getSelectionModel().getSelectedItem();

    if (selectedActivity != null) {
        int activite_id = selectedActivity.getId_ac();
        ServiceActivite sa = new ServiceActivite();
        // Obtenir le chemin d'accès à l'image de l'activité
        String imagePath = selectedActivity.getImagePath();
        Activite A = sa.getById(activite_id);

        // Ajouter cette ligne pour afficher le chemin d'accès dans la console
        System.out.println("Chemin d'accès à l'image : " + imagePath);

        if (imagePath != null && !imagePath.isEmpty()) {
            path_img.setText(A.getImagePath());
            // Charger l'image à partir du chemin d'accès
            File file = new File(A.getImagePath());
            Image image = new Image(file.toURI().toString());
            
            // Afficher l'image dans l'ImageView
         //   iv_act_ac.setImage(image);
        } else {
            // Effacer l'ImageView si aucune image n'est associée
           // iv_act_ac.setImage(null);
        }

        // Afficher l'ID de l'activité sélectionnée
        lbidac.setText(Integer.toString(selectedActivity.getId_ac()));
    } else {
        // Effacer l'ImageView si aucune activité n'est sélectionnée
       // iv_act_ac.setImage(null);
        // Effacer l'ID de l'activité sélectionnée
        lbidac.setText("");
    }
}



   
  
    @FXML
    public void rechercherActivites() {
    ServiceActivite activiteService = new ServiceActivite();
    List<Activite> activites = new ArrayList<>();
    
    TypeActivite typeSelectionne = chR_typ_AC.getValue();
    Objectif objectifSelectionne = chR_obj_AC.getValue();
    
    if (typeSelectionne != null && objectifSelectionne != null) {
        // Recherche par type et objectif
        activites = activiteService.afficherParTypeEtObjectif(typeSelectionne, objectifSelectionne);
    } else if (typeSelectionne != null) {
        // Recherche par type seulement
        activites = activiteService.afficherParType(typeSelectionne);
   
    } else {
        // Afficher un message d'erreur si rien n'est sélectionné
        System.out.println("Veuillez sélectionner un type ou un objectif pour la recherche.");
        // Vous pouvez également afficher un message d'erreur à l'utilisateur dans l'interface utilisateur.
    }

    // Mettez à jour votre interface utilisateur avec les activités trouvées
    afficherActivites(activites);
}
@FXML
private void resetAffichage(ActionEvent event) {
    // Effacez tout texte ou valeurs des champs de texte ou des ChoiceBox
  
    // Appelez la méthode pour afficher les activités par défaut (ou affichez toutes les activités)
    fnActiviteShow();
}



    
    


  public void afficherActivites(List<Activite> activites) {
    ObservableList<Activite> list = FXCollections.observableArrayList(activites);

    col_id_AC.setCellValueFactory(new PropertyValueFactory<>("id_ac")); 
    col_nom_AC.setCellValueFactory(new PropertyValueFactory<>("nom_ac"));       
    col_duree_AC.setCellValueFactory(new PropertyValueFactory<>("duree_ac"));        
    col_REP_AC.setCellValueFactory(new PropertyValueFactory<>("repetition_ac"));   
    col_OBJ_AC.setCellValueFactory(new PropertyValueFactory<>("objectif_ac"));  
    col_TYPE_AC.setCellValueFactory(new PropertyValueFactory<>("type_ac"));
    col_DESC_AC.setCellValueFactory(new PropertyValueFactory<>("description_ac"));       

    tvActivite.setItems(list);
}






   ////////////////////plannongActivite /////////////////////////////////////////:


  @FXML
private void fnAffecterActiviteAuPlanning(ActionEvent event) {
    
    // Récupérez l'ID de l'activité à partir du label (c'est ce que vous faites déjà)
    int activiteId = Integer.parseInt(lbidac.getText());

    // Récupérez le planning sélectionné par l'utilisateur
    Planning selectedPlanning = chnom_pl.getValue();

    if (selectedPlanning != null) {
        // Récupérez l'ID du planning
         ServicePlanning servicePlanning = new ServicePlanning();
        int planningId = selectedPlanning.getId_pl();
        boolean activiteExisteDeja = servicePlanning.ajouterActiviteAPlanning(planningId, activiteId);
        // Ensuite, ajoutez l'activité au planning en utilisant votre service
       
        if (activiteExisteDeja) {
     
       
        // Rafraîchissez votre TableView pour afficher les activités mises à jour
      pnActivite.toFront();
         afficherMessageSucces("Ajout avec succès");
   } else {
            // Si l'activité existe déjà, affichez un message d'erreur
            afficherMessageErreur("L'activité existe déjà dans le planning");
        }
        // Vous pouvez également afficher un message de succès ou effectuer d'autres actions ici
    } else {
        System.out.println("erreur");// Affichez un message d'erreur si l'utilisateur n'a pas sélectionné un planning
    }
}
 
private void afficherMessageSucces(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
private void afficherMessageErreur(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
  public void fnActivitepourplanningShow(int planningId) {
    ServicePlanning sr = new ServicePlanning();
    ObservableList<Activite> list = FXCollections.observableArrayList(sr.getActivitesPourPlanning(planningId));

    COLNOMAC.setCellValueFactory(new PropertyValueFactory<>("nom_ac"));
    COLDUREEAC.setCellValueFactory(new PropertyValueFactory<>("duree_ac"));
    COLREPAC.setCellValueFactory(new PropertyValueFactory<>("repetition_ac"));

    tv_Planning_to_Activite.setItems(list); 
  }

    @FXML
    private void clickMailActivite(ActionEvent event) {
     pnmail_ac.toFront();
    }
    @FXML
    private void envoyerMail_ac(ActionEvent event) {
            String to = toField_ac.getText();
        String subject = subjectField_ac.getText();
        String message = messageArea_ac.getText();

        try {
            MAIL.envoyer(to, subject, message);
            // Affichez un message de confirmation à l'utilisateur.
            System.out.println("E-mail envoyé avec succès.");
        } catch (Exception e) {
            // Gérez les exceptions liées à l'envoi de l'e-mail, par exemple, affichez un message d'erreur.
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
        
    }

    @FXML
    private void retour1_ac(ActionEvent event) {
        pnPlanning.toFront();
    }

    @FXML
    private void rating_acShow(ActionEvent event) {
        
           pn_rating_ac.toFront();
    Activite selectedActivite = tvActivite.getSelectionModel().getSelectedItem();
    if (selectedActivite != null) {
        ratingA_ac.setRating(selectedActivite.getRating_ac());
    
    }
    }
  @FXML
    private void enregistrer_ratting_ac(ActionEvent event) {
         Activite selectedActivite = tvActivite.getSelectionModel().getSelectedItem();
         if (selectedActivite != null) {
        double newRating = ratingA_ac.getRating();
        selectedActivite.setRating_ac((int) newRating);
        ServiceActivite A1 = new ServiceActivite();
        A1.updateRating(selectedActivite); // Mettre à jour la note dans la base de données
        
        
      if (newRating>3){
      
             SMS twilioService = new SMS();
            
             twilioService.sendSMS();
             showAlert("Notation enregistrée", "La notation a été enregistrée avec succès.");
            pnPlanning.toFront();
             
      }
      
    }
    }




    @FXML
    private void retour2_ac(ActionEvent event) {
        pnActivite.toFront();
    }

 private int likeCount = 0;
private int dislikeCount = 0;

    @FXML
    private void increment_like_ac(ActionEvent event) {
    likeCount++;
    lb_like_ac.setText(""+likeCount);
    
    // Désactive le bouton "Like" pour empêcher un deuxième clic.
    like_ac.setDisable(true);
    dislike_ac.setDisable(false);

    Planning selectedPlanning = tvPlanning.getSelectionModel().getSelectedItem();
    if (selectedPlanning != null) {
        selectedPlanning.setNb_like(likeCount);
        ServicePlanning p = new ServicePlanning();
        p.updateLike(selectedPlanning);
    }
}

    @FXML
    private void decremente_like_ac(ActionEvent event) {
    dislikeCount++;
    lb_dislike_ac.setText(""+ dislikeCount);
    
    // Désactive le bouton "Dislike" pour empêcher un deuxième clic.
    dislike_ac.setDisable(true);
    like_ac.setDisable(false);

    Planning selectedPlanning = tvPlanning.getSelectionModel().getSelectedItem();
    if (selectedPlanning != null) {
        selectedPlanning.setNb_dislike(dislikeCount);
        ServicePlanning p = new ServicePlanning();
        p.updatedisLike(selectedPlanning);
    }
}

    @FXML
    private void fnMenuHome_acc(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HOME.fxml"));
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
private void fnsupprimerActivite_Planning(ActionEvent event) {
    Activite rec = tv_Planning_to_Activite.getSelectionModel().getSelectedItem();
    if (rec != null) {
        int activiteId = rec.getId_ac();
        
        // Récupérez l'ID du planning à partir de lbidpl
        int planningId = Integer.parseInt(lbidpl.getText());

        // Assurez-vous que les ID ne sont pas vides
        if (activiteId > 0 && planningId > 0) {
            ServicePlanning P = new ServicePlanning();
            // Appelez la méthode pour supprimer l'activité du planning
            P.supprimerActiviteDePlanning(planningId, activiteId);

            // Affichez une alerte de suppression réussie
            showAlert("Suppression réussie", "L'activité a été supprimée avec succès.");
            fnActivitepourplanningShow(planningId);
            pnPlanning.toFront();
            // Mettez à jour votre interface utilisateur ou effectuez d'autres actions nécessaires
        }
    }
}

// Méthode pour afficher une alerte
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    }

   
    





 





