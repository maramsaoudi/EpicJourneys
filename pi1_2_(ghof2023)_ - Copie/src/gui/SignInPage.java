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

public class SignInPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign In");

        // Créer des champs de texte pour l'e-mail et le mot de passe
        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");

        // Créer le bouton "Sign In"
        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(e -> {
            // Gestion de l'authentification ici
        });

        // Créer le bouton "Sign Up" pour passer à la page d'inscription
        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            // Code pour passer à la page d'inscription
            // Par exemple : primaryStage.setScene(sceneSignUp);
        });

        // Organiser les éléments dans un VBox
        VBox layout = new VBox(10);
        layout.getChildren().addAll(emailField, passwordField, signInButton, signUpButton);
        Scene sceneSignIn = new Scene(layout, 300, 200);

        primaryStage.setScene(sceneSignIn);
        primaryStage.show();
    }
}