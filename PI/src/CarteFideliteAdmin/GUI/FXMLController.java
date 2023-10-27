/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarteFideliteAdmin.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pi.CarteFidelite;
import pi.CarteFideliteCrud; 
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import pi.CarteFidelite.EtatCarte;
import pi.CarteFidelite.NiveauCarte;
import pi.myConnection;

/**
 * FXML Controller class
 *
 * @author desig
 */
public class FXMLController implements Initializable {

    @FXML
    private TableView<CarteFidelite> fxTableCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdCarte;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxPtsFidelite;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateDebut;
    @FXML
    private TableColumn<CarteFidelite, Date> fxDateFin;
    @FXML
    private TableColumn<CarteFidelite, Integer> fxIdClient;
    @FXML
    private TableColumn<CarteFidelite, EtatCarte> fxEtatCarte;
    @FXML
    private TableColumn<CarteFidelite, NiveauCarte> fxNiveauCarte;
    ObservableList<CarteFidelite> listC; 
    int index = -1; 
    Connection conn = null; 
    ResultSet rs = null; 
    PreparedStatement st = null ; 
    CarteFideliteCrud cnx2 = new CarteFideliteCrud();
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
    fxIdCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("IdCarte"));
    fxPtsFidelite.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("PtsFidelite"));
    fxDateDebut.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("DateDebut"));
    fxDateFin.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("DateFin"));
    fxIdClient.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("IdClient"));
    fxEtatCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,EtatCarte>("EtatCarte"));
    fxNiveauCarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,NiveauCarte>("NiveauCarte"));
    listC=cnx2.listeDesEntites1(); 
    fxTableCarte.setItems(listC);
    }
}



