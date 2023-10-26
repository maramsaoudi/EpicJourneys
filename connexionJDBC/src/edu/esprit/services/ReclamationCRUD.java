/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import java.sql.Date;
import edu.esprit.entities.reclamation;
import edu.esprit.tools.MyConnection;
import java.sql.Connection;
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
 * @author SYB
 */
public class ReclamationCRUD {

    
    Connection cnx2;

    public ReclamationCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    public void ajouterReclamation() {
        try {
            String requete = "INSERT INTO reclamation (Id, textReclamation, cibleReclamation, EtatReclamation, dateReclamation)"
                    + " VALUES (13,  'ehi', 'wink', 'en Cours', 12/12/2012)  ";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("reclamation ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterReclamation2(reclamation R) {

        try {
            String requete2 = "INSERT INTO reclamation (Id, textReclamation, cibleReclamation, EtatReclamation, dateReclamation)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, R.getId());
            pst.setString(2, R.getTextReclamation());
            pst.setString(3, R.getCibleReclamation());
            pst.setString(4, R.getEtatReclamation());
            pst.setDate(5, R.getDateReclamation());
            pst.executeUpdate();
            System.out.println("votre reclamation est ajoutee");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
    
    
       public void ModifierReclamation (int IdReclamationModifier ,reclamation r){
 
   String rqt = "UPDATE reclamation SET idReclamation = ?,Id=?,textReclamation=?,cibleReclamation=?,EtatReclamation=?, dateReclamation=? WHERE idReclamation =? ";
        try {
            
            PreparedStatement pr = cnx2.prepareStatement(rqt);

            
            pr.setInt(1, IdReclamationModifier);
            pr.setInt(2, r.getId());
            pr.setString(3, r.getTextReclamation());
            pr.setString(4, r.getCibleReclamation());
            pr.setString(5, r.getEtatReclamation());
            pr.setDate(6, r.getDateReclamation());
            pr.setInt(7, IdReclamationModifier);
            pr.executeUpdate();
            System.out.println("lignes modifiées ");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
       
       
       
       
              public void ModifierReclamation2 (reclamation r){
 
   String rqt = "UPDATE reclamation SET EtatReclamation=? WHERE idReclamation =? ";
        try {
            
            PreparedStatement pr = cnx2.prepareStatement(rqt);

            
            pr.setString(1, "résolue");
            pr.setInt(2, r.getIdReclamation());
            

            pr.executeUpdate();
            System.out.println("lignes modifiées ");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }

       
     public void SuprimerReclamation(reclamation r ){
               try {
            String requete2 = "DELETE FROM reclamation WHERE idReclamation =?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,r.getIdReclamation());
            
            pst.executeUpdate();
            System.out.println("reclamation supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }


    public List<reclamation> afficherReclamations() {
        List<reclamation> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * from reclamation";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                reclamation R = new reclamation();
                R.setIdReclamation(rs.getInt("idReclamation"));
                R.setId(14);
                R.setTextReclamation(rs.getString("textReclamation"));
                R.setCibleReclamation(rs.getString("cibleReclamation"));
                
                R.setDateReclamation(rs.getDate("dateReclamation"));
                R.setEtatReclamation(rs.getString(5));
                myList.add(R);

                            
            }
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    
    
    public int countReclamationsByClient(int clientId) {
    try {
        String query = "SELECT COUNT(*) FROM reclamation WHERE Id = ?";
        PreparedStatement pst = cnx2.prepareStatement(query);
        pst.setInt(1, clientId);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    
    return 0; 
}

    
    public List<reclamation> searchReclamationsByDate(Date searchDate) {
    List<reclamation> myList = new ArrayList<>();
    try {
        String query = "SELECT * FROM reclamation WHERE dateReclamation = ?";
        PreparedStatement pst = cnx2.prepareStatement(query);
        pst.setDate(1, searchDate);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            reclamation R = new reclamation();
            R.setIdReclamation(rs.getInt("idReclamation"));
            R.setId(rs.getInt("Id"));
            R.setTextReclamation(rs.getString("textReclamation"));
            R.setCibleReclamation(rs.getString("cibleReclamation"));
            R.setDateReclamation(rs.getDate("dateReclamation"));
            R.setEtatReclamation(rs.getString("EtatReclamation"));
            myList.add(R);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
}

}
