package EvenmentSpecial.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.json.JSONObject;

import CRUD.OffreSpecialEvenementCrud;
import Entities.OffreSpecialEvenment;
import EvenmentSpecial.WeatherController;

/**
 *
 * @author desig
 */

public class GUIEVENMENTController implements Initializable {
    OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();

    @FXML
    private VBox weatherContainer;
    @FXML
    private Button fxNavCarte;
    @FXML
    private Button fxNavAjouter;
    @FXML
    private Button fxSupprimerOffreSpecial;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxTitreOffreSpecialE;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxDescriptionOfrreSpecialE;
    @FXML
    private TableColumn<OffreSpecialEvenment, Date> fxDateDepartOffre;
    @FXML
    private TableColumn<OffreSpecialEvenment, Float> fxPrix;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxCatOffreE;
    @FXML
    private TableColumn<OffreSpecialEvenment, Integer> fxGuideIdE;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxDestinationOffreE;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxImageOffreE;
    @FXML
    private TableColumn<OffreSpecialEvenment, String> fxNiveauE;
    @FXML
    private TableView<OffreSpecialEvenment> fxTableOffreSpecial;
    private ObservableList<OffreSpecialEvenment> listOffres;
    @FXML
    private Label fxlabelfhdslf;
    @FXML
    private Button fxChecckWeather;
    private int index;

