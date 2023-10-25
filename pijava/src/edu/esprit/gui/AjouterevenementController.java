package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import edu.esprit.services.EvenementCrud;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.DateCell;

public class AjouterevenementController implements Initializable {
    @FXML
    private TextField id_titre;
    @FXML
    private TextField id_type;
    @FXML
    private TextField id_destination;
    @FXML
    private TextArea id_description;
    @FXML
    private DatePicker id_date;
    @FXML
    private ComboBox<Integer> id_guide;
    @FXML
    private ComboBox<String> id_sponsor;
    @FXML
    private Spinner<Integer> id_prix;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField id_image;
    
  private Stage primaryStage;
   public static final String ACCOUNT_SID = "AC836eab96a74c077c9c1f66979efa2999"; // Replace with your Twilio Account SID
    public static final String AUTH_TOKEN = "6e5279e2019d12588e902231596da0fe";     // Replace with your Twilio Auth Token
    @FXML
    private Button btnretour;

public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
}

 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //disable past dates
        id_date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
        // Récupérez les guides
        populateGuideComboBox();

        // Récupérez les sponsors
        populateSponsorComboBox();
        
        // Initialiser le spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1, 1);
        valueFactory.setValue(1);
        id_prix.setValueFactory(valueFactory);
    }

    private void populateGuideComboBox() {
        try {
            MyConnection myConnection = MyConnection.getInstance();
            Connection dbConnection = myConnection.getCnx();

            Statement statement = dbConnection.createStatement();
            ResultSet resultSetGuideIds = statement.executeQuery("SELECT id FROM user WHERE role = 'GUIDE'");

            ObservableList<Integer> guideIds = FXCollections.observableArrayList();

            while (resultSetGuideIds.next()) {
                guideIds.add(resultSetGuideIds.getInt("id"));
            }

            id_guide.setItems(guideIds);

            resultSetGuideIds.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions ici
        }
    }

    private void populateSponsorComboBox() {
        try {
            MyConnection myConnection = MyConnection.getInstance();
            Connection dbConnection = myConnection.getCnx();

            Statement statement = dbConnection.createStatement();
            ResultSet resultSetSponsorNames = statement.executeQuery("SELECT nomSponsor FROM sponsor");

            ObservableList<String> sponsorNames = FXCollections.observableArrayList();

            while (resultSetSponsorNames.next()) {
                sponsorNames.add(resultSetSponsorNames.getString("nomSponsor"));
            }

            id_sponsor.setItems(sponsorNames);

            resultSetSponsorNames.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions ici
        }
    }

//   @FXML
//private void saveEvenement(ActionEvent event) {
//    String titre = id_titre.getText();
//    String type = id_type.getText();
//    String destination = id_destination.getText();
//    String description = id_description.getText();
//    String image = id_image.getText();
//
//    Date date = null;
//    if (id_date.getValue() != null) {
//        date = java.sql.Date.valueOf(id_date.getValue());
//    }
//
//    Integer guide = id_guide.getValue(); // Récupérer l'ID du guide sélectionné
//    String sponsor = id_sponsor.getValue(); // Récupérer le nom du sponsor sélectionné
//    Integer prix = id_prix.getValue();
//
//    Evenement e = new Evenement(type, date, description, destination, guide, image, prix, titre, sponsor);
//    EvenementCrud ec = new EvenementCrud();
//
//    ec.ajouterEvenement(e);
//    
//    // Close the initial table view
//    Stage currentStage = (Stage) id_titre.getScene().getWindow();
//    currentStage.close();
//
//    // Switch to the new TableView scene
//    switchToTableViewScene();
//}

    
    
    
    
    
    
  

// ...

@FXML
private void saveEvenement(ActionEvent event) {
       String titre = id_titre.getText();
    String type = id_type.getText();
    String destination = id_destination.getText();
    String description = id_description.getText();
    String image = id_image.getText();

    
     
    Date date = null;
    if (id_date.getValue() != null) {
        date = java.sql.Date.valueOf(id_date.getValue());
    }

    Integer guide = id_guide.getValue(); // Récupérer l'ID du guide sélectionné
    String sponsor = id_sponsor.getValue(); // Récupérer le nom du sponsor sélectionné
    Integer prix = id_prix.getValue();

    Evenement e = new Evenement(type, date, description, destination, guide, image, prix, titre, sponsor);
    EvenementCrud ec = new EvenementCrud();

    ec.ajouterEvenement(e);
       //Close the initial table view
    Stage currentStage = (Stage) id_titre.getScene().getWindow();
    currentStage.close();

    //Switch to the new TableView scene
  switchToTableViewScene();
    
    String eventDestination = destination;

    String eventMessage = "EpicJourneys has a new event in " + eventDestination + ": " + titre + ". Check our app for more details.";

    // Send the event announcement to clients
    List<String> recipientPhoneNumbers = getRecipientPhoneNumbers();
    boolean allMessagesSent = sendEventAnnouncementToClients(eventMessage, recipientPhoneNumbers);

    // Check if all messages were sent successfully
    if (allMessagesSent) {
        System.out.println("All messages sent successfully!");
    } else {
        System.out.println("Some messages failed to send. Please check your logs for details.");
    }

 }



private boolean sendEventAnnouncementToClients(String message, List<String> phoneNumbers) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    String fromPhoneNumber = "+16307492027";
    boolean allMessagesSent = true;
    for (String toPhoneNumber : phoneNumbers) {
        try {
            Message messageResult = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                message
            ).create();
            if (messageResult.getStatus() != null && messageResult.getStatus().equals("queued")) {
                // Message is successfully queued for delivery.
                System.out.println("Message to " + toPhoneNumber + " is queued for delivery. Message SID: " + messageResult.getSid());
            } else {
                allMessagesSent = false;
                System.err.println("Failed to send a message to " + toPhoneNumber);
                System.err.println("Message SID: " + messageResult.getSid());
            }
        } catch (com.twilio.exception.ApiException ex) {
            allMessagesSent = false;
            System.err.println("Twilio API Exception: " + ex.getMessage());
            System.err.println("Twilio Account SID: " + ACCOUNT_SID);
            System.err.println("Twilio Auth Token: " + AUTH_TOKEN);
            System.err.println("Twilio 'fromPhoneNumber': " + fromPhoneNumber);
        }
    }
    return allMessagesSent;
}



    
private List<String> getRecipientPhoneNumbers() {
    List<String> phoneNumbers = new ArrayList<>();
    
    try {
        MyConnection myConnection = MyConnection.getInstance();
        Connection dbConnection = myConnection.getCnx();
        Statement statement = dbConnection.createStatement();
        
        // Execute the query to retrieve phone numbers from the 'user' table where 'Role' is 'CLIENT'
        ResultSet resultSet = statement.executeQuery("SELECT numeroTelephone FROM user WHERE Role = 'CLIENT'");
        
        while (resultSet.next()) {
            phoneNumbers.add(resultSet.getString("numeroTelephone"));
        }
        
        resultSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
        // Handle exceptions that may occur during database access
    }
    
    System.out.println("Phone numbers retrieved: " + phoneNumbers); // Add this line for debugging
    
    return phoneNumbers;
}


    
private void switchToTableViewScene() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tableviewevents.fxml"));
        Parent tableViewRoot = loader.load();

        Scene tableViewScene = new Scene(tableViewRoot);

        Stage primaryStage = new Stage(); // Create a new stage for the TableView scene
        primaryStage.setScene(tableViewScene);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Handle any potential IOException
    }
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






