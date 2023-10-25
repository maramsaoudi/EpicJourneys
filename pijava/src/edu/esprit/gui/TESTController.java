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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mkanz
 */
public class TESTController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Button btn_id;
    
     public String getIdValue() {
        return id.getText();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      @FXML
    private void redirectguide(ActionEvent event) {
        // Récupérer la valeur de l'ID du guide
        String guideId = id.getText();

        // Créer une nouvelle scène pour l'interface Guide
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Guide.fxml"));
            Parent root = loader.load();
            GuideController guideController = loader.getController();
            guideController.chargerEvenements(Integer.parseInt(guideId)); 
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre actuelle
            Stage currentStage = (Stage) btn_id.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
