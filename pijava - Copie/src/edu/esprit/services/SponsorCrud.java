/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

/**
 *
 * @author mkanz
 */
import edu.esprit.entities.Sponsor;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SponsorCrud {
    Connection cnx;

    public SponsorCrud() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterSponsor(Sponsor s) {
        try {
            String requete = "INSERT INTO sponsor (NomSponsor, Secteurdactivite, adresseSponsor, numtelSponsor, emailSponsor) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, s.getNomSponsor());
            pst.setString(2, s.getSecteurdactivite());
            pst.setString(3, s.getAdresseSponsor());
            pst.setInt(4, s.getNumtelSponsor());
            pst.setString(5, s.getEmailSponsor());
            pst.executeUpdate();
            System.out.println("Sponsor ajouté avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierSponsor(Sponsor s) {
        try {
            String requete = "UPDATE sponsor SET NomSponsor=?, Secteurdactivite=?, adresseSponsor=?, numtelSponsor=?, emailSponsor=? WHERE IdSponsor=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, s.getNomSponsor());
            pst.setString(2, s.getSecteurdactivite());
            pst.setString(3, s.getAdresseSponsor());
            pst.setInt(4, s.getNumtelSponsor());
            pst.setString(5, s.getEmailSponsor());
            pst.setInt(6, s.getIdSponsor());
            pst.executeUpdate();
            System.out.println("Sponsor modifié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerSponsor(Sponsor s) {
        try {
            String requete = "DELETE FROM sponsor WHERE IdSponsor=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, s.getIdSponsor());
            pst.executeUpdate();
            System.out.println("Sponsor supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Sponsor> afficherSponsors() {
        List<Sponsor> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM sponsor";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Sponsor s = new Sponsor();
                s.setIdSponsor(rs.getInt("IdSponsor"));
                s.setNomSponsor(rs.getString("NomSponsor"));
                s.setSecteurdactivite(rs.getString("Secteurdactivite"));
                s.setAdresseSponsor(rs.getString("adresseSponsor"));
                s.setNumtelSponsor(rs.getInt("numtelSponsor"));
                s.setEmailSponsor(rs.getString("emailSponsor"));
                myList.add(s);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}

