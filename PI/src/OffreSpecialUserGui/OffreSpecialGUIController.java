package OffreSpecialUserGui;

import java.util.List;
import OffreSpecialEvenment.OffreSpecialEvenment;
import OffreSpecialEvenment.OffreSpecialEvenementCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class OffreSpecialGUIController implements Initializable { 
    
    

    @FXML
    private ListView<OffreSpecialEvenment> ListViewOffre; 
    

    
 
@Override
public void initialize(URL url, ResourceBundle rb) {
    OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();
    List<OffreSpecialEvenment> offreList = cnx2.afficherOffreSpecial();
    ObservableList<OffreSpecialEvenment> observableList = FXCollections.observableArrayList(offreList);
    
    ListViewOffre.setItems(observableList);

    ListViewOffre.setCellFactory(param -> new ListCell<OffreSpecialEvenment>() { 
            private final Text text = new Text();

        @Override
        protected void updateItem(OffreSpecialEvenment item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                try {
                    ImageView imageView = new ImageView(new Image(item.getImage())); // Load the image from the URL
                    imageView.setFitHeight(120); // Adjust the size of the image view
                    imageView.setPreserveRatio(true); 
                    
                    setGraphic(imageView); 
                     text.setText("Item: " + item.toString());

                } catch (Exception e) {
    System.err.println("Error loading image: " + e.getMessage());

    ImageView imageView = new ImageView(new Image("file:///C:/Users/desig/OneDrive/Desktop/memes/default.jpg")); // Load the image from a local file

    imageView.setFitHeight(120); // Adjust the size of the image view
    imageView.setPreserveRatio(true); 
    text.setStyle("-fx-padding: 10 10 10 10;");
    setGraphic(imageView);

                }
            }
        }
    });
}
}

