/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.app;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
   public static final String CURRENCY = "$";

   public main() {
   }

   public void start(Stage primaryStage) throws Exception {
       
       SharedModel sharedModel = new SharedModel();
       
        
        FXMLLoader sourceLoader = new FXMLLoader(getClass().getResource("RechercheWindow.fxml"));
        Parent sourceRoot = sourceLoader.load();
        RechercheWindowController sourceController = sourceLoader.getController();  
      //Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("RechercheWindow.fxml"));
      
      primaryStage.setTitle("resrver");
      primaryStage.setScene(new Scene(sourceRoot));
      primaryStage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
