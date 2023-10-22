package gui;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import entities.UserRole;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.stage.StageStyle;
import services.UserCRUD;

/**
 * FXML Controller class
 *
 * @author ghofr
 */

public class RegistrationController implements Initializable {

    @FXML
    private TextField pnom;
    @FXML
    private TextField pprenom;
    @FXML
    private TextField pemail;
    @FXML
    private TextField pmotdepasse;
    @FXML
    private TextField pconfirmmotdepasse;
    @FXML
    private TextField pnumerotelephone;
    @FXML
    private ComboBox<String> pgenre;
    @FXML
    private RadioButton pclient;
    @FXML
    private RadioButton pguide;
    @FXML
    private Label pErrorMessage;
    @FXML
    private Button pbtnRegister;
    private UserCRUD userCRUD = new UserCRUD();  // Instantiate UserCRUD for database operations
    @FXML
    private DatePicker pdatenaissance;
    @FXML
    private Label btnBack;

    ToggleGroup toggleGroup = new ToggleGroup();

    public String msgError = "Veuillez remplir tous les champs correctement !";

    UserCRUD serviceuser = new UserCRUD();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> genres = FXCollections.observableArrayList("Homme", "Femme");
        pgenre.setItems(genres);

        btnBack.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            try {
                Parent root = loader.load();
                btnBack.getScene().setRoot(root);
            } catch (IOException e) {
            }
        });

        pclient.setToggleGroup(toggleGroup);
        pguide.setToggleGroup(toggleGroup);

    }

    @FXML
    private void registerUser(ActionEvent event) {
        UserRole role = null;
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            String selectedValue = selectedRadioButton.getText();
            if (selectedValue.toUpperCase().contains(UserRole.CLIENT.name())) {
                role = UserRole.CLIENT;
            } else {
                role = UserRole.GUIDE;
            }
        }

        String nom = pnom.getText();
        String prenom = pprenom.getText();
        String email = pemail.getText();
        String motDePasse = pmotdepasse.getText();
        String confirmMotDePasse = pconfirmmotdepasse.getText();
        String numeroTelephone = pnumerotelephone.getText();
        String genre = pgenre.getValue();
        LocalDate date = pdatenaissance.getValue();
        if (!isValidNom(nom)
                || !isValidPrenom(prenom)
                || !isValidEmail(email)
                || !isStrongPassword(motDePasse)
                || !isSamePassword(confirmMotDePasse, motDePasse)
                || !isValidPhone(numeroTelephone)
                || !isValidDate(date)
                || !isValidGenre(genre)
                || !isValidRole(role)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(msgError);
            alert.showAndWait();
        } else {
            User user = new User();

            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setGenre(genre);
            user.setMotDePasse(motDePasse);
            user.setDateNaissance(date.toString());
            user.setNumeroTelephone(numeroTelephone);
            user.setRole(role);

            if (serviceuser.ajouterUtilisateur(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur a été ajouté");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
                    try {
                        Parent root = loader.load();
                        btnBack.getScene().setRoot(root);
                    } catch (IOException e) {
                    }

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur n'a pas été ajouté");
                alert.showAndWait();
            }

        }

    }

    public boolean isValidNom(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "Nom Invalide";
            return false;
        } else {
            return true;
        }

    }

    public boolean isSamePassword(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else {
            msgError = "Les mots de passe ne correspondent pas !";
           return false;
        }
    }

    public boolean isValidPrenom(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "Prenom Invalide";
            return false;
        } else {
            return true;
        }

    }

    public boolean isValidEmail(String email) {
        // Regular expression for  email 
        if(serviceuser.isEmailAlreadyUsed(email)){
            msgError = "Email utilisé";
            return false ;
        }
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

    public boolean isValidPhone(String input) {
        if (input == null || input.length() != 8) {
            msgError = "Le telephone  doit contenir 8 chiffres";
            return false; //  el param  null or toulou not 8
        }

        char firstChar = input.charAt(0); //  awel noumrou

        //  '2', '5', or '9'
        if (firstChar != '2' && firstChar != '5' && firstChar != '9') {
            msgError = "Le telephone  doit commencer par 2 - 5 - 9";
            return false;
        } else {
            return true;
        }
    }

    public boolean isStrongPassword(String password) {
        if (password == null || password.isEmpty()) {
            msgError = "Le mot de passe doit etre alphanumeric et contenir au moins 8 caracteres";
            return false;
        }
        if (password.length() < 8) {
            msgError = "Le mot de passe doit etre alphanumeric et contenir au moins 8 caracteres";
            return false;
        }
        if (!Pattern.compile(".*\\d.*").matcher(password).matches()) {
            msgError = "Le mot de passe doit etre alphanumeric et contenir au moins 8 caracteres";

            return false;
        }
        if (!Pattern.compile(".*[a-zA-Z].*").matcher(password).matches()) {
            msgError = "Le mot de passe doit etre alphanumeric et contenir au moins 8 caracteres";

            return false;
        }

        return true;
    }

    private boolean isValidGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            msgError = "Genre Invalide";
            return false;
        } else {
            return true;
        }

    }

    private boolean isValidRole(UserRole role) {
        if (role == null) {
            msgError = "Role Invalide";
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidDate(LocalDate date) {
        if (date == null) {
            msgError = "BirthDate Invalide";
            return false;
        } else {
            return true;

        }
    }
}
