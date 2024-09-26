/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.services;

import epicjourneys.se.entities.Panier;
import epicjourneys.se.interfraces.InterfaceCRUD;
import epicjourneys.se.utils.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierCRUD implements InterfaceCRUD<Panier> {

    @Override
    public void ajouterEntities(Panier panier) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Panier (nomp, client, Date_de_Commande, Adresse_de_Livraison, Frais_de_Livraison) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, panier.getNomp());
            pst.setString(2, panier.getClient());
            pst.setString(3, panier.getDate_de_Commande());
            pst.setString(4, panier.getAdresse_de_Livraison());
            pst.setString(5, panier.getFrais_de_Livraison());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                panier.setId(generatedKeys.getInt(1));
            }

            System.out.println("Panier ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Panier> listeDesEntities() {
        List<Panier> paniers = new ArrayList<>();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, nomp, client, Date_de_Commande, Adresse_de_Livraison, Frais_de_Livraison FROM Panier";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Panier panier = new Panier();
                panier.setId(rs.getInt("id"));
                panier.setNomp(rs.getString("nomp"));
                panier.setClient(rs.getString("client"));
                panier.setDate_de_Commande(rs.getString("Date_de_Commande"));
                panier.setAdresse_de_Livraison(rs.getString("Adresse_de_Livraison"));
                panier.setFrais_de_Livraison(rs.getString("Frais_de_Livraison"));
                paniers.add(panier);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return paniers;
    }

    @Override
    public void modifierEntities(Panier panier) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Panier SET nomp = ?, client = ?, Date_de_Commande = ?, Adresse_de_Livraison = ?, Frais_de_Livraison = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, panier.getNomp());
            pst.setString(2, panier.getClient());
            pst.setString(3, panier.getDate_de_Commande());
            pst.setString(4, panier.getAdresse_de_Livraison());
            pst.setString(5, panier.getFrais_de_Livraison());
            pst.setInt(6, panier.getId());
            pst.executeUpdate();
            System.out.println("Panier modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Panier panier) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Panier WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, panier.getId());
            pst.executeUpdate();
            System.out.println("Panier supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
