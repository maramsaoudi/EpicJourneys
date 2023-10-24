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
    private final ImageView imageView = new ImageView();

    {
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setPrefHeight(100); // Adjust the height as needed

        imageView.setFitHeight(80); // Adjust the size of the image view
        imageView.setPreserveRatio(true);
    }

    @Override
    protected void updateItem(OffreSpecialEvenment item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            try {
                Image image = new Image(item.getImage()); // Load the image from the URL
                imageView.setImage(image);
            } catch (Exception e) {
                System.err.println("Errorloading image: " + e.getMessage());
                imageView.setImage(new Image("default.jpg"));
            }

            setGraphic(imageView);
        }
    }
});






ListViewOffre.setCellFactory(TextFieldListCell.forListView(new StringConverter<OffreSpecialEvenment>() {
    @Override
    public String toString(OffreSpecialEvenment object) {
        return object != null ? object.toString() : "";
    }

    @Override
    public OffreSpecialEvenment fromString(String string) {
        // You can implement this method if you need to edit the items in the list
        return null;
    }
})); 



   
/*ListViewOffre.setCellFactory(param -> new ListCell<OffreSpecialEvenment>() {
    @Override
    protected void updateItem(OffreSpecialEvenment item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText("Item: " + item.toString());
        }
    }
});*/

/*ListViewOffre.setCellFactory(param -> new ListCell<OffreSpecialEvenment>() {
    @Override
    protected void updateItem(OffreSpecialEvenment item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText("Item: " + item.toString());        }
    }
});
  */
} 
} 


