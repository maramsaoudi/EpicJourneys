/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialEGUI;

import OffreSpecialEvenment.OffreSpecialEvenment;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi.CarteFidelite; 
import OffreSpecialEvenment.OffreSpecialEvenementCrud;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AjouterOffreSpecialController implements Initializable {
    @FXML
    private Button fxRetourInitial;
    
  private File selectedImageFile; 
   @FXML
    private Button fxImportAchraf; 
    @FXML
    private ImageView imageOffre;
    @FXML
    private TextField fxTitreOffre;
    @FXML
    private TextField fxPrixOffre;
    @FXML
    private TextField fxGuideIdOffre;
    @FXML
    private TextField fxDestinationOffre;
    
    @FXML
    private ComboBox<String> fxNiveauOffre;
    @FXML
    private DatePicker fxDateDepartOffre;
    @FXML
    private TextArea fxDescriptionOFfre;
    @FXML
    private TextField fxCattegorieOffre;
    @FXML
    private Button fxAjouterOffre;
    @FXML
    private Label fxAjouterOffrelabel;
   

@FXML
void retourToSceneInitiale(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIEVENMENTAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) fxRetourInitial.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}   
String imagePathOffre = null;
@FXML
private void fxImportAchraf(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(fxImportAchraf.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageOffre.setImage(image);
            imagePathOffre = selectedImageFile.getAbsolutePath();

        }
    }  
@FXML

private void AjouterOffreSpecialE(ActionEvent event) { 
    try {
        OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud();
        String titreOffre = fxTitreOffre.getText();
        String prixOffreText = fxPrixOffre.getText();
        String guideIdOffreText = fxGuideIdOffre.getText();
        String destinationOffre = fxDestinationOffre.getText();
        String niveauOffreValue = fxNiveauOffre.getValue();  
        if (titreOffre.isEmpty() || prixOffreText.isEmpty() || guideIdOffreText.isEmpty()
                || destinationOffre.isEmpty() || niveauOffreValue == null ) {
            fxAjouterOffrelabel.setText("Please fill in all fields.");
        } else {
            float prixOffre = Float.parseFloat(prixOffreText);
            int guideIdOffre = Integer.parseInt(guideIdOffreText);
            OffreSpecialEvenment.NiveauCarte niveauOffre = OffreSpecialEvenment.NiveauCarte.valueOf(niveauOffreValue);

            LocalDate localDate = fxDateDepartOffre.getValue();
            Date dateDepartOffre = java.sql.Date.valueOf(localDate);
            String descriptionOffre = fxDescriptionOFfre.getText();
            String cattegorieOffre = fxCattegorieOffre.getText();  

            // Check if the selected date is in the past
            LocalDate currentDate = LocalDate.now();
            if (localDate.isBefore(currentDate)) {
                fxAjouterOffrelabel.setText("Selected date is in the past.");
            } else {
                OffreSpecialEvenment evenment1 = new OffreSpecialEvenment(cattegorieOffre, dateDepartOffre, descriptionOffre, destinationOffre, guideIdOffre, imagePathOffre, prixOffre, titreOffre, niveauOffre);
                cnx2.ajouterOffreSpecialEvenment(evenment1);
                retourToSceneInitiale(event);
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("Invalid number format in prix or guide ID.");
        fxAjouterOffrelabel.setText("Invalid number format in prix or guide ID.");
    } catch (IllegalArgumentException e) {
        System.err.println("Invalid value for NiveauCarte.");
        fxAjouterOffrelabel.setText("Invalid value for NiveauCarte.");
    }
}




    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ObservableList<String> niveauOptions = FXCollections.observableArrayList("bronze", "silver", "gold");
         fxNiveauOffre.setItems(niveauOptions); 
         
         
         
         
         

        

        // TODO
    }    
    
}
