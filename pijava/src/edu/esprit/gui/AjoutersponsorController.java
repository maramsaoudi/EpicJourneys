package edu.esprit.gui;

import edu.esprit.entities.Sponsor;
import edu.esprit.services.SponsorCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjoutersponsorController implements Initializable {

    @FXML
    private TextField nomSponsorField;
    @FXML
    private TextField secteurField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telField;
    @FXML
    private TextField emailField;
    @FXML
    private Button btnajoutersponsor;
    @FXML
    private TextField dureeField;
private Stage stage; // Reference to the Stage
    @FXML
    private Button btnretour;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code, if needed
    }

    @FXML
    private void saveSponsor(ActionEvent event) {
        // Create a Sponsor object with the entered information
        Sponsor sponsor = new Sponsor();
        sponsor.setNomSponsor(nomSponsorField.getText());
        sponsor.setSecteurdactivite(secteurField.getText());
        sponsor.setAdresseSponsor(adresseField.getText());
        sponsor.setNumtelSponsor(Integer.parseInt(telField.getText()));
        sponsor.setEmailSponsor(emailField.getText());
       sponsor.setEmailSponsor(dureeField.getText());

           String email = emailField.getText();

        if (!isValidEmail(email)) {
        showAlert("Invalid Email", "Please enter a valid email address.");
        return; // Do not proceed with saving
    }
       
      
       
        // Create an instance of the SponsorCrud service
        SponsorCrud sponsorCrud = new SponsorCrud();

        // Add the sponsor to the database
        sponsorCrud.ajouterSponsor(sponsor);

        // Optionally, you can clear the input fields after adding the sponsor
        clearInputFields();
Stage currentStage = (Stage) nomSponsorField.getScene().getWindow();
    currentStage.close();  
    
    }

    
    private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);
    return pattern.matcher(email).matches();
}

private void showAlert(String title, String content) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}
    
    
    private void clearInputFields() {
        nomSponsorField.clear();
        secteurField.clear();
        adresseField.clear();
        telField.clear();
        emailField.clear();
        dureeField.clear();
    }

   @FXML
    private void retour(ActionEvent event) {
 try {

            Parent page1 = FXMLLoader.load(getClass().getResource("TableviewEvents.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
}

  
}
