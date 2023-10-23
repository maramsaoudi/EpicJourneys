/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialEGUI;

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

/**
 *
 * @author desig
 */
public class OffreSpecialEvenmentGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) { 
        Parent root;
        try {
           root = FXMLLoader.load(getClass().getResource("GUIEVENMENTAdmin.fxml"));
           Scene scene = new Scene(root);
            primaryStage.setTitle("EVENMENT Special");
            primaryStage.setScene(scene); 
            primaryStage.show();  

        } catch (IOException ex) {
            System.out.println(ex.getMessage());  } 
        
        
        
    } 
    

    
    public static void main(String[] args) {
        launch(args);
    }
    
}