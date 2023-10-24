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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author desig
 */
public class ModifferOffreController implements Initializable {

    @FXML
    private Button mofxRetourInitial;
    private File selectedImageFile; 
    @FXML
    private Button mofxImportAchraf; 
    @FXML
     ImageView moimageOffre;
    @FXML
     TextField mofxTitreOffre;
    @FXML
     TextField mofxPrixOffre;
    @FXML
     TextField mofxGuideIdOffre;
    @FXML
     TextField mofxDestinationOffre;
    @FXML
     ComboBox<String> mofxNiveauOffre;
    @FXML
     DatePicker mofxDateDepartOffre;
    @FXML
     TextArea mofxDescriptionOFfre;
    @FXML
     TextField mofxCattegorieOffre;
    @FXML
    private Button mofxAjouterOffre;

@FXML
void retourToSceneInitiale(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUIEVENMENTAdmin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) mofxRetourInitial.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}   
String imagePathOffre = null;
@FXML
private void mofxImportAchraf(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(mofxImportAchraf.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            moimageOffre.setImage(image);
            imagePathOffre = selectedImageFile.getAbsolutePath();

        }
    }  
@FXML
private void ModifierOffreSpecialle(ActionEvent event) { 
    
    try {OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud(); 
        String titreOffre = mofxTitreOffre.getText();
        float prixOffre = Float.parseFloat(mofxPrixOffre.getText());
        int guideIdOffre = Integer.parseInt(mofxGuideIdOffre.getText());
        String destinationOffre = mofxDestinationOffre.getText();
        OffreSpecialEvenment.NiveauCarte niveauOffre = OffreSpecialEvenment.NiveauCarte.valueOf(mofxNiveauOffre.getValue());
        LocalDate localDate = mofxDateDepartOffre.getValue();
        Date dateDepartOffre = java.sql.Date.valueOf(localDate);
        String descriptionOffre = mofxDescriptionOFfre.getText();
        String cattegorieOffre = mofxCattegorieOffre.getText(); 
        if (cattegorieOffre == null) 
        { 
            System.out.println("Crud 8alet"); 
            
        }
        else {

        
        OffreSpecialEvenment evenment1 = new OffreSpecialEvenment(cattegorieOffre, dateDepartOffre, descriptionOffre, destinationOffre, guideIdOffre, imagePathOffre, prixOffre, titreOffre, niveauOffre);
        
            
        cnx2.ajouterOffreSpecialEvenment(evenment1);  
        }
        
    } catch (NumberFormatException e) {
        System.err.println("Invalid number format in prix or guide ID.");
    } catch (IllegalArgumentException e) {
        System.err.println("Invalid value for NiveauCarte.");
    }
}



    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ObservableList<String> niveauOptions = FXCollections.observableArrayList("bronze", "silver", "gold");
         mofxNiveauOffre.setItems(niveauOptions); 
    }
}
         
