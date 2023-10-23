package gui;

import entities.User;
import entities.UserRole;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javax.swing.text.Document;
import services.UserCRUD;

//import com.itextpdf.text.Document;
/*import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;*/


/**
 * FXML Controller class
 *
 * @author ghofr
 */

public class TableViewController implements Initializable {

    @FXML
    private TableView<User> ttable;
    @FXML
    private Button tbtnmodifier;
    @FXML
    private Button tbtnsupprimer;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private TextField temail;
    @FXML
    private TextField tmotdepasse;
    @FXML
    private TextField tnumerodetelephone;
    @FXML
    private DatePicker tdatedenaissance;
    @FXML
    private ComboBox<String> tgenre;
    @FXML
    private RadioButton tclient;
    @FXML
    private RadioButton tguide;
    @FXML
    private Button tbtnajouter;

    ToggleGroup toggleGroup = new ToggleGroup();

    @FXML
    private TableColumn<User, String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_mdp;
    @FXML
    private TableColumn<User, String> col_phone;
    @FXML
    private TableColumn<User, String> col_date;
    @FXML
    private TableColumn<User, String> col_genre;
    @FXML
    private TableColumn<User, String> col_role;

    private ObservableList<User> data = FXCollections.observableArrayList();
    
    UserCRUD serviceuser = new UserCRUD();
    public String msgError = "Veuillez remplir tous les champs correctement !";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the ComboBox with gender options
        ObservableList<String> liste = FXCollections.observableArrayList("Homme", "Femme");
        tgenre.setItems(liste);

        // Associate the ToggleGroup with the radio buttons
        tclient.setToggleGroup(toggleGroup);
        tguide.setToggleGroup(toggleGroup);

        affichage();

