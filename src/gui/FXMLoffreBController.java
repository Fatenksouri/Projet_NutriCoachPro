/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Categorie;
import entity.Convention;
import entity.Offre;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;
import services.ServiceConvention;
import services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author omnia
 */
public class FXMLoffreBController implements Initializable {

   @FXML
    private ImageView image_view;
  
    @FXML
    private TextField tfNom;
   
    @FXML
    private DatePicker tfDatee;
    
    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField tfprix;
      
    @FXML
    ComboBox<Categorie> cbType = new ComboBox<>();
    
    @FXML
    ComboBox<Convention> cbconvention = new ComboBox<>();
       
    @FXML
    private TableView<Offre> tvOffre;


    
    @FXML
    private TableColumn<Offre, String> colNom;
    
    @FXML
    private TableColumn<Offre, String> colDatee;
    
    @FXML
    private TableColumn<Offre, String> colDate;

    @FXML
    private TableColumn<Offre, Float> colprix;
    
    @FXML
    private TableColumn<Offre, String> colsociete;
    
    @FXML
    private TableColumn<Offre, String> colImage;
   
    @FXML
    private TableColumn<Offre, String> colCategorie ;
    
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
                   @FXML
    private Label  txtUsername;
                                    @FXML
    private ImageView userImg;


   Categorie  type ;
    
   int conv =0;
    String pictureLink = "";
    
    @FXML
    private Button btnclear;
    
    
 java.util.Date date = new java.util.Date();
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 
 
 ServiceOffre so = new ServiceOffre();
 ServiceConvention sc = new ServiceConvention();

 
     @FXML
    private void handleMouseClicked(MouseEvent event) {
    }
 
    public void show()
    {
    ObservableList<Offre> data = FXCollections.observableArrayList(so.getAll());
        colNom.setCellValueFactory(new PropertyValueFactory("Nom_du_soin"));
        colDatee.setCellValueFactory(new PropertyValueFactory("date_debut"));
        colDate.setCellValueFactory(new PropertyValueFactory("date_fin"));
        colprix.setCellValueFactory(new PropertyValueFactory("prix"));
        colCategorie.setCellValueFactory(new PropertyValueFactory("categorie"));
        colImage.setCellValueFactory(new PropertyValueFactory("image"));    
        colsociete.setCellValueFactory(new PropertyValueFactory("convention_sosciete"));  
        tvOffre.setItems(data);        
    }
    
    private void clear() {
        tvOffre.getSelectionModel().clearSelection();
        tfNom.clear();
        tfprix.clear();
        tfDatee.setValue(LocalDate.parse(formatter.format(date)));
        tfdate.setValue(LocalDate.parse(formatter.format(date)));
        btnInsert.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        image_view.setImage(null);
        cbType.getSelectionModel().select(-1);
        cbconvention.getSelectionModel().select(-1);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfDatee.setValue(LocalDate.parse(formatter.format(date)));
        tfdate.setValue(LocalDate.parse(formatter.format(date)));
        
        
        ObservableList<Convention> Conventions = FXCollections.observableArrayList(sc.getAllsos());
        cbconvention.setItems(Conventions);
        
        cbconvention.setOnAction(event -> {
    Convention selectedcbconvention = cbconvention.getSelectionModel().getSelectedItem();
    if (selectedcbconvention != null) {
        conv = selectedcbconvention.getId();
        System.out.println(conv);
    }
});
        
        ObservableList<Categorie> categories = FXCollections.observableArrayList(Categorie.values());
        cbType.setItems(categories);
        
        
        cbType.setOnAction(event -> {
        Categorie selectedCategory = cbType.getSelectionModel().getSelectedItem();
    if (selectedCategory != null) {
        type = selectedCategory; 
    }
});
        
       show();
       tvOffre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (tvOffre.getSelectionModel().getSelectedItem() != null) {
                    Offre e = (Offre) tvOffre.getSelectionModel().getSelectedItem();
                    tfNom.setText(e.getNom_du_soin());
                    tfprix.setText(String.valueOf(e.getPrix()));
                    tfDatee.setValue(e.getDate_debut().toLocalDate());
                    tfdate.setValue(e.getDate_fin().toLocalDate());
    
    Categorie selectedCategory = null;
    for (Categorie c : cbType.getItems()) {
        if (c.name().equals(e.getCategorie())) {
            selectedCategory = c;
            break;
        }
    }

    if (selectedCategory != null) {
        cbType.getSelectionModel().select(selectedCategory);
    }

    
                    
        Convention selectedConvention  = null;
    for (Convention c : cbconvention.getItems()) {
        if (c.getSociete().equals(e.getConvention_sosciete())) {
            selectedConvention = c;
            break;
        }
    }

    if (selectedConvention != null) {
        cbconvention.getSelectionModel().select(selectedConvention);
    }

    
    conv = e.getConvention_id();
    
    pictureLink=e.getImage();
                    btnInsert.setDisable(true);

                }
            }
        });  
    }    
    
    
        @FXML
    private void Ajout(ActionEvent event) {

               if (tfNom.getText() == null || tfNom.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez remplir le champ du nom.");
            dialogW.showAndWait();
        } else if (tfDatee.getValue() == null) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez insérer la date.");
            dialogW.showAndWait();
            
          } else if (tfdate.getValue().compareTo(LocalDate.parse(formatter.format(date))) <0 ){
        
            Alert dialogW = new Alert(Alert.AlertType.ERROR);
            dialogW.setHeaderText(null);
            dialogW.setContentText("Veuillez choisir une date courante.");
            dialogW.showAndWait();
            
        } else if (tfprix.getText() == null || tfprix.getText().trim().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez remplir le champ du prix.");
            dialogW.showAndWait();
        } else if (type.name().isEmpty()) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); 
            dialogW.setContentText("Veuillez choisir la catégorie.");
            dialogW.showAndWait();
        }else if (conv == 0) {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null);
            dialogW.setContentText("Veuillez choisir la convention.");
            dialogW.showAndWait();
        } else {
            System.out.println(type);
            
       Offre c = new Offre(tfNom.getText(),type, Float.parseFloat(tfprix.getText()),Date.valueOf(tfDatee.getValue()),Date.valueOf(tfdate.getValue()),this.pictureLink,conv);

            so.ajouter(c);
             clear();
                         show();


        }
    
    }
    
        @FXML
    private void Modifier(ActionEvent event) {
        if (tvOffre.getSelectionModel().getSelectedItem() != null) {
    Offre c = new Offre(tvOffre.getSelectionModel().getSelectedItem().getId(),tfNom.getText(),type, Float.parseFloat(tfprix.getText()),Date.valueOf(tfDatee.getValue()),Date.valueOf(tfdate.getValue()),this.pictureLink,conv);


            so.modifier(c);
            
          ButtonType okButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setContentText("Voulez vous modifier cete offre?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvOffre.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Offre modifiée");
               
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
        dialog.setContentText("Voulez vous supprimer cette offre?");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (tvOffre.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Offre supprimée");
                so.supprimer(tvOffre.getSelectionModel().getSelectedItem().getId());
              show();
            }
            clear();
        } else {
            System.out.println("Annuler");
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
        File dir = new File("C:\\Users\\omnia\\Downloads\\convention_finale\\uploads\\");
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
    private void NavigateToConventionB(MouseEvent event) {
                           try {
                Parent page = FXMLLoader.load(getClass().getResource("FXMLconventionB.fxml"));
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
       
       Node root= this.tvOffre;
       
       
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
