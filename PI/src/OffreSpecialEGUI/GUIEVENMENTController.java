package OffreSpecialEGUI;

import OffreSpecialEvenment.OffreSpecialEvenementCrud;
import OffreSpecialEvenment.OffreSpecialEvenment;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.json.JSONObject;
import pi.CarteFidelite;
import pi.CarteFidelite.NiveauCarte;


/**
 *
 * @author desig
 */
public class GUIEVENMENTController implements Initializable { 
    private OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();  
private FilteredList<OffreSpecialEvenment> filteredList;

    public int sd = 0;
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
private TableColumn<OffreSpecialEvenment, OffreSpecialEvenment.NiveauCarte> fxNiveauE;
@FXML
private TableView<OffreSpecialEvenment> fxTableOffreSpecial; 
private  ObservableList<OffreSpecialEvenment> listOffres;
    @FXML
    private Label fxlabelfhdslf;
    @FXML
    private Button fxChecckWeather;
   
    @FXML
    void navigateToAjouter(ActionEvent event) {
        sd=0;
        
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOffreSpecial.fxml"));
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
    if (idOffreSpecialEvenment >= 0) {
        int idOffreSpecialEvenment = getOffreSpecialClick();
        try {
            OffreSpecialEvenment offre = OffreSpecialEvenementCrud.getEventById(idOffreSpecialEvenment);
            String location = offre.getDestination();
            System.err.println(location);
            String weatherData = getWeather(location);

            // Load the Weather.fxml file and create a new scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        Parent root = loader.load();
        WeatherController dwc = loader.getController();



           dwc. fxWeatherLabel.setText(weatherData);


            Scene weatherScene = new Scene(root);
            Stage weatherStage = new Stage();
            weatherStage.setTitle("Weather Information");
            weatherStage.setScene(weatherScene);
            weatherStage.show();
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    } else {
        fxlabelfhdslf.setText("Please select an event");
    }
}


    
        
    /*private int getCarte() {
    int idCarte = -1; 
    index = fxTableCarte.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
        CarteFidelite selectedCarte = fxTableCarte.getItems().get(index);
        idCarte = selectedCarte.getIdCarte(); }
        else 
        {   fxLabel2.setText("plz select a card"); 
                
    }
    return idCarte;}  
*/ 
        public String s=""; 

@FXML 
public String getWeather(String location) 
{  
        try {
            String apiUrl = "http://api.weatherapi.com/v1/current.json?key=7035064ddd13403581800106232510&q=" + location;

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
                String location1 = jsonResponse.getJSONObject("location").getString("name");
                String condition = current.getJSONObject("condition").getString("text");
                double temperatureCelsius = current.getDouble("temp_c");
                double temperatureFahrenheit = current.getDouble("temp_f");
                
                // Print the weather information
                s+="Location: " + location +"\n";
                s+="Condition: " + condition + "\n";
                s+="Temperature (Celsius): " + temperatureCelsius + "°C" + "\n";
                s+="Temperature (Fahrenheit): " + temperatureFahrenheit + "°F" + "\n";
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return s;
    }

    
    


@FXML
public void supprimerOffreSpecialEvenment() {
    // Get the selected ID using the getOffreSpecialClick() method
    int idOffreSpecialEvenment = getOffreSpecialClick();

    if (idOffreSpecialEvenment >= 0) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Delete Offre Special Evenment");
        confirmationDialog.setContentText("Are you sure you want to delete this Offre Special Evenment?");

        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                
                fxlabelfhdslf.setText("Deleted Offre Special Evenment with ID: " + idOffreSpecialEvenment); 
                OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud(); 
                cnx2.supprimerOffreSpecialEvenmet(idOffreSpecialEvenment);
                
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

    private int index;
    private int idOffreSpecialEvenment; 
@FXML
private int getOffreSpecialClick() {
    System.out.println("executing");
    int idOffreSpecialEvenment = -1; 
    index = fxTableOffreSpecial.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
        OffreSpecialEvenment selectedOffreSpecialEvenment = fxTableOffreSpecial.getItems().get(index);
        idOffreSpecialEvenment = selectedOffreSpecialEvenment.getIdOffreSpecialEvenment(); }

  System.out.println("Debugging : " + idOffreSpecialEvenment);

    return idOffreSpecialEvenment;}  


    private void setupEditableColumnString(TableColumn<OffreSpecialEvenment, String> column, String field) {
    column.setCellFactory(TextFieldTableCell.forTableColumn());
    column.setOnEditCommit(event -> {
        int x = getOffreSpecialClick();
        OffreSpecialEvenment evenement = OffreSpecialEvenementCrud.getEventById(x);

        String newValue = event.getNewValue();
        switch (field) {
            case "titre":
                evenement.setTitre(newValue);
                break;
            case "description":
                evenement.setTypeEvenement(newValue);
                break;
            case "destination":
                evenement.setDestination(newValue);
                break;
            case "catégorie":
                evenement.setCatégorie(newValue);
                break;
            case "image":
                evenement.setImage(newValue);
                break;  
                
        }
        OffreSpecialEvenementCrud modifier = new OffreSpecialEvenementCrud();
        modifier.modifierOffreSpecialEvenment(evenement,x);
    });
}  
  /*  private void setupEditableColumnEnum(TableColumn<OffreSpecialEvenment,OffreSpecialEvenment.NiveauCarte> column, String field) {
    column.setCellFactory(ComboBoxTableCell.forTableColumn(NiveauCarte.values()));
    column.setOnEditCommit(event -> {
        int x = getOffreSpecialClick();
        OffreSpecialEvenment evenement = OffreSpecialEvenementCrud.getEventById(x);

        OffreSpecialEvenment.NiveauCarte newValue = event.getNewValue();
        switch (field) {
            case "niveau":
                evenement.setNiveau(newValue);
                break;
        }

        OffreSpecialEvenementCrud modifier = new OffreSpecialEvenementCrud();
        modifier.modifierOffreSpecialEvenment(evenement, x);
    });
}*/

  


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
    int x=getOffreSpecialClick();

    column.setOnEditCommit(event -> {
        OffreSpecialEvenment evenement = OffreSpecialEvenementCrud.getEventById(x);
        String dateString = event.getNewValue().toString(); // Convert java.util.Date to String
        Date newDate = Date.valueOf(dateString); 
        evenement.setDate_depart(newDate);
        OffreSpecialEvenementCrud modifier = new OffreSpecialEvenementCrud();
        modifier.modifierOffreSpecialEvenment(evenement,x);
    });
}

  
 




    private void setupEditableColumnInteger(TableColumn<OffreSpecialEvenment, Integer> column, String field) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column.setOnEditCommit(event -> {
            OffreSpecialEvenment evenment = event.getRowValue();
            int x=getOffreSpecialClick();
            int newValue = event.getNewValue();
            if (field.equals("guide_id")) { 
                
                evenment.setGuide_id(newValue);
            }
            OffreSpecialEvenementCrud modifier = new OffreSpecialEvenementCrud();
            modifier.modifierOffreSpecialEvenment(evenment,x);
        });
    }

