package newpackage.se.gui;

import epicjourneys.se.entities.Produit;
import epicjourneys.se.services.ProduitCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Base64;
import java.util.Optional;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

public class ProduitFXMLController implements Initializable {
    @FXML
    private TextField Amira_NomProduit;
    @FXML
    private TextField Amira_PrixUnitaireProduit;
    @FXML
    private Spinner<Integer> Amira_StockProduit;
    @FXML
    private Button Amira_selectImage_BT;
    @FXML
    private ImageView Amira_imageView;
    @FXML
    private Button Amira_save_BT;
    @FXML
    private TableView<Produit> Amira_tableView;
    @FXML
    private TableColumn<Produit, String> Amira_colNom;
    @FXML
    private TableColumn<Produit, String> Amira_colPrixUnitaire;
    @FXML
    private TableColumn<Produit, Integer> Amira_colStock;
    @FXML
    private Button Amira_delet_BT;
    @FXML
    private Button Amira_update_BT;

    private File selectedImageFile;

    private ProduitCRUD produitCRUD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Amira_colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Amira_colPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        Amira_colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        int minValue = 1;
        int maxValue = 1000;
        int initialValue = 1;

        SpinnerValueFactory<Integer> stockValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue, initialValue);
        Amira_StockProduit.setValueFactory(stockValueFactory);

        produitCRUD = new ProduitCRUD();
        loadProduit();
    }

    @FXML
    private void Amira_selectImage_BT(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(Amira_selectImage_BT.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            Amira_imageView.setImage(image);
        }
    }
    //String imagepathproduit=null;

    @FXML
    private void Amira_save_BT(ActionEvent event) {
        String nom = Amira_NomProduit.getText();
        String prixUnitaire = Amira_PrixUnitaireProduit.getText();
        int stock = Amira_StockProduit.getValue();

        if (selectedImageFile != null) {
            if (validateFields()) {
                Alert confirmation = new Alert(AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setHeaderText("Confirmer l'ajout du produit");
                confirmation.setContentText("Êtes-vous sûr de vouloir ajouter ce produit ?");

                Optional<ButtonType> result = confirmation.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        byte[] imageBytes = new byte[(int) selectedImageFile.length()];
                        FileInputStream fis = new FileInputStream(selectedImageFile);
                        fis.read(imageBytes);
                        fis.close();

                        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                        //imagepathproduit=selectedImageFile.getAbsolutePath();

                        Produit produit = new Produit();
                        produit.setNom(nom);
                        produit.setPrixUnitaire(prixUnitaire);
                        produit.setStock(stock);
                        produit.setImage(imageBase64);
                        produitCRUD.ajouterProduit(produit);

                        List<Produit> produits = produitCRUD.listeDesProduits();
                        Amira_tableView.setItems((ObservableList<Produit>) produits);

                        clearFields();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else {
            showAlert("Sélection d'image requise", "Veuillez sélectionner une image.");
        }
    }

    @FXML
    private void Amira_delet_BT(ActionEvent event) {
        Produit selectedProduit = Amira_tableView.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Confirmer la suppression du produit");
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                produitCRUD.supprimerProduit(selectedProduit);
                loadProduit();
            }
        } else {
            showAlert("Sélection requise", "Veuillez sélectionner un produit à supprimer.");
        }
    }

    @FXML
    private void Amira_update_BT(ActionEvent event) {
        Produit selectedProduit = Amira_tableView.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            String newNom = Amira_NomProduit.getText();
            String newPrixUnitaire = Amira_PrixUnitaireProduit.getText();
            int newStock = Amira_StockProduit.getValue();

            if (validateFields()) {
                Alert confirmation = new Alert(AlertType.CONFIRMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setHeaderText("Confirmer la mise à jour du produit");
                confirmation.setContentText("Êtes-vous sûr de vouloir mettre à jour ce produit ?");

                Optional<ButtonType> result = confirmation.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    selectedProduit.setNom(newNom);
                    selectedProduit.setPrixUnitaire(newPrixUnitaire);
                    selectedProduit.setStock(newStock);

                    produitCRUD.modifierProduit(selectedProduit);

                    loadProduit();
                    clearFields();
                }
            }
        } else {
            showAlert("Sélection requise", "Veuillez sélectionner un produit à mettre à jour.");
        }
    }

    @FXML
    private void rowClicked() {
        Produit selectedProduit = Amira_tableView.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            Amira_NomProduit.setText(selectedProduit.getNom());
            Amira_PrixUnitaireProduit.setText(selectedProduit.getPrixUnitaire());
            Amira_StockProduit.getValueFactory().setValue(selectedProduit.getStock());

            String imageBase64 = selectedProduit.getImage();
            if (imageBase64 != null && !imageBase64.isEmpty()) {
                byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                Amira_imageView.setImage(image);
            } else {
                Amira_imageView.setImage(null);
            }
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        Amira_NomProduit.clear();
        Amira_PrixUnitaireProduit.clear();
        Amira_StockProduit.getValueFactory().setValue(1);
        Amira_imageView.setImage(null);
    }

    private void loadProduit() {
        ObservableList<Produit> produits = FXCollections.observableArrayList(produitCRUD.listeDesProduits());
        Amira_tableView.setItems(produits);
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (Amira_NomProduit.getText().isEmpty()) {
            isValid = false;
            showAlert("Champ Nom vide", "Veuillez saisir un nom de produit.");
        }

        String prixUnitaire = Amira_PrixUnitaireProduit.getText();
        try {
            Double.parseDouble(prixUnitaire);
        } catch (NumberFormatException e) {
            isValid = false;
            showAlert("Prix non valide", "Veuillez saisir un prix unitaire valide.");
        }

        return isValid;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
