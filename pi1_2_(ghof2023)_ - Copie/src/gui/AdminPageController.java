/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class AdminPageController implements Initializable {

    @FXML
    private PieChart  pieChart;
     UserCRUD serviceuser = new UserCRUD();
    @FXML
    private Label lblNombreUtilisateurs;
    Integer NombreUtilisateurs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int nombreUtilisateurs = serviceuser.getNombreUtilisateurs();
        
        // TODO
        lblNombreUtilisateurs.setText("Ils nous ont fait confiance !\n" + NombreUtilisateurs);


    // Appelez une méthode pour récupérer les données depuis la base de données
    int hommes = serviceuser.getHommesCount(); // Remplacez par votre méthode réelle
    int femmes = serviceuser.getFemmesCount(); // Remplacez par votre méthode réelle

    // Créez des objets PieChart.Data
    PieChart.Data hommesData = new PieChart.Data("Hommes", hommes);
    PieChart.Data femmesData = new PieChart.Data("Femmes", femmes);

    // Créez une liste de données
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(hommesData, femmesData);

    // Ajoutez les données au PieChart
    pieChart.setData(pieChartData);

    // Vous pouvez également personnaliser le PieChart ici
    pieChart.setTitle("Répartition des sexes");
    //UserCRUD aold = serviceuser.readById(ConnexionUserController.id_modif);
    }    

    
}
