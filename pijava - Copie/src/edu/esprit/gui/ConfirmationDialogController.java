/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ConfirmationDialogController {
    // Property to hold the selected event
    private Evenement selectedEvent;

    // Property to track if "Yes" was confirmed
    private boolean confirmed = false;

    // Getter and setter for the selected event
    public Evenement getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Evenement selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    // Method to handle "Yes" button click
    @FXML
    private void confirm(ActionEvent event) {
        confirmed = true;
        // Close the dialog or perform any other action
        // you want when "Yes" is confirmed.
        // You might use a reference to the dialog's stage to close it.
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // Method to handle "No" button click
    @FXML
    private void cancel(ActionEvent event) {
        // Close the dialog or perform any other action
        // you want when "No" is chosen.
        // You might use a reference to the dialog's stage to close it.
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    // Method to check if "Yes" was confirmed
    public boolean isConfirmed() {
        return confirmed;
    }
}

    

