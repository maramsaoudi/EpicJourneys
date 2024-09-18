/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import Entities.OffreSpecialEvenment;
import Entities.myConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mkanz
 */
public class OffreSpecialEvenementCrud {

    Connection cnx2;

    public OffreSpecialEvenementCrud() {
        cnx2 = myConnection.getInstance().getCnx();
    }

    public void ajouterOffreSpecialEvenemnt() {
        try {
            String requete = "INSERT INTO evenement (titre,description, date_depart,prix, catégorie, guide_id, destination, image)"
                    +
                    " VALUES ('Cultural Tour','description...','2023-10-11',1500,'Culture',123,'France','musée.jpg')";

            Statement st = cnx2.createStatement();
            st.executeUpdate(requete); // execute update QUE pour les req de maj (insert,update,delete),on utilise
                                       // executeQuery (select)
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterOffreSpecialEvenment(OffreSpecialEvenment e) {
        try {
            String requete2 = "INSERT INTO offrespecialevenment (titre,description, date_depart, prix, categorie, guide_id, destination, image,niveau)"
                    +
                    " VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setDate(3, e.getDate_depart());
            pst.setFloat(4, e.getPrix());
            pst.setString(5, e.getTypeEvenement());
            pst.setInt(6, e.getGuide_id());
            pst.setString(7, e.getDestination());
            pst.setString(8, e.getImage());
            pst.setString(9, e.getNiveau());
            pst.executeUpdate();
            System.out.println("Offre Special ajoutée ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public ObservableList<OffreSpecialEvenment> afficherEvenements() {
        ObservableList<OffreSpecialEvenment> myList = FXCollections.observableArrayList();
        String requete3 = "Select * from offrespecialevenment";

        try (ResultSet rs = cnx2.prepareStatement(requete3).executeQuery()) {

            while (rs.next()) {
                OffreSpecialEvenment e = new OffreSpecialEvenment();
                e.setIdEvenement(rs.getInt("IdOffreSpecialEvenment"));
                e.setTitre(rs.getNString("titre"));
                e.setDescription(rs.getNString("description"));
                e.setDate_depart(rs.getDate("date_depart"));
                e.setPrix(rs.getFloat("prix"));
                e.setTypeEvenement(rs.getNString("categorie"));
                e.setGuide_id(rs.getInt("guide_id"));
                e.setDestination(rs.getNString("destination"));
                e.setImage(rs.getNString("image"));
                e.setNiveau(rs.getNString("niveau"));

                myList.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public void modifierOffreSpecialEvenment(OffreSpecialEvenment e) {
        String requete2 = "UPDATE offrespecialevenment SET titre=?, description=?, date_depart=?, prix=?, categorie=?, guide_id=?, destination=?, image=?, niveau=? WHERE IdOffreSpecialEvenment=?";

        try (PreparedStatement pst = cnx2.prepareStatement(requete2)) {
            pst.setString(1, e.getTitre());
            pst.setString(2, e.getDescription());
            pst.setDate(3, e.getDate_depart());
            pst.setFloat(4, e.getPrix());
            pst.setString(5, e.getTypeEvenement());
            pst.setInt(6, e.getGuide_id());
            pst.setString(7, e.getDestination());
            pst.setString(8, e.getImage());
            pst.setString(9, e.getNiveau().toString());
            pst.setInt(10, e.getIdEvenement());

            pst.executeUpdate();
            System.out.println("OffreSpecial modifié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerOffreSpecialEvenmet(Integer id) {
        try {
            String requete2 = "DELETE FROM OffreSpecialEvenment WHERE IdOffreSpecialEvenment" + //
                    "=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Offre Special supprimé ! ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<OffreSpecialEvenment> afficherOffreSpecial() {

        List<OffreSpecialEvenment> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * " +
                    "FROM OffreSpecial " +
                    "INNER JOIN Cartefidelite ON OffreSpecial.niveau = Cartefidelite.NiveauCarte " +
                    "WHERE Cartefidelite.IdUser = ?";

            PreparedStatement st = cnx2.prepareStatement(requete3);

            ResultSet rs = st.executeQuery(requete3); // rs contient le retour de la req

            while (rs.next()) {
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

                // CarteFidelite.NiveauCarte niveau =
                // CarteFidelite.NiveauCarte.valueOf(niveauString);
                e.setNiveau(rs.getString(10));
                myList.add(e);
                // pour chaque itération : créer un evenement remplir l'objet "e" et l'ajoutere
                // dans mylist

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public OffreSpecialEvenment getEventById(int id) {
        OffreSpecialEvenment event = null;
        String requete = "SELECT * FROM OffreSpecialEvenment WHERE IdOffreSpecialEvenment = ?";
        try {
            PreparedStatement ps = cnx2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Assuming NiveauCarte is an enum or a class you retrieve from the result set

                // Create the OffreSpecialEvenment object
                event = new OffreSpecialEvenment(rs.getString("niveau"));

                // Set other attributes of OffreSpecialEvenment based on Evenement attributes
                event.setDate_depart(rs.getDate("date_depart"));
                event.setDescription(rs.getString("description"));
                event.setDestination(rs.getString("destination"));
                event.setGuide_id(rs.getInt("guide_id"));
                event.setImage(rs.getString("image"));
                event.setPrix(rs.getFloat("prix"));
                event.setTitre(rs.getString("titre"));

                // Add any additional attributes specific to OffreSpecialEvenment if needed
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return event;
    }

}
