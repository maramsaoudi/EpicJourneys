/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class VotreCodeController implements Initializable {

    @FXML
    private TextField fentervotrecode;
    @FXML
    private Button vconfirm;
    @FXML
    private Button vannuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmer(ActionEvent event) {
        if(fentervotrecode.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Champs vide !");
        alert.showAndWait();
        }else{
        if (Integer.parseInt(fentervotrecode.getText()) == MdpOubliéUserController.code)
        {
              try {

            Parent page1 = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
        }
        else 
        {
            Alert A = new Alert(Alert.AlertType.WARNING);
            A.setContentText("Code erroné ! ");
            A.show();
            
        }
    }
    }

    @FXML
    private void annuler(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("MdpOubliéUser.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }
    }
    

