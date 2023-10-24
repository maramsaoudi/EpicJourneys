package newpackage.se.gui;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import epicjourneys.se.entities.Commande;
import epicjourneys.se.services.CommandeCRUD;
import epicjourneys.se.services.UserCRUD;
import epicjourneys.se.entities.User;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import epicjourneys.se.interfraces.InterfaceCRUD;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

        ObservableList<Produit> produits = FXCollections.observableArrayList(produitCRUD.listeDesProduits());

        Amira_tableView_store.setItems(produits); 
        

        // Réagissez aux clics de ligne dans le TableView
        Amira_tableView_store.setOnMouseClicked((MouseEvent event) -> {
            Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();

            if (selectedProduit != null) {
                Amira_NomProduit_store.setText(selectedProduit.getNom());
                Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
                Amira_Stock_store.setText(String.valueOf(selectedProduit.getStock()));
System.out.println("TEST");
                // Charger et afficher l'image correspondante dans Amira_imageView_store
               // Image image = new Image("file:" + selectedProduit.getImage()); // Assurez-vous que le chemin est correct
               // Amira_imageView_store.setImage(image);
            }
        });
    }
    @FXML
    private void Amira_save_BT(ActionEvent event) {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            

        confirmationAlert.setTitle("Confirmation d'achat");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir acheter ce produit ?");

        ButtonType ouiButton = new ButtonType("Oui");
        ButtonType nonButton = new ButtonType("Non");

        confirmationAlert.getButtonTypes().setAll(ouiButton, nonButton);

        UserCRUD userCRUD = new UserCRUD();
      
        // Récupérez l'utilisateur actuellement connecté (par exemple, à partir d'une session ou d'un système d'authentification).
        // Remplacez "1" par l'ID de l'utilisateur connecté.
        User utilisateur = userCRUD.getUserById(6);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ouiButton && utilisateur != null) {
            Produit produitSelectionne = Amira_tableView_store.getSelectionModel().getSelectedItem();
            if (produitSelectionne != null) {
                ajouterCommande(utilisateur, produitSelectionne);
            }
        }
    }

    private void ajouterCommande(User utilisateur, Produit produit) {
        // Créez une instance de la classe Commande avec les détails de la commande.
        Commande commande = new Commande(utilisateur.getId(), produit.getId(), "votre_path_facture");

        // Ajoutez la commande à la table "commande" en utilisant la classe CommandeCRUD.
        CommandeCRUD commandeCRUD = new CommandeCRUD();
        commandeCRUD.ajouterEntities(commande);

        // Affichez un message de succès ou effectuez d'autres actions nécessaires.
        System.out.println("Commande ajoutée avec succès !");
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        Produit selectedProduit = Amira_tableView_store.getSelectionModel().getSelectedItem();

        if (selectedProduit != null) {
            Amira_NomProduit_store.setText(selectedProduit.getNom());
            Amira_PrixUnitaireProduit_store.setText(String.valueOf(selectedProduit.getPrixUnitaire()));
           
        }
    }}