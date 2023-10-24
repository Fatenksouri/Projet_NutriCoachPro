/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Category;
import entity.Produit;
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
import services.ServiceCategory;
import services.ServicePanier;
import services.ServiceProduit;



/**
 * FXML Controller class
 *
 * @author ksouri
 */
public class ProduitController implements Initializable {

    @FXML
    private ImageView image_view;
  
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrix;
        @FXML
    private TextField tfquantite;

   @FXML
    private TextField reference_produit;
    @FXML
    private TextField tfdetails;
    
    
    @FXML
    ComboBox<Category> cbType = new ComboBox<>();
    
    @FXML
    private TableView<Produit> tableauProduit;

    

    @FXML
    private TableColumn<Produit, String> colNom;
    @FXML
    private TableColumn<Produit, Float> colprix;
       
    @FXML
    private TableColumn<Produit, Integer> colQuantite;

    @FXML
    private TableColumn<Produit, String> coldetails;
    @FXML
    private TableColumn<Produit, String> colImage;
       
    @FXML
    private TableColumn<Produit, String> colref;

     @FXML
    private TableColumn<Produit, String> colType ;
  
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField filterField;
    @FXML
    private Button insert_image;
    @FXML
    private Label file_path;


   int type = 0;
    
        String pictureLink = "";

                ServiceProduit se = new ServiceProduit();
                             ServiceCategory sc = new ServiceCategory();

    @FXML
    private Button btnclear;



