package CarteFideliteUser.GUI;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import CRUD.CarteFideliteCrud;
import Entities.CarteFidelite;
import Entities.CarteFidelite.NiveauCarte;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

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
    @FXML
    private Label fxOffreCartelabel;
    int id = 24;
    CarteFideliteCrud carteFideliteCrud = new CarteFideliteCrud();

    private CarteFidelite carteFidelite;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carteFidelite = carteFideliteCrud.getCarteFideliteById(id);
        reloadUI();
    }

    void reloadUI() {
        fxOptional.setVisible(
                carteFidelite.getPtsFidelite() > 1000 && carteFidelite.getNiveauCarte() != NiveauCarte.gold);
        fxUpgradeCarte.setDisable(
                !(carteFidelite.getPtsFidelite() >= 1000 && carteFidelite.getNiveauCarte() != NiveauCarte.gold));

        fxOptional.visibleProperty().bind(
                Bindings.and(
                        Bindings.createBooleanBinding(() -> carteFidelite.getPtsFidelite() > 1000,
                                ptsFideliteProperty()),
                        Bindings.createBooleanBinding(() -> carteFidelite.getNiveauCarte() != NiveauCarte.gold,
                                niveauProperty())));
        fxUpgradeCarte.disableProperty().bind(
                Bindings.not(
                        Bindings.and(
                                Bindings.createBooleanBinding(() -> carteFidelite.getPtsFidelite() >= 1000,
                                        ptsFideliteProperty()),
                                Bindings.createBooleanBinding(() -> carteFidelite.getNiveauCarte() != NiveauCarte.gold,
                                        niveauProperty()))));
        fxBadge.imageProperty().bind(Bindings.createObjectBinding(this::getBadgeImage, niveauProperty()));
        fxNbrPtsFidelite.textProperty().bind(ptsFideliteProperty().asString().concat(" points"));
        fxMoney.textProperty().bind(ptsFideliteProperty().divide(100).asString().concat(" TND"));

        fxUpgradeCarte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert confirmation = new Alert(AlertType.CONFIRMATION);
                confirmation.setTitle("Upgrade Confirmation");
                confirmation.setHeaderText("Confirm Upgrade");
                confirmation.setContentText("Do you want to upgrade the card?");
                confirmation.initOwner(fxUpgradeCarte.getScene().getWindow());

                ButtonType result = confirmation.showAndWait().orElse(ButtonType.CANCEL);
                if (result == ButtonType.OK) {
                    if (carteFidelite.getPtsFidelite() >= 1000 && carteFidelite.getNiveauCarte() != NiveauCarte.gold) {
                        carteFidelite = carteFideliteCrud.UpgradeCarte(carteFidelite);

                    }
                }
            }
        });

    }

    @FXML
    public void NavToSpecialEvent(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../OffreSpecialUserGui/OffreSpecialGUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Carte Fidelite");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private Image getBadgeImage() {
        String imagePath = "";
        if (carteFidelite.getNiveauCarte() == NiveauCarte.gold) {
            imagePath = "../../public/gold.png";
        } else if (carteFidelite.getNiveauCarte() == NiveauCarte.silver) {
            imagePath = "../../public/silver.png";
        } else {
            imagePath = "../../public/bronze.png";
        }

        return new Image(getClass().getResource(imagePath).toString());
    }

    // Define property accessors for ptsFidelite and niveau
    public IntegerProperty ptsFideliteProperty() {
        return new SimpleIntegerProperty(carteFidelite.getPtsFidelite());
    }

    public ObjectProperty<NiveauCarte> niveauProperty() {
        return new SimpleObjectProperty<>(carteFidelite.getNiveauCarte());
    }

}
