/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author mkanz
 */
public class GuideController implements Initializable {
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the date format as needed

     @FXML
    private TableView<Evenement> id_tableviewevents;
    @FXML
    private TableColumn<Evenement, String> c_titre;
    @FXML
    private TableColumn<Evenement, String> c_type;
    @FXML
    private TableColumn<Evenement, String> c_destination;
    @FXML
    private TableColumn<Evenement, String> c_description;
    @FXML
    private TableColumn<Evenement, Date> c_date;

    @FXML
    private Button btnfermerguide;
    @FXML
    private Label countdownLabel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("TypeEvenement"));
        c_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
       
        id_tableviewevents.setEditable(true);

        StringConverter<Date> dateConverter = new StringConverter<Date>() {
            @Override
            public String toString(Date object) {
                return object.toString();
            }

            @Override
            public Date fromString(String string) {
                return Date.valueOf(string);
            }
        };

        StringConverter<Integer> intConverter = new IntegerStringConverter();
        StringConverter<Float> floatConverter = new FloatStringConverter();

        c_date.setCellFactory(TextFieldTableCell.forTableColumn(dateConverter));
       
          id_tableviewevents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            updateCountdown();
        }
    });
    startCountdownTimer();

    }    

public void chargerEvenements(int guideId) {
    List<Evenement> evenements = getEvenementsForGuide(guideId);
    id_tableviewevents.getItems().addAll(evenements);
}

public List<Evenement> getEvenementsForGuide(int guideId) {
        List<Evenement> evenementsAffectes = new ArrayList<>();

        try {
             MyConnection myConnection = MyConnection.getInstance();
        Connection dbConnection = myConnection.getCnx();
            String query = "SELECT titre,TypeEvenement,destination,description,date_depart FROM evenement WHERE guide_id = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, guideId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Evenement evenement = new Evenement();
                 evenement.setTitre(resultSet.getString("titre"));
        evenement.setTypeEvenement(resultSet.getString("TypeEvenement")); 
        evenement.setDestination(resultSet.getString("destination"));
        evenement.setDescription(resultSet.getString("description"));
        evenement.setDate_depart(resultSet.getDate("date_depart"));
                evenementsAffectes.add(evenement);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return evenementsAffectes;
    }
    
 
    
private void updateCountdown() {
    Evenement selectedEvent = id_tableviewevents.getSelectionModel().getSelectedItem();

    if (selectedEvent != null) {
      java.util.Date eventDate = selectedEvent.getDate_depart();
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        java.sql.Date eventSqlDate = new java.sql.Date(eventDate.getTime());

        long timeLeft = eventSqlDate.getTime() - currentDate.getTime();

        if (timeLeft > 0) {
            long seconds = timeLeft / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            seconds %= 60;
            minutes %= 60;
            hours %= 24;

            String countdownText = "Time Left: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s";
            countdownLabel.setText(countdownText);
        } else {
            countdownLabel.setText("Event has passed.");
        }
    } else {
        countdownLabel.setText("No event selected.");
    }
}



private Date findNearestEventDate() {
    Evenement selectedEvent = id_tableviewevents.getSelectionModel().getSelectedItem();
    
    if (selectedEvent != null) {
        return (Date) selectedEvent.getDate_depart();
    } else {
        return null;
    }
}

    
    
    
    
    
    private void startCountdownTimer() {
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> updateCountdown());
    Timeline timeline = new Timeline(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}

  
    
     @FXML
private void Fermerguide(ActionEvent event) {
   try {

            Parent page1 = FXMLLoader.load(getClass().getResource("Sign.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
                

}}






    

