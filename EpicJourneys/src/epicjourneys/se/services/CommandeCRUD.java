/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.services;

import epicjourneys.se.entities.Commande;
import epicjourneys.se.interfraces.InterfaceCRUD;
import epicjourneys.se.utils.MyConnection; 
import java.sql.*;
import java.util.*;

public class CommandeCRUD implements InterfaceCRUD<Commande> {

    /* public CommandeCRUD(Connection cnx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    private Connection connection;

    // Constructeur sans arguments
    public CommandeCRUD() {
        connection = MyConnection.getInstance().getCnx();
    }

    public CommandeCRUD(Connection cnx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void ajouterEntities(Commande commande) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Commande (nomp, client) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, commande.getNomp());
            pst.setString(2, commande.getClient());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                commande.setId(generatedKeys.getInt(1));
            }

            System.out.println("Commande ajoutée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> listeDesEntities() {
        List<Commande> commandes = new ArrayList<>();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, nomp, client FROM Commande";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setId(rs.getInt("id"));
                commande.setNomp(rs.getString("nomp"));
                commande.setClient(rs.getString("client"));
                commandes.add(commande);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }

    @Override
    public void modifierEntities(Commande commande) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Commande SET nomp = ?, client = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, commande.getNomp());
            pst.setString(2, commande.getClient());
            pst.setInt(3, commande.getId());
            pst.executeUpdate();
            System.out.println("Commande modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Commande commande) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Commande WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, commande.getId());
            pst.executeUpdate();
            System.out.println("Commande supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
