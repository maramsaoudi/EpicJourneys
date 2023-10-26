/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.reclamation;
import edu.esprit.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SYB
 */
public class AjoutrecController implements Initializable {

    private String mode = "add"; 
    private reclamation selectedReclamation;
    @FXML
    private ComboBox<String> cbCibleReclamation;
    @FXML
    private DatePicker dtDateReclamation;
    @FXML
    private Button btnAjouterReclamation;
    @FXML
    private TextField tfDescriptionReclamation;

    @FXML
    private Button btnAfficherReclamation;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] cb = {"Direction de l'Agence de Voyage", "service client", "Facturation et Paiement ", "Assurance Voyage", "guide"};
        cbCibleReclamation.getItems().addAll(cb);

        if ("update".equals(mode)) {
            populateFields(selectedReclamation);
            // TODO
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void populateFields(reclamation selectedReclamation) {
        if (selectedReclamation != null) {
            tfDescriptionReclamation.setText(selectedReclamation.getTextReclamation());
            dtDateReclamation.setValue(selectedReclamation.getDateReclamation().toLocalDate());

        }
    }

    public void setSelectedReclamation(reclamation reclamation) {
        selectedReclamation = reclamation;
    }

    @FXML
    private void saveReclamation(ActionEvent event) {
        String textReclamation = tfDescriptionReclamation.getText();
        String cibleReclamation = cbCibleReclamation.getValue();
        String EtatReclamation = "en attente";
        LocalDate selectDate = dtDateReclamation.getValue();
        Date dateReclamation = java.sql.Date.valueOf(selectDate);

        
        int clientId = 10; // mbaad nbadlou bel id shih
        ReclamationCRUD r = new ReclamationCRUD();
        int reclamationCount = r.countReclamationsByClient(clientId);

        if (reclamationCount >= 5) {
         
            showErrorDialog("vous avez atteint la limite maximale de 5 reclamations.");
        } else {
            
             if (textReclamation.isEmpty() || cibleReclamation.isEmpty() || dateReclamation == null) {
        showErrorDialog("Erreur "," Tous les champs doivent être remplis, et la date doit être sélectionnée.");
    } else {


            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("confirmation");
            dialog.setHeaderText("Voulez-vous vraiment ajouter cette Réclmation");
            dialog.initModality(Modality.APPLICATION_MODAL);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                
                
                reclamation r3 = new reclamation(10, textReclamation, cibleReclamation, EtatReclamation, dateReclamation);
                r.ajouterReclamation2(r3);
                showConfirmationDialog("Success", "Votre demande a été ajoutée avec succès.");}
            }
        }
    }
    
        private void showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        
            private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String errorMessage) {
        Dialog<ButtonType> errorDialog = new Dialog<>();
        errorDialog.setTitle("Error");
        errorDialog.setHeaderText(errorMessage);
        errorDialog.initModality(Modality.APPLICATION_MODAL);

        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        errorDialog.getDialogPane().getButtonTypes().add(closeButton);

        errorDialog.showAndWait();
    }

    @FXML
    private void btnAfficherReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichReclamation.fxml"));
            Parent rooot = loader.load();
            AffichReclamationController d = loader.getController();

            d.initialize(null, null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
