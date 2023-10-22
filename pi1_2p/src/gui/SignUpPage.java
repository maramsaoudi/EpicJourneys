/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author ghofr
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

        // Créer des champs de texte pour le nom, le prénom, l'e-mail, etc.
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Prénom");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Nom");
        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirmer le mot de passe");

        // Créer un DatePicker pour la date de naissance
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date de naissance");

        // Créer un choix de rôle (par exemple, Client ou Admin)
        ChoiceBox<String> roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll("Client", "Admin");
        roleChoiceBox.setValue("Client");

        // Créer un bouton "Register" pour l'enregistrement de l'utilisateur
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            // Code pour enregistrer l'utilisateur
        });

        // Organiser les éléments dans un VBox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            firstNameField, lastNameField, emailField, passwordField, confirmPasswordField,
            datePicker, roleChoiceBox, registerButton
        );
        Scene sceneSignUp = new Scene(layout, 700, 700);

        primaryStage.setScene(sceneSignUp);
        primaryStage.show();
    }
}