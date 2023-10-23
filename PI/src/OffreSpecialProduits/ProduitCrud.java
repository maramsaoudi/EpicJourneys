
package OffreSpecialProduits;

import OffreSpecialProduits.InterfaceCrud.InterfaceCRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi.myConnection;

public class ProduitCrud {
public class ProduitCRUD implements InterfaceCRUD<Produit> {

    @Override
    public void ajouterEntities(Produit produit) {
        Connection connection = myConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Produit (nomp, prixu, image) VALUES (?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,produit.getNomp());
            pst.setFloat(2, produit.getPrixu());
            pst.setString(3, produit.getImage());
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

    @Override
    public List<Produit> listeDesEntities() {
        List<Produit> produits = new ArrayList<>();
        Connection connection = myConnection.getInstance().getCnx();
        try {
            String requete = "SELECT  nomp, prixu, image FROM Produit";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setNomp(rs.getString("nomp"));
                produit.setPrixu(rs.getFloat("prixu"));
                produit.setImage(rs.getString("image"));
                produits.add(produit);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return produits;
    }

    @Override
    public void modifierEntities(Produit produit) {
        Connection connection = myConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Produit SET nomp = ?, prixu = ?, image = ? WHERE idp = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, produit.getNomp());
            pst.setFloat(2, produit.getPrixu());
            pst.setString(3, produit.getImage());
            pst.setInt(4, produit.getId());
            pst.executeUpdate();
            System.out.println("Produit modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Produit produit) {
        Connection connection = myConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Produit WHERE idp = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, produit.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
 

    
}
