/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entity.Convention;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import  javafx.print.PrinterJob ; 
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import org.apache.commons.lang3.RandomStringUtils;
import services.ServiceConvention;
//import utils.Mailling;




/**
 * FXML Controller class
 *
 * @author omnia
 */
public class FXMLconventionBController implements Initializable {

    ServiceConvention sc = new ServiceConvention();

    @FXML
    private TextField tfsociete;
    
    @FXML
    private TextField tfadresse;
    
    @FXML
    private TextField tfemail;
    
    @FXML
    private TextField tftelephone;
    
    @FXML
    private TableView<Convention> tvConvention;

    

    @FXML
    private TableColumn<Convention, String> colsociete;
    
    @FXML
    private TableColumn<Convention, String> coladresse;
    
    @FXML
    private TableColumn<Convention, String> colemail;

    @FXML
    private TableColumn<Convention, String> coltelephone;
    
    @FXML
    private TableColumn<Convention, String> colstatus;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnDelete;
   
    @FXML
    private Button btnclear;
    
    @FXML
    ComboBox<String> cbstatus = new ComboBox<>();
    
    
    String selectedCategory ;

    public FXMLconventionBController() {
    }
    

 
    public void show()
    {
    ObservableList<Convention> data = FXCollections.observableArrayList(sc.getAll());
        colsociete.setCellValueFactory(new PropertyValueFactory("societe"));
        coladresse.setCellValueFactory(new PropertyValueFactory("adresse"));
        colemail.setCellValueFactory(new PropertyValueFactory("email"));
        coltelephone.setCellValueFactory(new PropertyValueFactory("telephone"));    
        colstatus.setCellValueFactory(new PropertyValueFactory("status"));  
        tvConvention.setItems(data);
     
    }
    
    
      private void clear() {
        tvConvention.getSelectionModel().clearSelection();
        tfsociete.clear();
        tfadresse.clear();
        tfemail.clear();
        tftelephone.clear();
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        cbstatus.getSelectionModel().select(-1);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    ObservableList<String> statusItems = FXCollections.observableArrayList(
    "convention déposée",
    "convention acceptée",
    "convention refusée"
    );

   cbstatus.setItems(statusItems);

   cbstatus.setOnAction(event -> {
        selectedCategory = cbstatus.getValue() ;
});
  
        show();
         
             tvConvention.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tvConvention.getSelectionModel().getSelectedItem() != null) {
                    Convention e = (Convention) tvConvention.getSelectionModel().getSelectedItem();
                    tfsociete.setText(e.getSociete());
                    tfadresse.setText(e.getAdresse());
                    tfemail.setText(e.getEmail());
                    tftelephone.setText(e.getTelephone());
                }
            }
        });
    }    

    @FXML
    private void handleMouseClicked(MouseEvent event) {
    }

    
    @FXML
    private void Modifier(ActionEvent event) {
        
      // Mailling m = new Mailling();
        if (tvConvention.getSelectionModel().getSelectedItem() != null) {
                   
            Convention c = new Convention(tvConvention.getSelectionModel().getSelectedItem().getId(),tfsociete.getText(), tfadresse.getText(), tfemail.getText(), tftelephone.getText(),selectedCategory);

            sc.modifier(c);
            
            /*if(selectedCategory.equals("convention acceptée")){
                m.sendemail(tfemail.getText());
               
            }*/
            
        ButtonType okButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous modifier cette convention?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvConvention.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Convention modifiée");
               
             }
                  
            show();
            }      
            clear();
           
          } else {
            System.out.println("Annuler");
        }  
            
        }
 
        
    @FXML
    private void Delete(ActionEvent event) {
        ButtonType okButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous supprimer cette convention?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvConvention.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Evènement supprimé");
                sc.supprimer(tvConvention.getSelectionModel().getSelectedItem().getId());
              show();
            }
            clear();
        } else {
            System.out.println("Annuler");
        }
    }
    
    @FXML
    private void Clear(ActionEvent event) {
        clear();
    }

    
        @FXML
    private void NavigateToOffreB(MouseEvent event) {
                           try {
                Parent page = FXMLLoader.load(getClass().getResource("FXMLoffreB.fxml"));
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
    
      