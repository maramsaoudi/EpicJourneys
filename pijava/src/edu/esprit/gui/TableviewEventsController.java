package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import edu.esprit.services.EvenementCrud;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Scene;
import javafx.stage.Modality;


public class TableviewEventsController implements Initializable {

    @FXML
    private TableView<Evenement> id_tableviewevents;
    @FXML
    private Button btnajoutertableview;
    @FXML
    private Button btnsupprimertableview;
    @FXML
    private TableColumn<Evenement, String> c_titre;
    @FXML
    private TableColumn<Evenement, String> c_type;
    @FXML
    private TableColumn<Evenement, String> c_destination;
    @FXML
    private TableColumn<Evenement, String> c_description;
    @FXML
    private TableColumn<Evenement, String> c_image;
    @FXML
    private TableColumn<Evenement, Date> c_date;
    @FXML
    private TableColumn<Evenement, Integer> c_guide;
    @FXML
    private TableColumn<Evenement, String> c_sponsor;
    @FXML
    private TableColumn<Evenement, Float> c_prix;
    @FXML
    private Button tableaudebord;
    @FXML
    private Button btnsponsor;
    @FXML
    private Button btnLOGOUT;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("TypeEvenement"));
        c_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        c_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        c_date.setCellValueFactory(new PropertyValueFactory<>("date_depart"));
        c_guide.setCellValueFactory(new PropertyValueFactory<>("guide_id"));
        c_sponsor.setCellValueFactory(new PropertyValueFactory<>("SponsorEvenement"));
        c_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        id_tableviewevents.setEditable(true);

        StringConverter<Date> dateConverter = new StringConverter<Date>() {
            @Override
            public String toString(Date object) {
                return object.toString();
            }

            @Override
            public Date fromString(String string) {
                return Date.valueOf(string);
            }
        };

        StringConverter<Integer> intConverter = new IntegerStringConverter();
        StringConverter<Float> floatConverter = new FloatStringConverter();

        c_date.setCellFactory(TextFieldTableCell.forTableColumn(dateConverter));
        c_guide.setCellFactory(TextFieldTableCell.forTableColumn(intConverter));
        c_prix.setCellFactory(TextFieldTableCell.forTableColumn(floatConverter));

        loadEvenementData();

        handleEditEvents();
    }

    private void loadEvenementData() {
        try {
            ObservableList<Evenement> evenements = FXCollections.observableArrayList();
            MyConnection myConnection = MyConnection.getInstance();
            Connection dbConnection = myConnection.getCnx();
            String selectQuery = "SELECT titre, description, date_depart, prix, TypeEvenement, guide_id, destination, image, SponsorEvenement FROM evenements";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement evenement = new Evenement(
                    resultSet.getString("TypeEvenement"),
                    resultSet.getDate("date_depart"),
                    resultSet.getString("description"),
                    resultSet.getString("destination"),
                    resultSet.getInt("guide_id"),
                    resultSet.getString("image"),
                    resultSet.getFloat("prix"),
                    resultSet.getString("titre"),
                    resultSet.getString("SponsorEvenement")
                );
                evenements.add(evenement);
                System.out.println("ADDED!");
            }
            resultSet.close();
            preparedStatement.close();
            id_tableviewevents.setItems(evenements);
            System.out.println("Data added to TableView!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleEditEvents() {
        setupEditableColumnString(c_titre, "titre");
        setupEditableColumnString(c_type, "TypeEvenement");
        setupEditableColumnString(c_destination, "destination");
        setupEditableColumnString(c_description, "description");
        setupEditableColumnString(c_image, "image");
        setupEditableColumnDate(c_date);
        setupEditableColumnInteger(c_guide, "guide_id");
        setupEditableColumnString(c_sponsor, "SponsorEvenement");
        setupEditableColumnFloat(c_prix);
    }

    private void setupEditableColumnString(TableColumn<Evenement, String> column, String field) {
    column.setCellFactory(TextFieldTableCell.forTableColumn());
    column.setOnEditCommit(event -> {
        Evenement evenement = event.getRowValue();
        String newValue = event.getNewValue();
        switch (field) {
            case "titre":
                evenement.setTitre(newValue);
                break;
            case "TypeEvenement":
                evenement.setTypeEvenement(newValue);
                break;
            case "destination":
                evenement.setDestination(newValue);
                break;
            case "description":
                evenement.setDescription(newValue);
                break;
            case "image":
                evenement.setImage(newValue);
                break;
            case "SponsorEvenement":
                evenement.setSponsorEvenement(newValue);
                break;
        }
        EvenementCrud modifier = new EvenementCrud();
        modifier.modifierEvenement(evenement);
    });
}


 private void setupEditableColumnDate(TableColumn<Evenement, Date> column) {
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

    column.setOnEditCommit(event -> {
        Evenement evenement = event.getRowValue();
        String dateString = event.getNewValue().toString(); // Convert java.util.Date to String
        Date newDate = Date.valueOf(dateString); // Convert String back to java.util.Date
        evenement.setDate_depart(newDate);
        EvenementCrud modifier = new EvenementCrud();
        modifier.modifierEvenement(evenement);
    });
}

  
 




    private void setupEditableColumnInteger(TableColumn<Evenement, Integer> column, String field) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column.setOnEditCommit(event -> {
            Evenement evenement = event.getRowValue();
            int newValue = event.getNewValue();
            if (field.equals("guide_id")) {
                evenement.setGuide_id(newValue);
            }
            EvenementCrud modifier = new EvenementCrud();
            modifier.modifierEvenement(evenement);
        });
    }

    private void setupEditableColumnFloat(TableColumn<Evenement, Float> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        column.setOnEditCommit(event -> {
            Evenement evenement = event.getRowValue();
            evenement.setPrix(event.getNewValue());
            EvenementCrud modifier = new EvenementCrud();
            modifier.modifierEvenement(evenement);
        });
    }

   @FXML
