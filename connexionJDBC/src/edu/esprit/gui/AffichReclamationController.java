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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SYB
 */
public class AffichReclamationController implements Initializable {

    @FXML
    private Button btnModifierRec;
    @FXML
    private Button btnSupprimerRec;
    @FXML
    private TableView<reclamation> tvReclamation;
    @FXML
    private TableColumn<reclamation, Integer> colIDReclamation;
    @FXML
    private TableColumn<reclamation, String> ColText;
    @FXML
    private TableColumn<reclamation, String> ColCible;
    @FXML
    private TableColumn<reclamation, Date> ColDateReclamation;
    @FXML
    private TableColumn<reclamation, String> ColEtatReclamation;

    private reclamation selectedReclamation;
    @FXML
    private Button btnRetouRec;

    public void setSelectedReclamation(reclamation selectedReclamation) {
        this.selectedReclamation = selectedReclamation;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showReclamation() {
        ReclamationCRUD rcd = new ReclamationCRUD();
        List<reclamation> myList = rcd.afficherReclamations();
        colIDReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        ColText.setCellValueFactory(new PropertyValueFactory<>("textReclamation"));
        ColCible.setCellValueFactory(new PropertyValueFactory<>("cibleReclamation"));
        ColDateReclamation.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        ColEtatReclamation.setCellValueFactory(new PropertyValueFactory<>("etatReclamation"));
        ObservableList<reclamation> observableList = FXCollections.observableArrayList(myList);
        tvReclamation.setItems(observableList);

        tvReclamation.setRowFactory(tv -> {
            TableRow<reclamation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedReclamation = row.getItem();
                }
            });
            return row;
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showReclamation();

        btnSupprimerRec.setDisable(true);

        tvReclamation.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<reclamation>() {
            @Override
            public void changed(ObservableValue<? extends reclamation> observable, reclamation oldValue, reclamation newValue) {
                if (newValue != null) {

                    btnSupprimerRec.setDisable(false);
                } else {

                    btnSupprimerRec.setDisable(true);
                }
            }
        });

    }

    @FXML
    private void updateReclamation(ActionEvent event) {

        reclamation selectedReclamation = tvReclamation.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutrec.fxml"));
                Parent root = loader.load();
                AjoutrecController ajoutRecController = loader.getController();

                ajoutRecController.setMode("update");
                ajoutRecController.setSelectedReclamation(selectedReclamation);

                ajoutRecController.populateFields(selectedReclamation);
                ajoutRecController.initialize(null, null);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                Stage ajoutReclamationStage = new Stage();
                ajoutReclamationStage.setScene(scene);
                ajoutReclamationStage.initModality(Modality.APPLICATION_MODAL);
                ajoutReclamationStage.show();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void deleteReclamation(ActionEvent event) {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("confirmation");
        dialog.setHeaderText("Voulez-vous vraiment supprimer cette reclamation");
        dialog.initModality(Modality.APPLICATION_MODAL);

        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            try {

                reclamation r = tvReclamation.getSelectionModel().getSelectedItem();
                ReclamationCRUD rcd = new ReclamationCRUD();
                rcd.SuprimerReclamation(r);
                showReclamation();
                btnSupprimerRec.setDisable(true);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }

    @FXML
    private void backToRec(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutrec.fxml"));
            Parent rooot = loader.load();
            AjoutrecController d = loader.getController();

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
