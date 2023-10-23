/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.app;
 
import static E.app.main.CURRENCY;
import E.entities.EVENNEMENT;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author saif chaalene
 */
public class ExempleEvennementController implements Initializable {

    @FXML
    private Label exetitre;
    @FXML
    private Button exeshowdetait;
    @FXML
    private CheckBox execheckevennement;
    @FXML
    private Label exeprix;
    @FXML
    private Label exeetat;
    @FXML
    private Label exduree;
        @FXML
    private ImageView eximage;


    private EVENNEMENT evv;
    
    
    
     private  Stage stage;
    private Scene scene;
    private Parent root;
    
    public ExempleEvennementController() {
   }
    public void setData(EVENNEMENT ev){





  
        
        
        



        this.evv=ev;
        exetitre.setText(ev.getTitre());
        exeprix.setText(CURRENCY + ev.getPrix() );
        Image myimage= new Image(getClass().getResourceAsStream(ev.getImage()));
        eximage.setImage(myimage);
        exduree.setText(ev.getDuree());
        exeetat.setText(ev.getVille());
        
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ShowDetaitEvennemenet(ActionEvent event) {
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailWindow.fxml"));
            Parent rooot = loader.load(); 
           // ReservationWindowController targetController = loader.getController();
        
            stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            
            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ExempleEvennementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
}
