/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.reclamation;
import edu.esprit.services.ReclamationCRUD;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.internet.ParseException;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author SYB
 */
public class CadminRecController implements Initializable {

    @FXML
    private TableColumn<reclamation, Integer> colIdrec;
    @FXML
    private TableColumn<reclamation, Integer> colIdclient;
    @FXML
    private TableColumn<reclamation, String> colText;
    @FXML
    private TableColumn<reclamation, String> colCible;
    @FXML
    private TableColumn<reclamation, String> colDateRec;
    @FXML
    private TableColumn<reclamation, String> ColEtatRec;
    @FXML
    private Button btnRecResolu;
    @FXML
    private TableView<reclamation> tvAdminRec;
    @FXML
    private TextField tfChercherRec;
    @FXML
    private Button btnCherchRec;
    @FXML
    private Button btnResetRec;

    public void showReclamation() {
        ReclamationCRUD rcd = new ReclamationCRUD();
        List<reclamation> myList = rcd.afficherReclamations();
        colIdrec.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        colIdclient.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colText.setCellValueFactory(new PropertyValueFactory<>("textReclamation"));
        colCible.setCellValueFactory(new PropertyValueFactory<>("cibleReclamation"));
        colDateRec.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        ColEtatRec.setCellValueFactory(new PropertyValueFactory<>("etatReclamation"));
        ObservableList<reclamation> observableList = FXCollections.observableArrayList(myList);
        tvAdminRec.setItems(observableList);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     showReclamation();

     btnRecResolu.setDisable(true);
     tvAdminRec.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<reclamation>() {
        @Override
        public void changed(ObservableValue<? extends reclamation> observable, reclamation oldValue, reclamation newValue) {
            if (newValue != null) {
                btnRecResolu.setDisable(false);
            } else {
                btnRecResolu.setDisable(true);
            }
        }
    });

   
    Tooltip tooltip = new Tooltip("Sous forme de aaaa-mm-jj");
    tfChercherRec.setTooltip(tooltip);

    }

    @FXML
    private void treatReclamation(ActionEvent event) {

        try {

            reclamation r = tvAdminRec.getSelectionModel().getSelectedItem();
            ReclamationCRUD rcd = new ReclamationCRUD();
            rcd.ModifierReclamation2(r);
            showReclamation();
            btnRecResolu.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        showReclamation();
    }

    @FXML
    private void searchDate(ActionEvent event) {
        try {
            String dateText = tfChercherRec.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date searchDate = new Date(dateFormat.parse(dateText).getTime());
            ReclamationCRUD rcd = new ReclamationCRUD();
            List<reclamation> myList = rcd.searchReclamationsByDate(searchDate);
            ObservableList<reclamation> observableList = FXCollections.observableArrayList(myList);
            tvAdminRec.setItems(observableList);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(CadminRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void resetRecla(ActionEvent event) {
        showReclamation();
    }

}
