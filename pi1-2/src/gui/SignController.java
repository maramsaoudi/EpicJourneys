/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class SignController implements Initializable {

    @FXML
    private TextField femail;
    @FXML
    private PasswordField fpassword;
    @FXML
    private Label fsignup;
    public String msgError = "Veuillez remplir tous les champs correctement !";
    UserCRUD serviceuser = new UserCRUD();
    @FXML
    private Button fbtnlogin;
    @FXML
    private ImageView imgbig;
    @FXML
    private ImageView imgsmall;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vous pouvez ajouter des initialisations ou des actions ici si nécessaire

        fsignup.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            try {
                Parent root = loader.load();
                femail.getScene().setRoot(root);
            } catch (IOException e) {
            }
        });
        
        File fileLogo1 = new File("C:\\Users\\ghofr\\OneDrive\\Bureau\\PI\\WorkDone\\WorkDone\\pi1-2\\pi1-2\\src\\Resources\\epic1.png");
        Image logoI = new Image(fileLogo1.toURI().toString());
        imgsmall.setImage(logoI);
        
        
        File fileLogo2 = new File("C:\\Users\\ghofr\\OneDrive\\Bureau\\PI\\WorkDone\\WorkDone\\pi1-2\\pi1-2\\src\\Resources\\epic2.png");
        Image logo2 = new Image(fileLogo2.toURI().toString());
        imgbig.setImage(logo2);
    }

    @FXML

    private void login(ActionEvent event) throws IOException {
        // C'est le gestionnaire d'événements pour le bouton de connexion
        String email = femail.getText();
        String password = fpassword.getText();

        if (!isValidEmail(email) || !isValidPwd(password)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(msgError);
            alert.showAndWait();
        } else {
            if (serviceuser.isEmailAlreadyUsed(email)) {
                if (serviceuser.getUserByCredentials(email, password) != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TableView.fxml"));
                    try {
                        Parent root = loader.load();
                        femail.getScene().setRoot(root);
                    } catch (IOException e) {
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Email ou/& mot de passe incorrecte");
                    alert.showAndWait();
                }
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Pas de compte avec cet email");
                    alert.showAndWait();
            }

        }

    }

    public boolean isValidPwd(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "Mot de passe ne peut pas etre vide";
            return false;
        } else {
            return true;
        }

    }

    public boolean isValidEmail(String email) {
        // Regular expression for  email 

        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            msgError = "Email Invalide";
            return false;
        }

    }

}
