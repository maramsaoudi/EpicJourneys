/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialEGUI;

import OffreSpecialEvenment.OffreSpecialEvenementCrud;
import OffreSpecialEvenment.OffreSpecialEvenment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GUIEVENMENTController implements Initializable { 
    private OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud(); 

    @FXML
    private Button fxNavCarte;
    @FXML
    private Button fxNavAjouter;
    @FXML
    private Button fxNavModifier;
    @FXML
    private Button fxSupprimerOffreSpecial;
@FXML
private TableColumn<OffreSpecialEvenment, String> fxTitreOffreSpecialE;
@FXML
private TableColumn<OffreSpecialEvenment, String> fxDescriptionOfrreSpecialE;
@FXML
private TableColumn<OffreSpecialEvenment, Date> fxDateDepartOffre;
@FXML
private TableColumn<OffreSpecialEvenment, Float> fxPrix;
@FXML
private TableColumn<OffreSpecialEvenment, String> fxCatOffreE;
@FXML
private TableColumn<OffreSpecialEvenment, Integer> fxGuideIdE;
@FXML
private TableColumn<OffreSpecialEvenment, String> fxDestinationOffreE;
@FXML
private TableColumn<OffreSpecialEvenment, String> fxImageOffreE;
@FXML
private TableColumn<OffreSpecialEvenment, OffreSpecialEvenment.NiveauCarte> fxNiveauE;
@FXML
private TableView<OffreSpecialEvenment> fxTableOffreSpecial;
    @FXML
    private Label fxlabelfhdslf;
   
    @FXML
    void navigateToAjouter(ActionEvent event) {
        
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOffreSpecial.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) fxNavAjouter.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    private int index; 
    private int getSelectedOffreSpecialEvenment() {
    int idOffreSpecialEvenment = -1; 
    index = fxTableOffreSpecial.getSelectionModel().getSelectedIndex();
    if (index >= 0) {
        OffreSpecialEvenment selectedOffre = fxTableOffreSpecial.getItems().get(index);
        idOffreSpecialEvenment = selectedOffre.getIdEvenement();
    } else {
        // Handle the case when nothing is selected
        // You can set a label or show a message to prompt the user to select an item.
        fxlabelfhdslf.setText("Please select an Offre Special");
    }
    return idOffreSpecialEvenment;
}




    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        ObservableList<OffreSpecialEvenment> listOffres;

fxTitreOffreSpecialE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("titre"));
fxDescriptionOfrreSpecialE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("description"));
fxDateDepartOffre.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Date>("date_depart"));
fxPrix.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Float>("prix"));
fxCatOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("catégorie"));
fxGuideIdE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, Integer>("guide_id"));
fxDestinationOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("destination"));
fxImageOffreE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, String>("image"));
fxNiveauE.setCellValueFactory(new PropertyValueFactory<OffreSpecialEvenment, OffreSpecialEvenment.NiveauCarte>("niveau"));

listOffres = cnx2.listeDesOffres(); 
fxTableOffreSpecial.setItems(listOffres);

        
            

        
    }    
    
}

 

