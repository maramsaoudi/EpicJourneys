/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarteFideliteAdmin.main;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import CRUD.CarteFideliteCrud;
import CarteFideliteAdmin.modifierCarte.modifierCarteController;
import Entities.CarteFidelite;
import Entities.CarteFidelite.EtatCarte;
import Entities.CarteFidelite.NiveauCarte;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author desig
 */
public class CarteFideliteAdminController implements Initializable {

    @FXML
    private TableView<CarteFidelite> fxTableCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxPtsFidelite;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateDebut;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateFin;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdClient;
    @FXML
    private TableColumn<CarteFidelite, EtatCarte> fxEtatCarte;
    @FXML
    private TableColumn<CarteFidelite, NiveauCarte> fxNiveauCarte;
    ObservableList<CarteFidelite> listC = CarteFideliteCrud.listeDesEntites1();
    CarteFideliteCrud cnx2 = new CarteFideliteCrud();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxIdCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite, Integer>("IdCarte"));
        fxPtsFidelite.setCellValueFactory(new PropertyValueFactory<CarteFidelite, Integer>("PtsFidelite"));
        fxDateDebut.setCellValueFactory(new PropertyValueFactory<CarteFidelite, Date>("DateDebut"));
        fxDateFin.setCellValueFactory(new PropertyValueFactory<CarteFidelite, Date>("DateFin"));
        fxIdClient.setCellValueFactory(new PropertyValueFactory<CarteFidelite, Integer>("IdClient"));
        fxEtatCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite, EtatCarte>("EtatCarte"));
        fxNiveauCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite, NiveauCarte>("NiveauCarte"));
        fxTableCarte.setItems(listC);

        fxTableCarte.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                CarteFidelite selectedItem = fxTableCarte.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    handleDoubleClick(selectedItem, event);
                }
            }
        });
    }

    void handleDoubleClick(CarteFidelite carteFidelite, MouseEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("../modifierCarte/modifierCarte.fxml"));
            Parent root = loader.load();
            modifierCarteController controller = loader.getController();
            controller.setCarteFidelite(carteFidelite);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void RedirectToAjouter(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ajouterCarte/AjouterCarte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void NavToSpecialEvents(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../../EvenmentSpecial/main/GUIEVENMENTAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
