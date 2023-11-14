/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Utils.MyConnection;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javafx.application.Application.launch;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import services.EMailSender;

/**
 *
 * @author ghofr
 */
public class Home extends Application {
     
    @Override
    public void start(Stage primaryStage) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("House.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Welcome ");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("ex.getMessage()");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
