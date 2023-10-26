/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.demande;
import edu.esprit.tools.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SYB
 */
public class demandeCRUD {
    
     Connection cnx;

    public demandeCRUD() {
        cnx = MyConnection.getInstance().getCnx();

    }

    public void ajouterDemande1() {
        try {
            String requete = "INSERT INTO demande ( destination, dateDepart, type)"
                    + " VALUES ('maldive', 12/12/2012, 'nature')  ";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("demande ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterDemande (demande D) {

        try {
            String requete2 = "INSERT INTO demande ( Id, destination, dateDepart, type, emailD)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, 12);
            pst.setString(2, D.getDestination());
           
            pst.setDate(3, D.getDateDepart());
            pst.setString(4, D.getType());
            pst.setString(5, D.getEmailD());
           
            pst.executeUpdate();
            System.out.println("votre demande est ajoutee");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
    
    
       public void ModifierDemande (int IdDemandeModifier ,demande d){
 
   String rqt = "UPDATE demande SET idDemande = ?,destination=?, dateDepart=?,type=? WHERE idDemande =?";
        try {
            
            PreparedStatement pr = cnx.prepareStatement(rqt);

            
            pr.setInt(1, IdDemandeModifier);
            pr.setString(2, d.getDestination());
            pr.setDate(3, d.getDateDepart());
            pr.setString(4, d.getType()); 
            pr.setInt(5, IdDemandeModifier);
            pr.executeUpdate();
            System.out.println("lignes modifiées ");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }

       
     public void SuprimerDemande(demande d ){
               try {
            String requete2 = "DELETE FROM demande WHERE idDemande =?";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1,d.getIdDemande());
            
            pst.executeUpdate();
            System.out.println("demande supprimé ! ");
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }


    public List<demande> afficherDemande() {
        List<demande> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * from demande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                demande D = new demande();
            D.setIdDemande(rs.getInt("idDemande"));
            D.setId(14);
            D.setDestination(rs.getString("destination"));
            D.setDateDepart(rs.getDate("dateDepart"));
            D.setType(rs.getString("type"));
            myList.add(D);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    
    public boolean isDemandeExists(String destination, Date dateDepart, String type) {
    try {
        String query = "SELECT COUNT(*) FROM demande WHERE destination = ? AND dateDepart = ? AND type = ?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setString(1, destination);
        pst.setDate(2, dateDepart);
        pst.setString(3, type);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0; // If count > 0, the demand already exists.
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return false; // If an error occurs, consider the demand does not exist.
}

   public List<demande> afficherDemande2() {
        List<demande> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * from demande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                demande D = new demande();
            D.setIdDemande(rs.getInt("idDemande"));
            D.setId(14);
            D.setDestination(rs.getString("destination"));
            D.setDateDepart(rs.getDate("dateDepart"));
            D.setType(rs.getString("type"));
            D.setEmailD(rs.getString("emailD"));
            myList.add(D);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    } 
    
    
}
