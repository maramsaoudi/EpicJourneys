/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.app;

import E.entities.EVENNEMENT;
import E.entities.Recherche;

import E.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Region;


/**
 * FXML Controller class
 *
 * @author saif chaalene
 */
public class ReservationWindowController implements Initializable {
   
    Connection cnx2 ;
    public ReservationWindowController(){
        cnx2= MyConnection.getInstance().getCnx();
    
    }
@FXML
    private Label label;
    @FXML
    private Label rstitrepage;
    @FXML
    private HBox rvbox;
    @FXML
    private ScrollPane rscroll;
    @FXML
    private GridPane rspayer;
    
    
    
     
   
    
    
    
   SharedModel s = new SharedModel();
    List<Recherche> myListrr = new ArrayList<>();
    private List<EVENNEMENT> evennements=new ArrayList<>();
    
    public List<EVENNEMENT> getData(){
         List<EVENNEMENT> evennements= new ArrayList<>();
        EVENNEMENT evennement;
        
        
       String t =s.o().get(s.o().size()-1).getDestinationRechercher() ;
        String r =s.o().get(s.o().size()-1).getTypeRechercher() ;
        float bb =s.o().get(s.o().size()-1).getBudgetRechercher();
        int ll = (int) (bb);
        Date dd =s.o().get(s.o().size()-1).getDateDepartRecherche();
        
        
        
        
       //  System.out.println(t);
          try {
      
    

    String requete = "SELECT * FROM evenement WHERE  destination = ? AND TypeEvennement=? AND prix >= ? AND prix <= ? AND date_depart= ? ";
    PreparedStatement st = cnx2.prepareStatement(requete);
st.setString(1, t);
st.setString(2, r);
st.setInt(3, ll-50);
    st.setInt(4, ll+50);
    st.setDate(5, dd);
    ResultSet rs = st.executeQuery();
              
            while(rs.next()){
              
                        
                EVENNEMENT evennemen = new EVENNEMENT();
                 
               evennemen.setTitre(rs.getString(2));
            evennemen.setPrix(rs.getInt(5));
            evennemen.setImage(rs.getString(9));
            evennemen.setVille(rs.getString(8));
            evennemen.setDuree(rs.getString(10));
                evennements.add(evennemen);
         
              System.out.println(evennements);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return evennements;}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       
        evennements.addAll(getData());
        int column=0;
        int row=1;
       
        
        try {
         
            for(int i=0; i<evennements.size();i++){
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource( "ExempleEvennement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                ExempleEvennementController exempleEvennement =(ExempleEvennementController) fxmlLoader.getController();
                exempleEvennement.setData(evennements.get(i));
                
                if(column==3){
                    column=0;
                    row++;
                }
                rspayer.add(anchorPane,column++,row);
                //
                rspayer.setMinWidth(Region.USE_COMPUTED_SIZE);
                rspayer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                rspayer.setMaxWidth(Region.USE_PREF_SIZE);
                //
                rspayer.setMinHeight(Region.USE_COMPUTED_SIZE);
                rspayer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rspayer.setMaxHeight(Region.USE_PREF_SIZE);
                //
                GridPane.setMargin(anchorPane, new Insets( 10.0));
            } }
        
        catch (IOException ex) {
              ex.printStackTrace();
            }
                
            
        
            // TODO
        
        
        // TODO
    }    

    @FXML
    private void rpayer(ActionEvent event) {
   
    
    
    }
    
   
}
