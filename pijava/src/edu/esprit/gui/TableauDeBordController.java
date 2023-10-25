package edu.esprit.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import edu.esprit.utils.MyConnection;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TableauDeBordController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private Button btnclosedashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Créez une série de données pour le graphique
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre d'événements par destination");

        // Connexion à la base de données
        MyConnection myConnection = MyConnection.getInstance();
        Connection dbConnection = myConnection.getCnx();

        try {
            String query = "SELECT destination, COUNT(*) AS count FROM evenements GROUP BY destination";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String destination = resultSet.getString("destination");
                int count = resultSet.getInt("count");
                series.getData().add(new XYChart.Data<>(destination, count));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ajoutez la série au graphique
        barChart.getData().add(series);
    }

    @FXML
    private void closeActiondashboard(ActionEvent event) {
        
    // Get the source node (the button that was clicked)
    Node source = (Node) event.getSource();

    // Get the stage (window) that contains the source node
    Stage stage = (Stage) source.getScene().getWindow();

    // Close the stage
    stage.close();
}

    }

