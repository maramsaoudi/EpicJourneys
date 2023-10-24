package newpackage.se.gui;
/*
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import epicjourneys.se.services.ProduitCRUD;
import epicjourneys.se.entities.Produit;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import newpackage.se.gui.ImageTableCell;
import javafx.scene.image.ImageView;


public class StoreController implements Initializable {

    @FXML
    private TableView<Produit> Amira_tableView_store;
    @FXML
    private TableColumn<Produit, String> Amira_colNom_store;
    @FXML
    private TableColumn<Produit, String> Amira_colPrixUnitaire_store;
    @FXML
    private TableColumn<Produit, Integer> Amira_colStock_store;

    @FXML
    private TextField Amira_NomProduit_store;
    @FXML
    private TextField Amira_PrixUnitaireProduit_store;
    @FXML
    private TextField Amira_Stock_store;
    @FXML
    private Button Amira_save_BT_store;
    @FXML
    private ImageView Amira_imageView_store;
    @FXML
    private TableColumn<?, ?> Amira_colImage_store;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurez les colonnes du TableView
        Amira_colNom_store.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Amira_colPrixUnitaire_store.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        Amira_colStock_store.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Ajout de la colonne d'image personnalisée
        TableColumn<Produit, Image> imageColumn = new TableColumn<>("Image");
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imageColumn.setCellFactory(new Callback<TableColumn<Produit, Image>, TableCell<Produit, Image>>() {
            @Override
            public TableCell<Produit, Image> call(TableColumn<Produit, Image> param) {
                return new ImageTableCell(); 
            }
        });

        Amira_tableView_store.getColumns().add(imageColumn);
        // Chargez les données de la base de données dans le TableView
        ProduitCRUD produitCRUD = new ProduitCRUD();
        ObservableList<Produit> produits = produitCRUD.listeDesEntities1();
        Amira_tableView_store.setItems(produits);
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            Amira_NomProduit_store.setText(selectedProduit.getNom());
            Amira_PrixUnitaireProduit_store.setText(selectedProduit.getPrixUnitaire());
            Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));

            // Charger et afficher l'image correspondante dans Amira_imageView_store
            Amira_imageView_store.setImage(selectedProduit.getImage());
        }
    }

    @FXML
    private void Amira_save_BT(ActionEvent event) {
        // Code pour gérer le bouton "Acheter" (si nécessaire)
    }
}

*/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import epicjourneys.se.services.ProduitCRUD;
import epicjourneys.se.entities.Produit;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import newpackage.se.gui.ImageTableCell;


public class StoreController implements Initializable {

    @FXML
    private TableView<Produit> Amira_tableView_store;
    @FXML
    private TableColumn<Produit, String> Amira_colNom_store;
    @FXML
    private TableColumn<Produit, Double> Amira_colPrixUnitaire_store;
    @FXML
    private TableColumn<Produit, Integer> Amira_colStock_store;

    @FXML
    private TextField Amira_NomProduit_store;
    @FXML
    private TextField Amira_PrixUnitaireProduit_store;
    @FXML
    private TextField Amira_Stock_store;
    @FXML
    private Button Amira_save_BT_store;
    @FXML
    private ImageView Amira_imageView_store;
    @FXML
    private TableColumn<?, ?> Amira_colImage_store;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurez les colonnes du TableView
        Amira_colNom_store.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Amira_colPrixUnitaire_store.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        Amira_colStock_store.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Chargez les données de la base de données dans le TableView
        ProduitCRUD produitCRUD = new ProduitCRUD();
        ObservableList<Produit> produits = produitCRUD.listeDesEntities1();
        Amira_tableView_store.setItems(produits);

        // Réagissez aux clics de ligne dans le TableView
        Amira_tableView_store.setOnMouseClicked((MouseEvent event) -> {
            Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();

            if (selectedProduit != null) {
                Amira_NomProduit_store.setText(selectedProduit.getNom());
                Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
                Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));

                // Charger et afficher l'image correspondante dans Amira_imageView_store
                Image image = new Image("file:" + selectedProduit.getImage()); // Assurez-vous que le chemin est correct
                Amira_imageView_store.setImage(image);
            }
        });
    }

    @FXML
    private void Amira_save_BT(ActionEvent event) {
        // Code pour gérer le bouton "Acheter" (si nécessaire)
    }

    @FXML
    private void rowClicked(MouseEvent event) { Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();

    if (selectedProduit != null) {
        Amira_NomProduit_store.setText(selectedProduit.getNom());
        Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
        Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));

        // Charger et afficher l'image correspondante dans Amira_imageView_store
        Image image = new Image(selectedProduit.getImage());
        Amira_imageView_store.setImage(image);
    }
    }
}
