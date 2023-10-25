/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Avis;
import entities.User;
import entities.UserRole;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import services.AvisCRUD;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class AjoutAvisController implements Initializable {

    @FXML
    private TextField rauteur;
    @FXML
    private TextArea rcontenu;
   
    @FXML
    private Button rajouter;
    
      private AvisCRUD AvisCRUD = new AvisCRUD(); 
      public String msgError = "Veuillez remplir tous les champs correctement !";
      AvisCRUD serviceavis = new AvisCRUD();
    @FXML
    private ComboBox<String> rnote;
    @FXML
    private AnchorPane Btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> notes = FXCollections.observableArrayList("1", "2","3", "4","5");
        rnote.setItems(notes);
        
        
        
          Btnback.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            try {
                Parent root = loader.load();
                Btnback.getScene().setRoot(root);
            } catch (IOException e) {
            }
        });
          
          
      /*    File fileLogo1 = new File("C:\\Users\\ghofr\\OneDrive\\Bureau\\src\\Resources\\travel.jng");
        Image logoI = new Image(fileLogo1.toURI().toString());
        imgsmall.setImage(logoI);*/
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
       

       

        String auteur = rauteur.getText();
        String contenu = rcontenu.getText();
   
        String note = rnote.getValue();
       
        if (!isValidAuteur(auteur)
                || !isValidNote(note)
                || !isValidContenu(contenu))
                
               {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(msgError);
            alert.showAndWait();
        } else {
            Avis avis= new Avis();

            avis.setAuteur(auteur);
            avis.setNote(note);
            avis.setContenu(contenu);
           

            if (serviceavis.ajouterAvis(avis)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("avisr a été ajouté");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
                    try {
                        Parent root = loader.load();
                        Btnback.getScene().setRoot(root);
                    } catch (IOException e) {
                    }

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("avis n'a pas été ajouté");
                alert.showAndWait();
            }

        }

    }

    public boolean isValidAuteur(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "auteur Invalide";
            return false;
        } else {
            return true;
        }

    }

    
    

    public boolean isValidContenu(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "Contenu Invalide";
            return false;
        } else {
            return true;
        }

    }

    

   

       

       
    


    private boolean isValidNote(String note) {
        if (note == null || note.isEmpty()) {
            msgError = "Note Invalide";
            return false;
        } else {
            return true;
        }

    }

   

   
    
}
