/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mkanz
 */
public class MessageBoxController implements Initializable {

    @FXML
    private Button btnmsgboxsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fermerFenetre(ActionEvent event) {
        // Obtenez la source de l'événement (le bouton "OK" qui a été cliqué)
        Button source = (Button) event.getSource();

        // Obtenez la scène associée à la source
        Scene scene = source.getScene();

        // Obtenez la fenêtre (stage) associée à la scène
        Stage stage = (Stage) scene.getWindow();

        // Fermez la fenêtre (stage)
        stage.close();
    }

    
}