    private void setupEditableColumnFloat(TableColumn<OffreSpecialEvenment, Float> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        column.setOnEditCommit(event -> {
            int x=getOffreSpecialClick();
            OffreSpecialEvenment evenement = OffreSpecialEvenementCrud.getEventById(x);
            evenement.setPrix(event.getNewValue());
            OffreSpecialEvenementCrud modifier = new OffreSpecialEvenementCrud();
            modifier.modifierOffreSpecialEvenment(evenement,x);
        });
    }


@Override
    public void initialize(URL url, ResourceBundle rb) {  
        setupEditableColumnString(fxTitreOffreSpecialE, "titre");
        setupEditableColumnString(fxCatOffreE,"catégorie");
        setupEditableColumnString(fxDescriptionOfrreSpecialE, "description");

        //setupEditableColumnDate(fxDateDepartOffre); 
       //    c_date.setCellFactory(TextFieldTableCell.forTableColumn(dateConverter)); 
       TableColumn<OffreSpecialEvenment, Integer> guideIdColumn = new TableColumn<>("Guide ID"); 
       setupEditableColumnInteger(fxGuideIdE, "guide_id");

       


        setupEditableColumnString(fxDestinationOffreE, "destination"); 
        setupEditableColumnString(fxImageOffreE, "image");  
         //setupEditableColumnString(fxNiveauE, "niveau"); 
         setupEditableColumnDate(fxDateDepartOffre);
         setupEditableColumnFloat(fxPrix); 
         //setupEditableColumnEnum(fxNiveauE, "niveau");

    
        fxTableOffreSpecial.setEditable(true);

       

fxTitreOffreSpecialE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("titre"));
fxDescriptionOfrreSpecialE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("description"));
fxDateDepartOffre.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Date>("date_depart"));
fxPrix.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Float>("prix"));
fxCatOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("catégorie"));
fxGuideIdE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Integer>("guide_id"));
fxDestinationOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("destination"));
fxImageOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("image"));
fxNiveauE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, OffreSpecialEvenment.NiveauCarte>("niveau")); 


listOffres = cnx2.listeDesOffres(); 

fxTableOffreSpecial.setItems(listOffres);
   /* FilteredList<OffreSpecialEvenment> filterData = new FilteredList<>(listOffres, b -> true);
    fxChercherOffre.textProperty().addListener((observable, oldValue, newValue) -> {
        filterData.setPredicate((OffreSpecialEvenment offre) -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            AtomicBoolean matchFound = new AtomicBoolean(false);

            if (String.valueOf(offre.getIdEvenement()).toLowerCase().contains(lowerCaseFilter)) {
                matchFound.set(true);
                System.out.println("Debug: Matching Id - " + offre.getIdEvenement());
            }

            if (matchFound.get()) {
                return true;
            } else {
                System.out.println("Debug: Checking other attributes");
                return offre.getTitre().toLowerCase().contains(lowerCaseFilter)
                    || offre.getTypeEvenement().toLowerCase().contains(lowerCaseFilter)
                    || offre.getDestination().toLowerCase().contains(lowerCaseFilter)
                    || offre.getDescription().toLowerCase().contains(lowerCaseFilter)
                    || offre.getImage().toLowerCase().contains(lowerCaseFilter)
                    || String.valueOf(offre.getGuide_id()).toLowerCase().contains(lowerCaseFilter)
                    || offre.getDate_depart().toString().toLowerCase().contains(lowerCaseFilter)
                    || offre.getCatégorie().toLowerCase().contains(lowerCaseFilter)
                    || offre.getNiveau().toString().toLowerCase().contains(lowerCaseFilter);
            }
        });
    });

    SortedList<OffreSpecialEvenment> sortedData = new SortedList<>(filterData);
    sortedData.comparatorProperty().bind(fxTableOffreSpecial.comparatorProperty());


    ;*/
   
   
   
}
}

        
            

        
   
    


 