        ttable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    tdatedenaissance.setValue(LocalDate.parse(newValue.getDateNaissance()));
                    tnom.setText(newValue.getNom());
                    tprenom.setText(newValue.getPrenom());
                    temail.setText(newValue.getEmail());
                    tnumerodetelephone.setText(newValue.getNumeroTelephone());
                    tmotdepasse.setText(newValue.getMotDePasse());
                    tgenre.setValue(newValue.getGenre());
                    if(newValue.getRole()==UserRole.CLIENT){
                        toggleGroup.selectToggle(tclient);
                    }else{
                        toggleGroup.selectToggle(tguide);
                    }
                    

                }
            }

        });

        tbtnmodifier.setOnMouseClicked((t) -> {
            User selectedUser = ttable.getSelectionModel().getSelectedItem();

            if (selectedUser != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Modifier l'utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Etes vous sur de vouloir modifier l'utilisateur " + selectedUser.getNom() + " ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
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

                    String nom = tnom.getText();
                    String prenom = tprenom.getText();
                    String email = temail.getText();
                    String motDePasse = tmotdepasse.getText();
                    String numeroTelephone = tnumerodetelephone.getText();
                    String genre = tgenre.getValue();
                    LocalDate date = tdatedenaissance.getValue();
                    if (!isValidNom(nom)
                            || !isValidPrenom(prenom)
                            || !isValidEmail(email)
                            || !isStrongPassword(motDePasse)
                            || !isValidPhone(numeroTelephone)
                            || !isValidDate(date)
                            || !isValidGenre(genre)
                            || !isValidRole(role)) {

                        Alert alertz = new Alert(Alert.AlertType.ERROR);
                        alertz.initStyle(StageStyle.UTILITY);
                        alertz.setTitle("Error");
                        alertz.setHeaderText(null);
                        alertz.setContentText(msgError);
                        alertz.showAndWait();
                    } else {
                        User user = new User();
                        user.setId(selectedUser.getId());
                        user.setNom(nom);
                        user.setPrenom(prenom);
                        user.setEmail(email);
                        user.setGenre(genre);
                        user.setMotDePasse(motDePasse);
                        user.setDateNaissance(date.toString());
                        user.setNumeroTelephone(numeroTelephone);
                        user.setRole(role);

                        if (serviceuser.modifierUtilisateur(user)) {
                            Alert alertd = new Alert(Alert.AlertType.INFORMATION);
                            alertd.initStyle(StageStyle.UTILITY);
                            alertd.setTitle("Success");
                            alertd.setHeaderText(null);
                            alertd.setContentText("Utilisateur a été modifié");
                            Optional<ButtonType> resultd = alertd.showAndWait();
                            if (resultd.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                            {
                                affichage();
                                clearInputFields();
                            }
                        } else {
                            Alert alertr = new Alert(Alert.AlertType.ERROR);
                            alertr.initStyle(StageStyle.UTILITY);
                            alertr.setTitle("Error");
                            alertr.setHeaderText(null);
                            alertr.setContentText("Utilisateur n'a pas été modifié");
                            alertr.showAndWait();
                        }

                    }

                }
            } else {
                Alert alertz = new Alert(Alert.AlertType.ERROR);
                alertz.initStyle(StageStyle.UTILITY);
                alertz.setTitle("Error");
                alertz.setHeaderText(null);
                alertz.setContentText("Selectionnez un utilisateur !");
                alertz.showAndWait();
            }

        });

    }

    @FXML
    private void handleAjouterButtonAction() {
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

        String nom = tnom.getText();
        String prenom = tprenom.getText();
        String email = temail.getText();
        String motDePasse = tmotdepasse.getText();
        String numeroTelephone = tnumerodetelephone.getText();
        String genre = tgenre.getValue();
        LocalDate date = tdatedenaissance.getValue();
        if (!isValidNom(nom)
                || !isValidPrenom(prenom)
                || !isValidEmail(email)
                || !isStrongPassword(motDePasse)
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
                    affichage();
                    clearInputFields();
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

    @FXML
    private void handleSupprimerButtonAction() {
        // Get the selected user from the table
        User selectedUser = ttable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Supprimer l'utilisateur");
            alert.setHeaderText(null);
            alert.setContentText("Etes vous sur de vouloir supprimer l'utilsiateur " + selectedUser.getNom() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
            {
                if (serviceuser.supprimerUtilisateur(selectedUser.getId())) {
                    Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                    alerts.initStyle(StageStyle.UTILITY);
                    alerts.setTitle("Success");
                    alerts.setHeaderText(null);
                    alerts.setContentText("l'utilisateur " + selectedUser.getNom() + " a été supprimée");
                    alerts.showAndWait();
                    clearInputFields();
                    affichage();
                } else {
                    Alert alertz = new Alert(Alert.AlertType.ERROR);
                    alertz.initStyle(StageStyle.UTILITY);
                    alertz.setTitle("Error");
                    alertz.setHeaderText(null);
                    alertz.setContentText("l'utilisateur " + selectedUser.getNom() + "n'a pas été supprimée");
                    alertz.showAndWait();
                }
            }

        } else {
            Alert alertz = new Alert(Alert.AlertType.ERROR);
            alertz.initStyle(StageStyle.UTILITY);
            alertz.setTitle("Error");
            alertz.setHeaderText(null);
            alertz.setContentText("Selectionnez un utilisateur !");
            alertz.showAndWait();
        }
    }

    private void clearInputFields() {
        tnom.clear();
        tprenom.clear();
        temail.clear();
        tmotdepasse.clear();
        tnumerodetelephone.clear();
        tdatedenaissance.getEditor().clear();
        tgenre.getSelectionModel().clearSelection();
        toggleGroup.selectToggle(null);
    }

    @FXML
    private void resetFilter(ActionEvent event) {
        affichage();
    }

    private void affichage() {
        data = FXCollections.observableArrayList(serviceuser.rechercherTousLesUtilisateurs());
        ttable.setItems(data);
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
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
            return false; // bsh nchouf ken el param  null or toulou not 8
        }
        char firstChar = input.charAt(0); // 
        // 
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

    @FXML
    private void AfficherClients(ActionEvent event) {
        data = FXCollections.observableArrayList(serviceuser.getUsersByRole(UserRole.CLIENT));
        ttable.setItems(data);
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    @FXML
    private void AfficherGuides(ActionEvent event) {
        data = FXCollections.observableArrayList(serviceuser.getUsersByRole(UserRole.GUIDE));
        ttable.setItems(data);
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_mdp.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


    @FXML
    private void Logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            try {
                Parent root = loader.load();
                tbtnajouter.getScene().setRoot(root);
            } catch (IOException e) {
            }
    }
}

 
    
