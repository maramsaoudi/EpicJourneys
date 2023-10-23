/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialEvenment; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.myConnection;


public class EvenementCrud {

    Connection cnx2;
    public EvenementCrud() {
        cnx2= myConnection.getInstance().getCnx();
    }
    
    
    public void ajouterEvenement (){
        try {
            String requete = "INSERT INTO evenement (titre,description, date_depart,prix, catégorie, guide_id, destination, image)"+
                    " VALUES ('Cultural Tour','description...','2023-10-11',1500,'Culture',123,'France','musée.jpg')";
            
            
            
           Statement st =   cnx2.createStatement();
           st.executeUpdate(requete); //execute update QUE pour les req de maj (insert,update,delete),on utilise executeQuery (select)
            System.out.println("Evenement ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
    }
    
    public void ajouterEvenement (Evenement e){
        try {
            String requete2 = "INSERT INTO evenement (titre,description, date_depart, ,prix, catégorie, guide_id, destination, image)"+
                    " VALUES (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setDate(3,e.getDate_depart());
            pst.setFloat(5, e.getPrix());
            pst.setString(6, e.getTypeEvenement());
            pst.setInt(7, e.getGuide_id());
            pst.setString(8, e.getDestination());
            pst.setString(9, e.getImage());
            
            pst.executeUpdate();
            System.out.println("Evenement ajoutée ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
  
    }
    
    
   
        public void modifierEvenement (Evenement e){
        try {
                   String requete2 = "UPDATE evenement SET titre=?,description=?, date_depart=?,prix=?, catégorie=?, guide_id=?, destination=?, image=? where IdEvenement=?";
            
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setDate(3,e.getDate_depart());
      
            pst.setFloat(5, e.getPrix());
            pst.setString(6, e.getTypeEvenement());
            pst.setInt(7, e.getGuide_id());
            pst.setString(8, e.getDestination());
            pst.setString(9, e.getImage());
            pst.setInt(10, e.getIdEvenement()); 

            pst.executeUpdate();
            System.out.println("Evenement modifié ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
    }
    
    
    
        public void supprimerEvenement (Evenement e){
        try {
            String requete2 = "DELETE FROM evenement WHERE IdEvenement  =?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, e.getIdEvenement());
            pst.executeUpdate();
            System.out.println("Evenement supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }

    public List<Evenement> afficherEvenements () {
        
        
        List <Evenement> myList = new ArrayList<>();
        try {
            
            String requete3 = "Select * from evenement";
            Statement st=cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); //rs contient le retour de la req
             
       
            while (rs.next()){
            Evenement e = new Evenement();
            e.setIdEvenement(rs.getInt("IdEvenement"));
            e.setTitre(rs.getNString("titre"));
            e.setDescription(rs.getNString("description"));
            e.setDate_depart(rs.getDate("date_depart"));
         
            e.setPrix(rs.getFloat("prix"));
            e.setTypeEvenement(rs.getNString("catégorie"));
            e.setGuide_id(rs.getInt("guide_id"));
            e.setDestination(rs.getNString("destination"));
            e.setImage(rs.getNString("image"));
            
            
            myList.add(e); 

            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
}
