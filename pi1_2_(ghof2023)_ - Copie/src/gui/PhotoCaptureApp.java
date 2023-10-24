/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;


/**
 *
 * @author ghofr
 */

 
public class PhotoCaptureApp extends Application {
    private int loginAttempts = 0;
    private final int maxAttempts = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Photo Capture App");
        
        TextField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label statusLabel = new Label("");
        
        loginButton.setOnAction(event -> {
            if (authenticateUser(passwordField.getText())) {
                statusLabel.setText("Login successful.");
            } else {
                statusLabel.setText("Login failed.");
                loginAttempts++;
                if (loginAttempts >= maxAttempts) {
                    capturePhoto();
                }
            }
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(new Label("Enter Password:"), passwordField, loginButton, statusLabel);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean authenticateUser(String password) {
        // Implement your authentication logic here
        // Return true if the password is correct
        return "correct_password".equals(password);
    }

    private void capturePhoto() {
        // Implement code to capture a photo of the user
        // You may need to use external libraries or APIs for this
        // After capturing the photo, you can save it to a file or database
        // Be sure to adjust the code for your specific requirements
    }
}

    

