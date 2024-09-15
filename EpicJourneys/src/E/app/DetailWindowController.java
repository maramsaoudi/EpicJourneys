  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author saif chaalene
 */
public class DetailWindowController implements Initializable {

    @FXML
    private Label titreC;
    @FXML
    private Label typeG;
    @FXML
    private Label guideD;
    @FXML
    private TextArea decriptionE;
    @FXML
    private ImageView imageD;
    @FXML
    private TableView<?> tableviewC;
    @FXML
    private TableColumn<?, ?> tvC;
    @FXML
    private TableColumn<?, ?> tvCC;
    @FXML
    private TextField IDC;
    @FXML
    private TextArea contenuC;
    @FXML
    private Button ajouterC;
    @FXML
    private Button modifierC;
    @FXML
    private Button suprimerC;
    @FXML
    private Button translateC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
