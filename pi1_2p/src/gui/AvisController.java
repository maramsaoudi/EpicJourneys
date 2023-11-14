/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Avis;

import entities.User;
import entities.UserRole;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import services.AvisCRUD;
import services.IAvisCRUD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import gui.SignController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import services.UserCRUD;



/**
 * FXML Controller class
 *
 * @author ghofr
 */
public class AvisController implements Initializable {
   
    @FXML
    private TextField aauteur;
    @FXML
    private ComboBox<String> anote;
    @FXML
    private TextField acontenu;
    @FXML
    private TableView<Avis> atable;
    @FXML
    private TableColumn<Avis, String> com_auteur;
    @FXML
    private TableColumn<Avis, String> col_note;
    @FXML
    private TableColumn<Avis, String> col_contenu;
    @FXML
    private Button abtnajouter;
    @FXML
    private Button abtnsupprimer;
    @FXML
    private Button abtnmodifier;
private ObservableList<Avis> data = FXCollections.observableArrayList();

AvisCRUD serviceavis = new AvisCRUD();
    public String msgError = "Veuillez remplir tous les champs correctement !";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the ComboBox with gender options
        ObservableList<String> liste = FXCollections.observableArrayList("1", "2","3","4","5");
        anote.setItems(liste);

        

        affichage();

        atable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Avis>() {
            @Override
            public void changed(ObservableValue<? extends Avis> observable, Avis oldValue, Avis newValue) {
                if (newValue != null) {
                    
                    aauteur.setText(newValue.getAuteur());
                    anote.setValue(newValue.getNote());
                    acontenu.setText(newValue.getContenu());
                    
                    
                    
                    

                }
            }

        });

        abtnmodifier.setOnMouseClicked((t) -> {
            Avis selectedUser = atable.getSelectionModel().getSelectedItem();

            if (selectedUser != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Modifier l'utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Etes vous sur de vouloir modifier le contenu " + selectedUser.getContenu() + " ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                   /* UserRole role = null;
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

                    if (selectedRadioButton != null) {
                        String selectedValue = selectedRadioButton.getText();
                        if (selectedValue.toUpperCase().contains(UserRole.CLIENT.name())) {
                            role = UserRole.CLIENT;
                        } else {
                            role = UserRole.GUIDE;
                        }
                    }*/

                    String auteur = aauteur.getText();
                    String note = anote.getValue();
                    String contenu = acontenu.getText();
                    
                    
                   
                    if (!isValidAuteur(auteur)
                            
                             || !isValidNote(note)
                            || !isValidContenu(contenu))
                            
                             {

                        Alert alertz = new Alert(Alert.AlertType.ERROR);
                        alertz.initStyle(StageStyle.UTILITY);
                        alertz.setTitle("Error");
                        alertz.setHeaderText(null);
                        alertz.setContentText(msgError);
                        alertz.showAndWait();
                    } else {
                        Avis avis = new Avis();
                        avis.setIdAvis(selectedUser.getIdAvis());
                        avis.setAuteur(auteur);
                         avis.setNote(note);
                        avis.setContenu(contenu);
                        
                       
                        

                        if (serviceavis.modifierAvis(avis)) {
                            Alert alertd = new Alert(Alert.AlertType.INFORMATION);
                            alertd.initStyle(StageStyle.UTILITY);
                            alertd.setTitle("Success");
                            alertd.setHeaderText(null);
                            alertd.setContentText("Contenu a été modifié");
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
                            alertr.setContentText("Contenu n'a pas été modifié");
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
       // UserRole role = null;
        

      

        String auteur = aauteur.getText();
        String note = anote.getValue();
        String contenu = acontenu.getText();
        
        
        
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
            Avis avis = new Avis();

            avis.setAuteur(auteur);
           
            
            avis.setNote(note);
            avis.setContenu(contenu);

            if (serviceavis.ajouterAvis(avis)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Avis a été ajouté");
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
                alert.setContentText("Avis n'a pas été ajouté");
                alert.showAndWait();
            }

        }

    }

    @FXML
    private void handleSupprimerButtonAction() {
        // Get the selected user from the table
       Avis selectedAvis = atable.getSelectionModel().getSelectedItem();
        if (selectedAvis != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Supprimer l'avis");
            alert.setHeaderText(null);
            alert.setContentText("Etes vous sur de vouloir supprimer l'avis " + selectedAvis.getContenu() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
            {
                if (serviceavis.supprimerAvis(selectedAvis.getIdAvis())) {
                    Alert alerts = new Alert(Alert.AlertType.INFORMATION);
                    alerts.initStyle(StageStyle.UTILITY);
                    alerts.setTitle("Success");
                    alerts.setHeaderText(null);
                    alerts.setContentText("l'avis " + selectedAvis.getContenu() + " a été supprimée");
                    alerts.showAndWait();
                    clearInputFields();
                    affichage();
                } else {
                    Alert alertz = new Alert(Alert.AlertType.ERROR);
                    alertz.initStyle(StageStyle.UTILITY);
                    alertz.setTitle("Error");
                    alertz.setHeaderText(null);
                    alertz.setContentText("l'avis " + selectedAvis.getContenu() + "n'a pas été supprimée");
                    alertz.showAndWait();
                }
            }

        } else {
            Alert alertz = new Alert(Alert.AlertType.ERROR);
            alertz.initStyle(StageStyle.UTILITY);
            alertz.setTitle("Error");
            alertz.setHeaderText(null);
            alertz.setContentText("Selectionnez un avis !");
            alertz.showAndWait();
        }
    }

    private void clearInputFields() {
        aauteur.clear();
        acontenu.clear();
        
        anote.getSelectionModel().clearSelection();
       
    }

 /*   @FXML
    private void resetFilter(ActionEvent event) {
        affichage();
    }*/

    private void affichage() {
        data = FXCollections.observableArrayList(serviceavis.rechercherTousLesAvis());
        atable.setItems(data);
        com_auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
       
       
        col_note.setCellValueFactory(new PropertyValueFactory<>("note"));
        
    }

    public boolean isValidAuteur(String str) {
        if (str == null || str.isEmpty()) {
            msgError = "Auteur Invalide";
            return false;
        } else {
            return true;
        }

    }

   /* public boolean isSamePassword(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else {
            msgError = "Les mots de passe ne correspondent pas !";
            return false;
        }
    }*/

    public boolean isValidContenu(String str) {
        if (str == null || str.isEmpty()) {
           
            msgError = "Contenu Invalide";
            return false;
        } else {
            return true;
        }
    }

   /* public boolean isValidEmail(String email) {
      
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            msgError = "Email Invalide";
            return false;
        }
    }*/

  /*  public boolean isValidPhone(String input) {
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
    }*/

  /*  public boolean isStrongPassword(String password) {
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
    }*/

    private boolean isValidNote(String note) {
        if (note == null || note.isEmpty()) {
            msgError = "Note Invalide";
            return false;
        } else {
            return true;
        }
    }

  /*  private boolean isValidRole(UserRole role) {
        if (role == null) {
            msgError = "Role Invalide";
            return false;
        } else {
            return true;
        }
    }*/

  /*  private boolean isValidDate(LocalDate date) {
        if (date == null) {
            msgError = "BirthDate Invalide";
            return false;
        } else {
            return true;

        }
    }*/

  /*  @FXML
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
    }*/


    private void Logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            try {
                Parent root = loader.load();
                abtnajouter.getScene().setRoot(root);
            } catch (IOException e) {
            }
    }
}
