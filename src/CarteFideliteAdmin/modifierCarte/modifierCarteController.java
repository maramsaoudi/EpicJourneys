/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarteFideliteAdmin.modifierCarte;

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
import java.util.Date;

import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author desig
 */
public class modifierCarteController implements Initializable {

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

    private CarteFidelite carteFidelite;
    CarteFideliteCrud carteFideliteCrud = new CarteFideliteCrud();

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCarteFidelite(CarteFidelite carteFidelite) {
        if (this.carteFidelite != carteFidelite) {
            this.carteFidelite = carteFidelite;
            fxUserInput.getItems().add(carteFidelite.getIdClient());
            // Update the UI with the new CarteFidelite values
            fxUserInput.setValue(carteFidelite.getIdClient());
            fxPtsFideliteInput.setText(String.valueOf(carteFidelite.getPtsFidelite()));
            fxDateDebutInput.setValue(((java.sql.Date) carteFidelite.getDateDebut()).toLocalDate());
            fxDateFinInput.setValue(((java.sql.Date) carteFidelite.getDateFin()).toLocalDate());
            etatCarteInput.setValue(carteFidelite.getEtatCarte().name());
            NiveauCarteInput.setValue(carteFidelite.getNiveauCarte().name());

        }
    }

    @FXML
    private void modifierCarte(ActionEvent event) {
        // Get values from form fields
        Integer userId = fxUserInput.getValue();

        int ptsFidelite = Integer.parseInt(fxPtsFideliteInput.getText());
        Date dateDebut = java.sql.Date
                .valueOf(fxDateDebutInput.getValue());
        Date dateFin = java.sql.Date.valueOf(fxDateFinInput.getValue());
        EtatCarte etatCarte = EtatCarte.valueOf(etatCarteInput.getValue());
        NiveauCarte niveauCarte = NiveauCarte.valueOf(NiveauCarteInput.getValue());

        CarteFidelite carteFidelite1 = new CarteFidelite(userId, ptsFidelite, dateDebut, dateFin,
                niveauCarte, etatCarte);

        carteFideliteCrud.modifierCarte(carteFidelite1, carteFidelite.getIdCarte());

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

    @FXML
    void deleteCarte(ActionEvent event) {
        carteFideliteCrud.SupprimerCarte(carteFidelite.getIdCarte());
        GoBack(event);
    }

}
