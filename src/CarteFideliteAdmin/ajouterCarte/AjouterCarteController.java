/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarteFideliteAdmin.ajouterCarte;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CRUD.CarteFideliteCrud;
import Entities.CarteFidelite;
import Entities.CarteFidelite.EtatCarte;
import Entities.CarteFidelite.NiveauCarte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author desig
 */
public class AjouterCarteController implements Initializable {

    @FXML
    private DatePicker fxDateDebutInput;
    @FXML
    private DatePicker fxDateFinInput;
    @FXML
    private ChoiceBox<Integer> fxUserInput;
    @FXML
    private TextField fxPtsFideliteInput;
    @FXML
    private ChoiceBox<String> etatCarteInput;
    @FXML
    private ChoiceBox<String> NiveauCarteInput;

    // private CarteFidelite carteFidelite;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CarteFideliteCrud carteFideliteCrud = new CarteFideliteCrud();
        List<Integer> userIds = carteFideliteCrud.getUsersWithoutCarteFidelite();
        ObservableList<Integer> options = FXCollections.observableArrayList(userIds);
        fxUserInput.setItems(options);
        fxUserInput.setValue(userIds.get(0));
        fxDateDebutInput.setValue(LocalDate.now());
        fxDateFinInput.setValue(LocalDate.now().plusYears(1));
        fxPtsFideliteInput.setText("0");
        fxPtsFideliteInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    fxPtsFideliteInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void setCarteFidelite(CarteFidelite carteFidelite) {
        setCarteFidelite(carteFidelite);
    }

    @FXML
    private void addCarte(ActionEvent event) {
        CarteFideliteCrud carteFideliteCrud = new CarteFideliteCrud();
        // Get values from form fields
        Integer userId = fxUserInput.getValue();

        int ptsFidelite = Integer.parseInt(fxPtsFideliteInput.getText());

        Date dateDebut = java.sql.Date
                .valueOf(fxDateDebutInput.getValue());
        Date dateFin = java.sql.Date.valueOf(fxDateFinInput.getValue());
        EtatCarte etatCarte = EtatCarte.valueOf(etatCarteInput.getValue());
        NiveauCarte niveauCarte = NiveauCarte.valueOf(NiveauCarteInput.getValue());

        CarteFidelite carteFidelite = new CarteFidelite(userId, ptsFidelite, dateDebut, dateFin,
                niveauCarte, etatCarte);
        carteFideliteCrud.ajouterCarteFidelite(carteFidelite);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../main/CarteFideliteAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.setTitle("Ajouter Carte Fidelite");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void GoBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../main/CarteFideliteAdmin.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            primaryStage.setTitle("Ajouter Carte Fidelite");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
