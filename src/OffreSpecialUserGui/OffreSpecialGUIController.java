package OffreSpecialUserGui;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CRUD.OffreSpecialEvenementCrud;
import Entities.OffreSpecialEvenment;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OffreSpecialGUIController implements Initializable {
    @FXML
    private VBox eventContainer;

    private OffreSpecialEvenementCrud offreSpecialEvenementCrud = new OffreSpecialEvenementCrud();

    private void displayEvents(List<OffreSpecialEvenment> events) {
        for (OffreSpecialEvenment event : events) {
            HBox eventBox = new HBox();
            eventBox.setSpacing(10);

            ImageView eventImage = new ImageView();
            File file = new File(event.getImage());
            Image image = new Image(file.toURI().toString());
            eventImage.setImage(image);
            eventImage.setFitWidth(100);
            eventImage.setPreserveRatio(true);

            Label titleLabel = new Label(event.getTitre());
            Label dateLabel = new Label("Date: " + event.getDate_depart());
            Label descriptionLabel = new Label("Description: " + event.getDescription());

            eventBox.getChildren().addAll(eventImage, titleLabel, dateLabel, descriptionLabel);

            eventContainer.getChildren().add(eventBox);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<OffreSpecialEvenment> offreSpecialEvenments = offreSpecialEvenementCrud.afficherEvenements();
        displayEvents(offreSpecialEvenments);

    }

    @FXML
    void NavToLoyalty(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../CarteFideliteUser/GUI/UICarte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
