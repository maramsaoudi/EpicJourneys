package gui;

import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    private Label fForgotpassword;
   
    
    UserCRUD user = new UserCRUD () ;
    private int u ;
    private List<User>luser = new ArrayList<>();
    
    
    
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fsignup.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            try {
                Parent root = loader.load();
                femail.getScene().setRoot(root);
            } catch (IOException e) {
            }
        });
        
        fForgotpassword.setOnMouseClicked((t) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MdpOubliéUser.fxml"));
            try {
                Parent root = loader.load();
                femail.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
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
        if (email.equals("user@gmail.com") && password.equals("123456789q")) {
            // L'utilisateur a saisi l'email et le mot de passe de l'administrateur
            // Rediriger vers la page d'administration (AdminPage)
            FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
            Parent adminRoot = adminLoader.load();
            femail.getScene().setRoot(adminRoot);
        } else {
            // L'utilisateur a saisi d'autres identifiants
            // Rediriger vers la page client (ClientPage) par défaut
            FXMLLoader clientLoader = new FXMLLoader(getClass().getResource("ClientPage.fxml"));
            Parent clientRoot = clientLoader.load();
            femail.getScene().setRoot(clientRoot);
        }
    }
    
   luser = user.rechercherTousLesUtilisateurs2(femail.getText()) ;
   u = luser.get(0).getId();
    }

    public boolean isValidPwd(String str) {
        return str != null && !str.isEmpty();
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
    
    
    
    
}
