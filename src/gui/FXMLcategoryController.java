/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Category;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ServiceCategory;


/**
 * FXML Controller class
 *
 * @author ksouri
 */
public class FXMLcategoryController implements Initializable {

    
    @FXML
    private TextField tfType;
    
        @FXML
    private TableView<Category> tvCategory;

        
    @FXML
    private TableColumn<Category, String> colType;
    
      @FXML
    private Button btnlogout;
        
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    
      @FXML
    private void handleMouseClicked(MouseEvent event) {
    }
                ServiceCategory se = new ServiceCategory();
             
    @FXML
    private Button btnclear;
    @FXML
    private Button imprimer;
        @FXML
    private TextField filterField;
    
    public FXMLcategoryController(){
        
    }
    
    
        public void show()
    {
    ObservableList<Category> data = FXCollections.observableArrayList(se.getAll());
        colType.setCellValueFactory(new PropertyValueFactory("type"));
    tvCategory.setItems(data);
     
    }
        
              private void clear() {
        tvCategory.getSelectionModel().clearSelection();
        tfType.clear();
        btnInsert.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
                     tvCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tvCategory.getSelectionModel().getSelectedItem() != null) {
                    Category e = (Category) tvCategory.getSelectionModel().getSelectedItem();
                    tfType.setText(e.getType());
                    btnInsert.setDisable(true);

                }
            }
        });
    }    
    
    
        @FXML
    private void Ajout(ActionEvent event) {

               if (tfType.getText() == null || tfType.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("Veuillez remplir le champ de type.");
            dialogW.showAndWait();
        } else {
            
            Category e = new Category(tfType.getText());
            se.ajouter(e);
        }

       show();
        clear();
        
        
        
        
    }
    
         
        
            @FXML
    private void Modifier(ActionEvent event) {
        if (tvCategory.getSelectionModel().getSelectedItem() != null) {
                        Category e = new Category(tvCategory.getSelectionModel().getSelectedItem().getId(),tfType.getText());

            se.modifier(e);
            
          ButtonType okButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous modifier cette catégorie?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvCategory.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Catégorie modifiée");
               
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
        dialog.setContentText("Voulez vous supprimer cette catégorie?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvCategory.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Catégorie supprimée");
                se.supprimer(tvCategory.getSelectionModel().getSelectedItem().getId());
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
    
}
