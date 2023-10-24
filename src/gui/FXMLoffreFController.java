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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceOffre;


/**
 * FXML Controller class
 *
 * @author omnia
 */
public class FXMLoffreFController implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private Slider priceSlider;

    
    @FXML
private TextField searchField;
    
    
    private List<Offre> o = new ArrayList<>();
    private ServiceOffre p = new ServiceOffre();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        o.addAll(p.getAll());
        // Populate the grid with all offre initially
        updateGridWithOffres(o);

        
        // Add a change listener to the price slider
        priceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Filter offres based on the slider value
            double maxPrice = priceSlider.getValue();
            List<Offre> filteredOffres = filterOffresByPrice(o, maxPrice);
            updateGridWithOffres(filteredOffres);
        });
    }

    // Method to filter offres by price
    private List<Offre> filterOffresByPrice(List<Offre> offres, double maxPrice) {
        List<Offre> filtered = new ArrayList<>();
        for (Offre offre : offres) {
            if (offre.getPrix() <= maxPrice) {
                filtered.add(offre);
            }
        }
        return filtered;
    }

    // Helper method to update the grid with offres
    private void updateGridWithOffres(List<Offre> offres) {
        grid.getChildren().clear(); // Clear the grid
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < offres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLgrid.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                FXMLgridController FXMLgrid = fxmlLoader.getController();
                FXMLgrid.setData(offres.get(i));
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
    }
        @FXML
private void refresh(ActionEvent event) {
      updateGridWithOffres(o);
}
    
    
    @FXML
private void searchProducts(ActionEvent event) {
    String query = searchField.getText();
    List<Offre> searchResults = searchOffresByName(query); 
    updateGridWithOffres(searchResults);
}

private List<Offre> searchOffresByName(String query) {
    List<Offre> results = new ArrayList<>();
    for (Offre offre : o) {
        if (offre.getNom_du_soin().toLowerCase().contains(query.toLowerCase()) ||
            offre.getConvention_sosciete().toLowerCase().contains(query.toLowerCase())) {
            results.add(offre);
        }
    }
    return results;
}
          @FXML
    private void NavigateToConventionF(MouseEvent event) {
                           try {
                Parent page = FXMLLoader.load(getClass().getResource("FXMLconventionF.fxml"));
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
