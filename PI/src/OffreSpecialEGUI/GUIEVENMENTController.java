package OffreSpecialEGUI;

import OffreSpecialEvenment.OffreSpecialEvenementCrud;
import OffreSpecialEvenment.OffreSpecialEvenment;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pi.CarteFidelite;


/**
 *
 * @author desig
 */
public class GUIEVENMENTController implements Initializable { 
    private OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();  
private FilteredList<OffreSpecialEvenment> filteredList;

    public int sd = 0;

    @FXML
    private Button fxNavCarte;
    @FXML
    private Button fxNavAjouter;
    @FXML
    private Button fxNavModifier;
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
    private TextField fxChercherOffre;
   
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
 
@FXML
private void navigateToModifier(ActionEvent event) {
  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifferOffre.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ModifferOffreController mo = loader.getController();

        int idOffreSpecialEvenment = getOffreSpecialClick();
        if (idOffreSpecialEvenment != -1) {
            OffreSpecialEvenment selectedEvent = OffreSpecialEvenementCrud.getEventById(idOffreSpecialEvenment);

            /*mo.moimageOffre.setImage(new Image(selectedEvent.getImage()));
            mo.mofxTitreOffre.setText(selectedEvent.getTitre());
            mo.mofxPrixOffre.setText(String.valueOf(selectedEvent.getPrix()));
            mo.mofxGuideIdOffre.setText(String.valueOf(selectedEvent.getGuide_id()));
            mo.mofxDestinationOffre.setText(selectedEvent.getDestination());
            mo.mofxNiveauOffre.setValue(selectedEvent.getNiveau().toString());
            mo.mofxDateDepartOffre.setValue(selectedEvent.getDate_depart().toLocalDate());
            mo.mofxDescriptionOFfre.setText(selectedEvent.getDescription());
            mo.mofxCattegorieOffre.setText(selectedEvent.getCatégorie());*/
        } else {
            fxlabelfhdslf.setText("Please Select a Card");
        }

        Stage stage = (Stage) fxNavModifier.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
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

@Override
    public void initialize(URL url, ResourceBundle rb) { 

       

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

        
            

        
   
    


 

