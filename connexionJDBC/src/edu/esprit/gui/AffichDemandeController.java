
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.demande;
import edu.esprit.services.demandeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SYB
 */
public class AffichDemandeController implements Initializable {

    @FXML
    private TableView<demande> tvDemande;
    @FXML
    public TableColumn<demande, Integer> colIdDemande;
    @FXML
    private TableColumn<demande, String> colDestDemande;
    @FXML
    private TableColumn<demande, Date> colDateDemande;
    @FXML
    private TableColumn<demande, String> colTypeDemande;
    @FXML
    private Button btnModifierDem;
    @FXML
    private Button btnSupprimerDem;

    private demande selectedDemande;
   

    public void setSelectedDemande(demande selectedDemande) {
        this.selectedDemande = selectedDemande;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showdemande() {
        demandeCRUD dcd = new demandeCRUD();
        List<demande> myList = dcd.afficherDemande();

        colIdDemande.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        colDestDemande.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDateDemande.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        colTypeDemande.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<demande> observableList = FXCollections.observableArrayList(myList);
        tvDemande.setItems(observableList);

       
        tvDemande.setRowFactory(tv -> {
            TableRow<demande> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedDemande = row.getItem();
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

        showdemande();

        btnSupprimerDem.setDisable(true);

        
        tvDemande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<demande>() {
            @Override
            public void changed(ObservableValue<? extends demande> observable, demande oldValue, demande newValue) {
                if (newValue != null) {
                   
                    btnSupprimerDem.setDisable(false);
                } else {
                   
                    btnSupprimerDem.setDisable(true);
                }
            }
        });

        // TODO
    }

    @FXML
    public void updateDemande(ActionEvent event) {
        demande selectedDemande = tvDemande.getSelectionModel().getSelectedItem();
        if (selectedDemande != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutDemande.fxml"));
                Parent root = loader.load();
                FXMLController ajoutDemandeController = loader.getController();

                ajoutDemandeController.setMode("update");
                ajoutDemandeController.setSelectedDemande(selectedDemande);

               
                ajoutDemandeController.populateFields(selectedDemande);
                ajoutDemandeController.initialize(null, null);
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                Stage ajoutDemandeStage = new Stage();
                ajoutDemandeStage.setScene(scene);
                ajoutDemandeStage.initModality(Modality.APPLICATION_MODAL);
                ajoutDemandeStage.show();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void deleteDemande(ActionEvent event) {

          Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("confirmation");
        dialog.setHeaderText("Voulez-vous vraiment supprimer cette demande");
        dialog.initModality(Modality.APPLICATION_MODAL);
        
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
    
        try {

            demande d = tvDemande.getSelectionModel().getSelectedItem();
            demandeCRUD dc = new demandeCRUD();
            dc.SuprimerDemande(d);
            showdemande();
            btnSupprimerDem.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}

    @FXML
   private void backToDem(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutDemande.fxml"));
            Parent rooot = loader.load();
            FXMLController d = loader.getController();
            
            d.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.err.println(ex.getMessage());
        }
        
    }
}
