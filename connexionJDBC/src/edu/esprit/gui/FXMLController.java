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
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Tooltip;

public class FXMLController implements Initializable {

    private String mode = "add"; // initilizeha b hedhi add
    private demande selectedDemande;

    @FXML
    private TextField tfDestination;
    @FXML
    private TextField tfType;
    @FXML
    private DatePicker dtDateDepart;
    @FXML
    private Button btnAjoutDemande;
    @FXML
    private Button btnAffichDemande;
    @FXML
    private TextField tfEmailDemande;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           if ("update".equals(mode)) {
        populateFields(selectedDemande);
    }

    // Create a tooltip for tfEmailDemande
    Tooltip tooltip = new Tooltip("Vérifier bien l'email");
    tfEmailDemande.setTooltip(tooltip);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void populateFields(demande selectedDemande) {
        if (selectedDemande != null) {
            tfDestination.setText(selectedDemande.getDestination());
            dtDateDepart.setValue(selectedDemande.getDateDepart().toLocalDate());
            tfType.setText(selectedDemande.getType());
        }
    }

    public void setSelectedDemande(demande demande) {
        selectedDemande = demande;
    }

    @FXML
    private void saveDemande(ActionEvent event) {
        String destination = tfDestination.getText();
        LocalDate selectDate = dtDateDepart.getValue();
        Date dateDepart = (selectDate != null) ? Date.valueOf(selectDate) : null;
        String type = tfType.getText();

        if (destination.isEmpty() || type.isEmpty() || dateDepart == null) {
            showErrorDialog("Erreur", "Tous les champs doivent être remplis, et la date doit être sélectionnée.");
        } else {
            demandeCRUD dc = new demandeCRUD();

            // Check if the demand already exists
            boolean demandeExists = dc.isDemandeExists(destination, dateDepart, type);

            if (demandeExists) {
                showErrorDialog("Erreur", "Cette demande existe déjà.");
            } else {
                // If the demand doesn't exist, add it to the database.
                demande d = new demande(destination, dateDepart, type, tfEmailDemande.getText() );
                dc.ajouterDemande(d);
                sendConfirmationEmail();
                showConfirmationDialog("Success", "Votre demande a été ajoutée avec succès.");
            }
        }
    }

    private void sendConfirmationEmail() {
        final String username = "maram.saoudi@esprit.tn";
        final String password = "223JFT6402";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maram.saoudi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tfEmailDemande.getText()));
            message.setSubject("Confirmation de demande");

            String destination = tfDestination.getText();
            String type = tfType.getText();

            String emailContent = "<h3>Bonjour, votre demande a été reçue avec succès.</h3>"
                    + "<p>Destination: " + destination + "</p>"
                    + "<p>Thème: " + type + "</p>"
                    + "<p>Vous recevrez un e-mail lorsque celle-ci sera acceptée.</p>";

            message.setContent(emailContent, "text/html");
            Transport.send(message);

            System.out.println("Email recue...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnAffichDemande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichDemande.fxml"));
            Parent rooot = loader.load();
            AffichDemandeController detailController = loader.getController();
            detailController.initialize(null, null);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
