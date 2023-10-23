package epicjourneys.se.services;

import epicjourneys.se.entities.Panier;
import epicjourneys.se.entities.Produit;
import epicjourneys.se.interfraces.InterfaceCRUD;
import epicjourneys.se.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProduitCRUD implements InterfaceCRUD<Produit> {

    @Override
    public void ajouterEntities(Produit produit) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Produit (nom, prixUnitaire, stock, image) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, produit.getNom());
            pst.setString(2, produit.getPrixUnitaire());
            pst.setInt(3, produit.getStock());
            pst.setString(4, produit.getImage());

            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                produit.setId(generatedKeys.getInt(1));
            }

            System.out.println("Produit ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Produit> listeDesEntities1() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, nom, prixUnitaire, stock, image FROM Produit";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId(rs.getInt("id"));
                produit.setNom(rs.getString("nom"));
                produit.setPrixUnitaire(rs.getString("prixUnitaire"));
                produit.setStock(rs.getInt("stock"));
                produit.setImage(rs.getString("image"));
                myList.add(produit);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(Produit produit) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Produit SET nom = ?, prixUnitaire = ?, stock = ?, image = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, produit.getNom());
            pst.setString(2, produit.getPrixUnitaire());
            pst.setInt(3, produit.getStock());
            pst.setString(4, produit.getImage());
            pst.setInt(5, produit.getId());

            pst.executeUpdate();

            System.out.println("Produit modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Produit produit) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Produit WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, produit.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> listeDesEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
