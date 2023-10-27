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
import pi.CarteFidelite;
import pi.myConnection;

/**
 *
 * @author mkanz
 */
public class OffreSpecialEvenementCrud {

    Connection cnx2;
    public OffreSpecialEvenementCrud() {
        cnx2= myConnection.getInstance().getCnx();
    }
    
    
    public void ajouterOffreSpecialEvenemnt(){
        try {
            String requete = "INSERT INTO evenement (titre,description, date_depart,prix, catégorie, guide_id, destination, image)"+
                    " VALUES ('Cultural Tour','description...','2023-10-11',1500,'Culture',123,'France','musée.jpg')";
            
            
            
           Statement st =   cnx2.createStatement();
           st.executeUpdate(requete); //execute update QUE pour les req de maj (insert,update,delete),on utilise executeQuery (select)
            System.out.println("Offre ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
    }
    
    public void ajouterOffreSpecialEvenment (OffreSpecialEvenment e){
        try {
            String requete2 = "INSERT INTO evenement (titre,description, date_depart, ,prix, catégorie, guide_id, destination, image,niveau)"+
                    " VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setDate(3,e.getDate_depart());
            pst.setFloat(5, e.getPrix());
            pst.setString(6, e.getTypeEvenement());
            pst.setInt(7, e.getGuide_id());
            pst.setString(8, e.getDestination());
            pst.setString(9, e.getImage()); 
            pst.setString(10,e.getNiveau().toString());            
            pst.executeUpdate();
            System.out.println("Offre Special ajoutée ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
  
    }
    
    
    
        public void modifierOffreSpecialEvenment (OffreSpecialEvenment e){
        try {
                   String requete2 = "UPDATE evenement SET titre=?,description=?, date_depart=?,prix=?, catégorie=?, guide_id=?, destination=?, image=?, niveau=? where IdEvenement=?";
            
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
            pst.setString(11, e.getNiveau().toString());

            pst.executeUpdate();
            System.out.println("OffreSpecial modifié ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
    }
    
    
    
        public void supprimerOffreSpecialEvenmet (OffreSpecialEvenment e){
        try {
            String requete2 = "DELETE FROM OffreSpecialEvenment WHERE IdEvenement  =?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, e.getIdEvenement());
            pst.executeUpdate();
            System.out.println("Offre Special supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<OffreSpecialEvenment> afficherOffreSpecial () {
        
        
        List <OffreSpecialEvenment> myList = new ArrayList<>();
        try {
            
                String requete3 = "SELECT * " +
                 "FROM OffreSpecial " +
                 "INNER JOIN Cartefidelite ON OffreSpecial.niveau = Cartefidelite.NiveauCarte " +
                 "WHERE Cartefidelite.IdUser = ?";


            PreparedStatement st=cnx2.prepareStatement(requete3); 
            
            ResultSet rs = st.executeQuery(requete3); //rs contient le retour de la req
             
       
            while (rs.next()){
            OffreSpecialEvenment e = new OffreSpecialEvenment();
            e.setIdEvenement(rs.getInt("IdEvenement"));
            e.setTitre(rs.getNString("titre"));
            e.setDescription(rs.getNString("description"));
            e.setDate_depart(rs.getDate("date_depart"));
         
            e.setPrix(rs.getFloat("prix"));
            e.setTypeEvenement(rs.getNString("catégorie"));
            e.setGuide_id(rs.getInt("guide_id"));
            e.setDestination(rs.getNString("destination"));
            e.setImage(rs.getNString("image"));  
            String niveauString = rs.getString(10); 
            //CarteFidelite.NiveauCarte niveau = CarteFidelite.NiveauCarte.valueOf(niveauString); 
            OffreSpecialEvenment.NiveauCarte niveau = OffreSpecialEvenment.NiveauCarte.valueOf(niveauString);
            e.setNiveau(niveau);
            myList.add(e); 
            //pour chaque itération : créer un evenement remplir l'objet "e" et l'ajoutere dans mylist 
            
            
         
          
            
            }
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    
    
}
