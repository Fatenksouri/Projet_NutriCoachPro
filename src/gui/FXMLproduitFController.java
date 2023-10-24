/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Produit;
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
import services.ServicePanier;
import services.ServiceProduit;


/**
 * FXML Controller class
 *
 * @author ksouri
 */
public class FXMLproduitFController implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private Slider priceSlider;

    
    @FXML
private TextField searchField;
    
    
    private List<Produit> o = new ArrayList<>();
                ServiceProduit p = new ServiceProduit();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        o.addAll(p.getAll());
        updateGridWithProducts(o);

        
        priceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double maxPrice = priceSlider.getValue();
            List<Produit> filteredProducts = filterProductsByPrice(o, maxPrice);
            updateGridWithProducts(filteredProducts);
        });
    }

    private List<Produit> filterProductsByPrice(List<Produit> products, double maxPrice) {
        List<Produit> filtered = new ArrayList<>();
        for (Produit product : products) {
            if (product.getPrix() <= maxPrice) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    
    private void updateGridWithProducts(List<Produit> products) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLgridProduit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                FXMLgridProduitController FXMLgrid = fxmlLoader.getController();
                FXMLgrid.setData(products.get(i));
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
      updateGridWithProducts(o);
}
    
    
    @FXML
private void searchProducts(ActionEvent event) {
    String query = searchField.getText();
    List<Produit> searchResults = searchProductsByName(query); 
    updateGridWithProducts(searchResults);
}

private List<Produit> searchProductsByName(String query) {
    List<Produit> results = new ArrayList<>();
    for (Produit product : o) {
        if (product.getNom().toLowerCase().contains(query.toLowerCase()) ||
            product.getDetails().toLowerCase().contains(query.toLowerCase())) {
            results.add(product);
        }
    }
    return results;
}
          @FXML
 private void goPanierF (ActionEvent event) {
                 try {
                Parent page = FXMLLoader.load(getClass().getResource("Panier.fxml"));
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
