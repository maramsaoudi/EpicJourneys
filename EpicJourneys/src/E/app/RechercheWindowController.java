/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.app;

import E.entities.Recherche;
import E.services.RechercheEvennementCrud;

import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//import org.controlsfx.control.Notifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author saif chaalene
 */

public class RechercheWindowController implements Initializable {

    public RechercheWindowController() {
    }

    @FXML
    private Button rchercher;
    @FXML
    private TextField rdestination;
    @FXML
    private ComboBox<String> rtype;
    @FXML
    private DatePicker rdate;
    @FXML
    private TextField rbudget;
        @FXML
    private ImageView zz;

    
    @FXML
    private Label rnotf;
    
    private  Stage stage;
    private Scene scene;
    private Parent root;
    
    
    
  
  
  
  
  
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String [] iteams={"sport","culturel","nature"}  ;
    rtype.getItems().addAll(iteams);
//String[] posw={"ghaza","palastine","hamas"};
//TextFields.bindAutoCompletion(rdestination,posw);


Image myimage= new Image(getClass().getResourceAsStream("maldive.jpg"));
zz.setImage(myimage);

// TODO
        
    }    

    @FXML
 
    private void saverecherche(ActionEvent event) throws IOException {
        String rrdestination = rdestination.getText();
        //  sharedModel.setTextData(rrdestination);
        try {
         
        
          //ReservationWindowController targetController = loader.getController();
         
            
            LocalDate rrdate =rdate.getValue();
            Date rrrdate = java.sql.Date.valueOf(rrdate);
            
            float rrbudget = Float.parseFloat(rbudget.getText());
           String rrtype=rtype.getValue();
            Recherche r=new Recherche(rrdestination,rrtype,rrbudget, (java.sql.Date) rrrdate);
            if(rrtype!= null){
            RechercheEvennementCrud rc = new RechercheEvennementCrud();
           rc.AjouterRechercheEvennement(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationWindow.fxml"));
            Parent rooot = loader.load(); 
           // ReservationWindowController targetController = loader.getController();
        
            stage= (Stage)((Node)event.getSource()).getScene().getWindow();
            
            scene = new Scene(rooot);
            stage.setScene(scene);
            stage.show();
            
            }
            else {
                
                
                rnotf.setText("you have to choose your type");
           
            }
                    
                    
                    } 
        catch (IOException ex) {
            Logger.getLogger(RechercheWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
  
    }

    public TextField getRdestination() {
        return rdestination;
    }

    public void setRdestination(TextField rdestination) {
        this.rdestination = rdestination;
    }

    public ComboBox<String> getRtype() {
        return rtype;
    }

    public void setRtype(ComboBox<String> rtype) {
        this.rtype = rtype;
    }
    
 
}
