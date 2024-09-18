package EvenmentSpecial.ajouter;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjouterOffreSpecial extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = getClass().getResource("./AjouterOffreSpecial.fxml");
            System.out.println(url);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("EVENMENT Special");
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
