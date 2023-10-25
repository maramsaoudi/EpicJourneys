/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Evenement;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkanz
 */
public class EvenementCrud {

    Connection cnx2;
    public EvenementCrud() {
        cnx2= MyConnection.getInstance().getCnx();
    }
    
    
    public void ajouterEvenement (){
        try {
            String requete = "INSERT INTO evenements (titre,description, date_depart,prix, TypeEvenement, guide_id, destination, image)"+
                    " VALUES ('Cultural Tour','description...','2023-10-11',1500,'Culture',123,'France','musée.jpg')";
            
            
            
           Statement st =   cnx2.createStatement();
           st.executeUpdate(requete); //execute update QUE pour les req de maj (insert,update,delete),on utilise executeQuery (select)
            System.out.println("Evenement ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
    }
    
   public void ajouterEvenement(Evenement e) {
    try {
        String requete2 = "INSERT INTO evenements (titre, description, date_depart, prix, guide_id, TypeEvenement, destination, image, SponsorEvenement)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = cnx2.prepareStatement(requete2);
        pst.setString(1, e.getTitre());
        pst.setString(2, e.getDescription());
 // Convert java.util.Date to java.sql.Date
        Date dateDepart = new Date(e.getDate_depart().getTime());
        pst.setDate(3, dateDepart);        pst.setFloat(4, e.getPrix());
        pst.setInt(5, e.getGuide_id());  // Use setInt for an integer field
        pst.setString(6, e.getTypeEvenement());
        pst.setString(7, e.getDestination());
        pst.setString(8, e.getImage());
        pst.setString(9, e.getSponsorEvenement());

        pst.executeUpdate();
        System.out.println("Evenement ajoutée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    
      public void modifierEvenement(Evenement e) {
    try {
        String requete2 = "UPDATE evenements SET description=?, date_depart=?, SponsorEvenement=?, prix=?, TypeEvenement=?, guide_id=?, destination=?, image=? WHERE titre=?";


        PreparedStatement pst = cnx2.prepareStatement(requete2);
        pst.setString(1, e.getDescription());
        pst.setDate(2, new java.sql.Date(e.getDate_depart().getTime()));
        pst.setString(3, e.getSponsorEvenement());
        pst.setFloat(4, e.getPrix());
        pst.setString(5, e.getTypeEvenement());
        pst.setInt(6, e.getGuide_id());
        pst.setString(7, e.getDestination());
        pst.setString(8, e.getImage());
        pst.setString(9, e.getTitre());

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Evenement modifié ! " + rowsUpdated + " record(s) updated.");
        } else {
            System.out.println("No records were updated.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    
        public void supprimerEvenement (Evenement e){
        try {
            String requete2 = "DELETE FROM evenements WHERE titre=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.executeUpdate();
            System.out.println("Evenement supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Evenement> afficherEvenements () {
        
        
        List <Evenement> myList = new ArrayList<>();
        try {
            
            String requete3 = "Select * from evenements";
            Statement st=cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3); //rs contient le retour de la req
             
       
            while (rs.next()){
            Evenement e = new Evenement();
            e.setIdEvenement(rs.getInt("IdEvenement"));
            e.setTitre(rs.getString("titre"));
            e.setDescription(rs.getString("description"));
            e.setDate_depart(rs.getDate("date_depart"));
         
            e.setPrix(rs.getFloat("prix"));
            e.setTypeEvenement(rs.getString("TypeEvenement"));
            e.setGuide_id(rs.getInt("guide_id"));
            e.setDestination(rs.getString("destination"));
            e.setImage(rs.getString("image"));
                        e.setSponsorEvenement(rs.getString("SponsorEvenement")); 
            
            myList.add(e); 
            //pour chaque itération : créer un evenement remplir l'objet "e" et l'ajouter dans mylist 
            System.out.println(e.toString());
            
         
          
            
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
}
