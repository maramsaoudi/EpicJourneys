package CarteFideliteUser.GUI;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pi.CarteFidelite.NiveauCarte;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import pi.CarteFideliteCrud;
import pi.myConnection;

public class UICarteController implements Initializable {
  
    @FXML
    private Label fxNbrPtsFidelite;
    @FXML
    private Label fxMoney;
    @FXML
    private ImageView fxBadge;
    @FXML
    private Label fxOptional;
    @FXML
    private Button fxUpgradeCarte;

    private int ptsFidelite = 1000; 
    private NiveauCarte niveau = NiveauCarte.silver; 
    @FXML
    private Label fxOffreCartelabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // Set the initial visibility based on the initial conditions
            fxOptional.setVisible(ptsFidelite > 1000 && niveau != NiveauCarte.gold);
            fxUpgradeCarte.setDisable(!(ptsFidelite >= 1000 && niveau != NiveauCarte.gold )); 
            int x=CarteFideliteCrud.getNbrOffreSpecial(niveau);
            fxOffreCartelabel.setText("we have " + x + " special offers for you ");
            
            
           
            
            
            // Bind visibility to properties
            fxOptional.visibleProperty().bind(
                    Bindings.and(
                            Bindings.createBooleanBinding(() -> ptsFidelite > 1000, ptsFideliteProperty()),
                            Bindings.createBooleanBinding(() -> niveau != NiveauCarte.gold, niveauProperty())
                    )
            );
            fxUpgradeCarte.disableProperty().bind(
                    Bindings.not(
                            Bindings.and(
                                    Bindings.createBooleanBinding(() -> ptsFidelite >= 1000, ptsFideliteProperty()),
                                    Bindings.createBooleanBinding(() -> niveau != NiveauCarte.gold, niveauProperty())
                            )
                    ));
            fxBadge.imageProperty().bind(Bindings.createObjectBinding(this::getBadgeImage,niveauProperty())); 
            fxNbrPtsFidelite.textProperty().bind(ptsFideliteProperty().asString().concat(" points"));
            fxMoney.textProperty().bind(ptsFideliteProperty().divide(100).asString().concat(" TND"));
            
            fxUpgradeCarte.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // Show a confirmation dialog
                    Alert confirmation = new Alert(AlertType.CONFIRMATION);
                    confirmation.setTitle("Upgrade Confirmation");
                    confirmation.setHeaderText("Confirm Upgrade");
                    confirmation.setContentText("Do you want to upgrade the card?");
                    confirmation.initOwner(fxUpgradeCarte.getScene().getWindow()); 
                    
                    ButtonType result = confirmation.showAndWait().orElse(ButtonType.CANCEL);
                    if (result == ButtonType.OK) {
                        if (ptsFidelite >= 1000 && niveau != NiveauCarte.gold) {
                            
                            //upgrade Carte
                        }
                    }
                }
            }); 
        
                             

    } 
private Image getBadgeImage() {
    String imagePath = "";
    if (niveau == NiveauCarte.gold) {
        imagePath = "/CarteFideliteUser/GUI/gold.png";
    } else if (niveau == NiveauCarte.silver) {
        imagePath = "/CarteFideliteUser/GUI/silver.png";
    } else {
        imagePath = "/CarteFideliteUser/GUI/bronze.png";
    }

    return new Image(getClass().getResource(imagePath).toString());
}

   
        
    

    // Define property accessors for ptsFidelite and niveau
    public IntegerProperty ptsFideliteProperty() {
        return new SimpleIntegerProperty(ptsFidelite);
    }

    public ObjectProperty<NiveauCarte> niveauProperty() {
        return new SimpleObjectProperty<>(niveau);
    }

    
}
