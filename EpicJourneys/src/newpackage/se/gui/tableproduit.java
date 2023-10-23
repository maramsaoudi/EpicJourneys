package newpackage.se.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class tableproduit extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tableproduit.fxml"));
        Parent root = loader.load();

        // Vous pouvez obtenir une référence au contrôleur si nécessaire
        TableproduitController controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Votre Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
