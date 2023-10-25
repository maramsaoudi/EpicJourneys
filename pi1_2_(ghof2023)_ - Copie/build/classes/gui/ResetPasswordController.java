/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.JOptionPane;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField RNewPassword;
    @FXML
    private TextField RVerifyPassword;
    @FXML
    private Button RReset;
    @FXML
    private Button rannuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      

  /*  @FXML
    private void Reset(ActionEvent event) {
        if(RNewPassword.getText().equals(RVerifyPassword.getText())){
//check whether the user enter same password in both textfield
try{
String updateQuery = "UPDATE user SET `motdepasse`=? WHERE email=?";
con = DriverManager.getConnection("jdbc:mysql://localhost/pi", "root","");
pst=con.prepareStatement(updateQuery);
pst.setString(1, RVerifyPassword.getText());
pst.setString(2, user);
pst.executeUpdate();
JOptionPane.showMessageDialog(null, "Reset Successfully");


}catch(Exception ex){
JOptionPane.showMessageDialog(null, ex);
}
}else{
JOptionPane.showMessageDialog(null, "password do not match");
    }
    }*/
    
    }

    @FXML
    private void Reset(ActionEvent event) {
         Alert A = new Alert(Alert.AlertType.INFORMATION);
        if (!RNewPassword.getText().equals("") && RNewPassword.getText().equals(RVerifyPassword.getText())) {
            UserCRUD user = new UserCRUD();
            user.ResetPassword(MdpOubliéUserController.EmailReset, RNewPassword.getText());
            A.setContentText("Mot de passe modifié avec succes ! ");
            A.show();
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            Parent page1 = loader.load();
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        } else {
            A.setContentText("veuillez saisir un mot de passe conforme !");
            A.show();
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
         try {

            Parent page1 = FXMLLoader.load(getClass().getResource("Sign.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
    }
    }

