
package edu.esprit.gui;
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
 * @author mkanz
 */
public class test extends Application {
    
   public void start(Stage primaryStage) {
        try {
            // Chargez le fichier FXML de la fenêtre tableviewevents.fxml
            Parent root = FXMLLoader.load(getClass().getResource("TEST.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("testguide");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