    public ProduitController() {
    }
    

 
    public void show()
    {
    ObservableList<Produit> data = FXCollections.observableArrayList(se.getAll());
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colprix.setCellValueFactory(new PropertyValueFactory("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory("quantite"));
                coldetails.setCellValueFactory(new PropertyValueFactory("details"));
        colImage.setCellValueFactory(new PropertyValueFactory("image"));    
         colType.setCellValueFactory(new PropertyValueFactory("category_type"));  
         colref.setCellValueFactory(new PropertyValueFactory("ref"));  
        tableauProduit.setItems(data);
          
        
    }
      private void clear() {
        tableauProduit.getSelectionModel().clearSelection();
        tfNom.clear();
tfPrix.clear();
tfquantite.clear();
tfdetails.clear();
reference_produit.clear();
        btnInsert.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        image_view.setImage(null);
     cbType.getSelectionModel().select(-1);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


ObservableList<Category> categories = FXCollections.observableArrayList(sc.getAll()); 
cbType.setItems(categories);

cbType.setOnAction(event -> {
    Category selectedCategory = cbType.getSelectionModel().getSelectedItem();
    if (selectedCategory != null) {
        type = selectedCategory.getId();
    }
});

        show();
         
             tableauProduit.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tableauProduit.getSelectionModel().getSelectedItem() != null) {
                    Produit e = (Produit) tableauProduit.getSelectionModel().getSelectedItem();
                    tfNom.setText(e.getNom());
                    tfdetails.setText(e.getDetails());                
                    tfPrix.setText(String.valueOf(e.getPrix()));
                    tfquantite.setText(String.valueOf(e.getQuantite()));
    Category selectedCategory = null;
    for (Category c : cbType.getItems()) {
        if (c.getType().equals(e.getCategory_type())) {
            selectedCategory = c;
            break;
        }
    }

    if (selectedCategory != null) {
        cbType.getSelectionModel().select(selectedCategory);
    }

    type = e.getCategory_id();
                    tfdetails.setText(String.valueOf(e.getDetails()));
                    pictureLink=e.getImage();
                    btnInsert.setDisable(true);

                }
            }
        });
    }    

    @FXML
    private void handleMouseClicked(MouseEvent event) {
    }


    @FXML
    private void Ajout(ActionEvent event) {
            System.out.println(type);
            String reference = reference_produit.getText();

    if (reference == null || reference.trim().isEmpty()) {
        Alert dialogW = new Alert(Alert.AlertType.WARNING);
        dialogW.setTitle("A warning dialog-box");
        dialogW.setHeaderText(null);
        dialogW.setContentText("Veuillez remplir le champ de référence s'il vous plaît!");
        dialogW.showAndWait();
    } else {
        // Reference is not empty, proceed with checking and adding the product
        int quantity = Integer.parseInt(tfquantite.getText());

        // Call your Check method and check its result
        boolean updated = se.Check(reference, quantity);

        if (updated) {
            // The quantity is updated successfully
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setHeaderText(null);
            successAlert.setContentText("La quantité est mise à jour.");
            successAlert.showAndWait();
            clear();
            show();
        } else {
           

               if (tfNom.getText() == null || tfNom.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null);
            dialogW.setContentText("Veuillez remplir le champ du nom.");
            dialogW.showAndWait();
        } else if (tfPrix.getText() == null || tfPrix.getText().trim().isEmpty())  {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez insérer le prix.");
            dialogW.showAndWait();
            
          } else if (tfquantite.getText() == null || tfquantite.getText().trim().isEmpty())  {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("Veuillez insérer la quantité.");
            dialogW.showAndWait();
            
          } else if (tfdetails.getText() == null || tfdetails.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("Veuillez remplir le champ du prix.");
            dialogW.showAndWait();
        } else if (type == 0) {
            
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez choisir la catégorie.");
            dialogW.showAndWait();
            
        } else {
            System.out.println(type); 
             
            Produit c = new Produit(tfNom.getText(),tfdetails.getText(), Float.parseFloat(tfPrix.getText()),Integer.parseInt(tfquantite.getText()),this.pictureLink,type, reference_produit.getText());
            
            se.ajouter(c);
            type=0;
             clear();
                         show();


        }
            
        }
 }
     
    }
    
    
    @FXML
    private void Modifier(ActionEvent event) {
        if (tableauProduit.getSelectionModel().getSelectedItem() != null) {
            
                        Produit c = new Produit(tableauProduit.getSelectionModel().getSelectedItem().getId(),tfNom.getText(),tfdetails.getText(), Float.parseFloat(tfPrix.getText()),Integer.parseInt(tfquantite.getText()),this.pictureLink,type,reference_produit.getText());


            se.modifier(c);
            
          ButtonType okButtonType = new ButtonType("Confirmé", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annulé", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous modifier ce produit ?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tableauProduit.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Produit modifié");
               
             }
 
            show();
            }
        
            clear();
           
          } else {
            System.out.println("Annulé");
        }  
      

        }
 
        
    @FXML
    private void Delete(ActionEvent event) {
        ButtonType okButtonType = new ButtonType("Confirmé", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annulé", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous supprimer ce produit?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tableauProduit.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "produit Supprimé");
                se.supprimer(tableauProduit.getSelectionModel().getSelectedItem().getId());
              show();
            }
            clear();
        } else {
            System.out.println("Annulé");
        }
    }

  
    @FXML
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException, SQLException {

   

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
 

           image_view.setImage(image);
             Image image1 = image_view.getImage();
       this.pictureLink =ConvertFileImage(image1);
       System.err.println(this.pictureLink);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }


    public static String ConvertFileImage(Image image) throws SQLException, IOException {

        String ext = "jpg";
        File dir = new File("C:\\Users\\faten\\Downloads\\finale_faten - changé\\finale_faten - changé\\Faten_finale\\uploads");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(24), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return name;
    }

    public void load_pic(String links) {

        Image img = new Image(links);
        image_view.setImage(img);

    }


    @FXML
    private void Clear(ActionEvent event) {
        clear();
    }


     @FXML
 private void NavigateToctagorieF (MouseEvent event) {
                 try {
                Parent page = FXMLLoader.load(getClass().getResource("FXMLcategory.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
     
 } 
 
     @FXML
    private void OnClickedPrint(ActionEvent event) {
          
       PrinterJob job = PrinterJob.createPrinterJob();
       
       Node root= this.tableauProduit;
       if(job != null){
       job.showPrintDialog(root.getScene().getWindow()); 

    Printer printer = job.getPrinter();
    PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
        
    }
   
     }
   
    }
}
    
      