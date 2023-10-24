package gui;

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.UserCRUD;



/**
 * FXML Controller class
 *
 * @author ghofr
 */


import services.EMailSender;
public class MdpOubliéUserController implements Initializable {

    @FXML
    private TextField MVotreadresseemail;
    @FXML
    private Button Menvoyercode;
    @FXML
    private Label MLogin;
     UserCRUD user = new UserCRUD();
     private int generateVerificationCode() {
        
     Random random = new Random();
     return 100000 + random.nextInt(900000);
    }
   // int randomCode;
    public static int code;
    public static String EmailReset ; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MLogin.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sign.fxml"));
            try {
                Parent root = loader.load();
                MLogin.getScene().setRoot(root);
            } catch (IOException e) {
            }
        });
        
    }
  /*  public class EmailConfig {
    public static final String EMAIL = "YOUR EMAIL";
    public static final String PASSWORD = "YOUR EMAIL PASSWORD";
}*/

    @FXML
    private void envoyercode(ActionEvent event) {
        code = generateVerificationCode();
        Alert A = new Alert(Alert.AlertType.WARNING);
        UserCRUD user = new UserCRUD();

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            boolean verifMail = MVotreadresseemail.getText().matches(emailRegex);

        if (!MVotreadresseemail.getText().equals("") && verifMail) {
            if (user.ChercherMail(MVotreadresseemail.getText()) == 1) {
                EmailReset = MVotreadresseemail.getText();
                EMailSender.sendEmail("ghofranetayari61@gmail.com", "ugve iuxe xqth ffmx", MVotreadresseemail.getText(), "Verification code", "Votre code est : " + code);

                try {

                    Parent page1 = FXMLLoader.load(getClass().getResource("VotreCode.fxml"));

                    Scene scene = new Scene(page1);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);

                    stage.show();

                } catch (IOException ex) {

                    System.out.println(ex.getMessage());

                }

            } else {
                A.setContentText("pas de compte lié avec cette adresse ! ");
                A.show();
            }
        } else {
            A.setContentText("Veuillez saisir une adresse mail valide ! ");
            A.show();
        }
  
}
    }

 /*  @FXML
    private void verifeycode(ActionEvent event) {
       Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    if (Integer.valueOf(MEntercode.getText()) == randomCode) {
        // Créez une nouvelle instance de la classe Reset
        Reset rs = new Reset(MVotreadresseemail.getText());

        // Créez un nouveau stage pour la fenêtre Reset
        Stage resetStage = new Stage();
        
        // Appelez la méthode start de l'instance Reset
        rs.start(resetStage);

        // Cacher la fenêtre actuelle
        currentStage.hide();
    } else {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Code incorrect");
        alert.setHeaderText(null);
        alert.setContentText("Le code ne correspond pas.");
        alert.showAndWait();
    }

    } */

