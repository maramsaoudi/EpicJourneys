/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.demande;
import edu.esprit.services.demandeCRUD;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author SYB
 */
public class AdminDemandeController implements Initializable {

    @FXML
    private TableView<demande> tvDemande;
    @FXML
    private Button btnAccpDemande;
    @FXML
    private TableColumn<demande, Integer> colIdDemande;
    @FXML
    private TableColumn<demande, Integer> colIdClient;
    @FXML
    private TableColumn<demande, String> colDestDemande;
    @FXML
    private TableColumn<demande, Date> colDateDemande;
    @FXML
    private TableColumn<demande, String> colTypeDemande;
    
    @FXML
    private TableColumn<demande, String> colemailD;

    /**
     * Initializes the controller class.
     */
    
        private demande selectedDemande;


    public void setSelectedDemande(demande selectedDemande) {
        this.selectedDemande = selectedDemande;
    }
        public void showdemande() {
        demandeCRUD dcd = new demandeCRUD();
        List<demande> myList = dcd.afficherDemande2();

        colIdDemande.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        colIdClient.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colDestDemande.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDateDemande.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        colTypeDemande.setCellValueFactory(new PropertyValueFactory<>("type"));
        colemailD.setCellValueFactory(new PropertyValueFactory<>("emailD"));

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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
              showdemande();

        btnAccpDemande.setDisable(true);

        
        tvDemande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<demande>() {
            @Override
            public void changed(ObservableValue<? extends demande> observable, demande oldValue, demande newValue) {
                if (newValue != null) {
                   
                    btnAccpDemande.setDisable(false);
                } else {
                   
                    btnAccpDemande.setDisable(true);
                }
            }
        });
        // TODO
    }

private void sendConfirmationEmail(String clientEmail) {
    final String username = "maram.saoudi@esprit.tn"; 
    final String password = "223JFT6402"; 

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com"); 
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientEmail)); 
        message.setSubject("Acceptation de demande d'évenement");
        
        
        String emailContent = "<h3>Chers client,</h3>"
                + "<p>votre demande a été accepté.</p>"
                + "<p>Merci pour votre services.</p>";

        message.setContent(emailContent, "text/html");
        Transport.send(message);

        System.out.println("Email sent to " + clientEmail);
    } catch (MessagingException e) {
        System.err.println("Email sending failed: " + e.getMessage());
    }
}

    
private void showConfirmationDialog(String title, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

@FXML
private void approveDemande(ActionEvent event) {
   
    demande selectedDemande = tvDemande.getSelectionModel().getSelectedItem();
    
    if (selectedDemande != null) {
        String clientEmail = selectedDemande.getEmailD(); 
        
        sendConfirmationEmail(clientEmail);
       
        tvDemande.getItems().remove(selectedDemande);
    }
    
      showConfirmationDialog("Demande acceptée", "La demande a été acceptée avec succès.");

}
    
}
