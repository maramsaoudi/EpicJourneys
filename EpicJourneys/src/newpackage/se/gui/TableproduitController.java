import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import epicjourneys.se.entities.Produit;
import epicjourneys.se.utils.MyConnection; 

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.io.ByteArrayInputStream;

public class TableproduitController implements Initializable {
    @FXML
    private TableView<Produit> tableView;

    @FXML
    private TableColumn<Produit, String> nomCol;

    @FXML
    private TableColumn<Produit, String> prixCol;

    @FXML
    private TableColumn<Produit, Integer> stockCol;

    @FXML
    private TableColumn<Produit, Image> imageCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Produit> produits = retrieveProduitsFromDatabase();
        tableView.setItems(produits);
    }

    private ObservableList<Produit> retrieveProduitsFromDatabase() {
        ObservableList<Produit> produits = FXCollections.observableArrayList();

        Connection connection = MyConnection.getInstance().getCnx(); // Utilisation de MyConnection

        try {
            String sql = "SELECT * FROM Produit";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Produit produit = new Produit(
                    resultSet.getString("nom"),
                    resultSet.getString("prixUnitaire"),
                    resultSet.getInt("stock"),
                    resultSet.getString("image")
                );

                produits.add(produit);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
        }

        return produits;
    }
}