    @FXML
    void navigateToAjouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ajouter/AjouterOffreSpecial.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) fxNavAjouter.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void NavToCarte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../../CarteFideliteAdmin/main/CarteFideliteAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) fxNavAjouter.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayWeatherData() {
        if (index >= 0) {
            try {
                OffreSpecialEvenment offre = getOffreSpecialClick();
                String location = offre.getDestination();
                String weatherData = getWeather(location);

                // Load the Weather.fxml file and create a new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Weather.fxml"));
                Parent root = loader.load();
                WeatherController dwc = loader.getController();

                dwc.fxWeatherLabel.setText(weatherData);

                Scene weatherScene = new Scene(root);
                Stage weatherStage = new Stage();
                weatherStage.setTitle("Weather Information");
                weatherStage.setScene(weatherScene);
                weatherStage.show();
            } catch (Exception e) {
                System.err.println("Unexpected Error: " + e.getMessage());
            }
        } else {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR);
            errorDialog.setTitle("Error");
            errorDialog.setHeaderText("No Selection");
            errorDialog.setContentText("Please select an Offre Special Evenment to delete.");
            errorDialog.showAndWait();
        }
    }

    @FXML
    public String getWeather(String location) {
        try {
            String apiUrl = "http://api.weatherapi.com/v1/current.json?key=7035064ddd13403581800106232510&q="
                    + location;

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the reader
                reader.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract the current weather data
                JSONObject current = jsonResponse.getJSONObject("current");
                String condition = current.getJSONObject("condition").getString("text");
                double temperatureCelsius = current.getDouble("temp_c");
                double temperatureFahrenheit = current.getDouble("temp_f");
                String s = "Location: " + location + "\n ";
                // Print the weather information
                s += s += "Condition: " + condition + "\n";
                s += "Temperature (Celsius): " + temperatureCelsius + "°C" + "\n";
                s += "Temperature (Fahrenheit): " + temperatureFahrenheit + "°F" + "\n";
                return s;

            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    @FXML
    public void supprimerOffreSpecialEvenment() {
        if (index >= 0) {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Delete Offre Special Evenment");
            confirmationDialog.setContentText("Are you sure you want to delete this Offre Special Evenment?");

            confirmationDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    OffreSpecialEvenment offreSpecialEvenment = getOffreSpecialClick();
                    cnx2.supprimerOffreSpecialEvenmet(offreSpecialEvenment.getIdEvenement());
                    listOffres = cnx2.afficherEvenements();

                    fxTableOffreSpecial.setItems(listOffres);

                }
            });
        } else {
            Alert errorDialog = new Alert(Alert.AlertType.ERROR);
            errorDialog.setTitle("Error");
            errorDialog.setHeaderText("No Selection");
            errorDialog.setContentText("Please select an Offre Special Evenment to delete.");
            errorDialog.showAndWait();
        }
    }

    @FXML
    private OffreSpecialEvenment getOffreSpecialClick() {
        index = fxTableOffreSpecial.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            OffreSpecialEvenment selectedOffreSpecialEvenment = fxTableOffreSpecial.getItems().get(index);

            return selectedOffreSpecialEvenment;
        }
        return new OffreSpecialEvenment();

    }

    private void setupEditableColumnString(TableColumn<OffreSpecialEvenment, String> column, String field) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            OffreSpecialEvenment evenement = getOffreSpecialClick();

            String newValue = event.getNewValue();
            switch (field) {
                case "titre":
                    evenement.setTitre(newValue);
                    break;
                case "description":
                    evenement.setDescription(newValue);
                    break;
                case "destination":
                    evenement.setDestination(newValue);
                    break;
                case "image":
                    evenement.setImage(newValue);
                    break;
                case "catégorie":
                    evenement.setTypeEvenement(newValue);
                    break;

            }

            cnx2.modifierOffreSpecialEvenment(evenement);
        });
    }

    private void setupEditableColumnDate(TableColumn<OffreSpecialEvenment, Date> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public String toString(Date date) {
                return (date != null) ? dateFormat.format(date) : "";
            }

            @Override
            public Date fromString(String string) {
                try {
                    return (string != null && !string.isEmpty()) ? new Date(dateFormat.parse(string).getTime()) : null;
                } catch (ParseException e) {
                    return null;
                }
            }
        }));
        OffreSpecialEvenment offreSpecialEvenment = getOffreSpecialClick();

        column.setOnEditCommit(event -> {

            String dateString = event.getNewValue().toString(); // Convert java.util.Date to String
            Date newDate = Date.valueOf(dateString);
            offreSpecialEvenment.setDate_depart(newDate);

            cnx2.modifierOffreSpecialEvenment(offreSpecialEvenment);
        });
    }

    private void setupEditableColumnInteger(TableColumn<OffreSpecialEvenment, Integer> column, String field) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column.setOnEditCommit(event -> {
            OffreSpecialEvenment offreSpecialEvenment = getOffreSpecialClick();

            int newValue = event.getNewValue();
            if (field.equals("guide_id")) {
                offreSpecialEvenment.setGuide_id(newValue);
            }

            cnx2.modifierOffreSpecialEvenment(offreSpecialEvenment);
        });
    }

    private void setupEditableColumnFloat(TableColumn<OffreSpecialEvenment, Float> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        column.setOnEditCommit(event -> {
            OffreSpecialEvenment offreSpecialEvenment = getOffreSpecialClick();
            OffreSpecialEvenementCrud offreSpecialEvenementCrud = new OffreSpecialEvenementCrud();
            OffreSpecialEvenment evenement = offreSpecialEvenementCrud
                    .getEventById(offreSpecialEvenment.getIdEvenement());
            evenement.setPrix(event.getNewValue());

            cnx2.modifierOffreSpecialEvenment(evenement);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupEditableColumnString(fxTitreOffreSpecialE, "titre");
        setupEditableColumnString(fxCatOffreE, "catégorie");
        setupEditableColumnString(fxDescriptionOfrreSpecialE, "description");

        // setupEditableColumnDate(fxDateDepartOffre);
        // c_date.setCellFactory(TextFieldTableCell.forTableColumn(dateConverter));
        setupEditableColumnInteger(fxGuideIdE, "guide_id");

        setupEditableColumnString(fxDestinationOffreE, "destination");
        setupEditableColumnString(fxImageOffreE, "image");
        setupEditableColumnString(fxNiveauE, "niveau");
        setupEditableColumnDate(fxDateDepartOffre);
        setupEditableColumnFloat(fxPrix);

        fxTableOffreSpecial.setEditable(true);

        fxTitreOffreSpecialE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("titre"));
        fxDescriptionOfrreSpecialE
                .setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("description"));
        fxDateDepartOffre.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Date>("date_depart"));
        fxPrix.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Float>("prix"));
        fxCatOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("TypeEvenement"));
        fxGuideIdE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Integer>("guide_id"));
        fxDestinationOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("destination"));
        fxImageOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("image"));
        fxNiveauE.setCellValueFactory(
                new PropertyValueFactory<OffreSpecialEvenment, String>("niveau"));
        OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();
        listOffres = cnx2.afficherEvenements();

        fxTableOffreSpecial.setItems(listOffres);

    }
}