private void openAjouterevenement(ActionEvent event) {
    // Close the initial table view
    Stage currentStage = (Stage) id_tableviewevents.getScene().getWindow();
    currentStage.close();

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterevenement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage ajouterevenementStage = new Stage();

        // Get the controller
        AjouterevenementController ajouterevenementController = loader.getController();

        // Pass the main stage reference to the controller
        ajouterevenementController.setPrimaryStage(ajouterevenementStage); // Pass the current stage

        ajouterevenementStage.setScene(scene);
        ajouterevenementStage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

// Add this method to your controller
@FXML
public void supprimerLigne() {
    Evenement selectedEvent = id_tableviewevents.getSelectionModel().getSelectedItem();
    if (selectedEvent != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationDialog.fxml"));
            Parent root = loader.load();
            
            // Get the controller for the confirmation dialog
            ConfirmationDialogController dialogController = loader.getController();

            // Set the selected event or any other data you want to pass to the dialog
            dialogController.setSelectedEvent(selectedEvent);

            // Create a new stage for the confirmation dialog
            Stage confirmationDialogStage = new Stage();
            confirmationDialogStage.initModality(Modality.APPLICATION_MODAL);
            confirmationDialogStage.setScene(new Scene(root));
            
            // Show the confirmation dialog
            confirmationDialogStage.showAndWait();
            
            // Check the result from the dialog
            boolean confirmed = dialogController.isConfirmed();
            if (confirmed) {
                // User clicked "Yes," perform the deletion
                EvenementCrud evenementCrud = new EvenementCrud();
                evenementCrud.supprimerEvenement(selectedEvent);

                // Remove the item from the table view
                id_tableviewevents.getItems().remove(selectedEvent);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        
        loadMessageBox();
    }
}

 
  private void loadMessageBox() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageBox.fxml"));
        Parent root = loader.load();
        
        Stage messageBoxStage = new Stage();
        messageBoxStage.setScene(new Scene(root));
        
        messageBoxStage.initModality(Modality.APPLICATION_MODAL);
        messageBoxStage.initOwner(id_tableviewevents.getScene().getWindow());
        
        messageBoxStage.showAndWait();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
private void tableaudebordaction(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TableauDeBord.fxml"));
        Parent root = loader.load();

        // Create a new stage for the Tableau de Bord
        Stage tableauDeBordStage = new Stage();
        Scene scene = new Scene(root);

        // Set the scene for the new stage
        tableauDeBordStage.setScene(scene);

        // Show the new stage (Tableau de Bord) in a new window
        tableauDeBordStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
private void gestionsponsor(ActionEvent event) {
    
  // Close the initial table view
    Stage currentStage = (Stage) id_tableviewevents.getScene().getWindow();
    currentStage.close();

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutersponsor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage ajoutersponsorstage = new Stage();

        // Get the controller
        AjoutersponsorController ajoutersponsorController = loader.getController();

        // Pass the main stage reference to the controller
        ajoutersponsorController.setStage(ajoutersponsorstage); // Pass the current stage

        ajoutersponsorstage.setScene(scene);
        ajoutersponsorstage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
}





    @FXML
    private void logout(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("espaceAdmin.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

           System.out.println(ex.getMessage());

        }
                

    }
  
  
}







