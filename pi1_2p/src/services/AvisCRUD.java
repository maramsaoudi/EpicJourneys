/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Avis;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import Utils.MyConnection;
import entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ghofr
 */
public class AvisCRUD {
  private List<Avis> listeAvis;
private Connection cnx = MyConnection.getInstance().getCnx();
    public AvisCRUD() {
        listeAvis = new ArrayList<>();
    }

    
     public boolean ajouterAvis(Avis avis) {
        boolean res = false;
        try {
            String requete = "INSERT INTO avis (auteur, note, contenu) VALUES (?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setString(1, avis.getAuteur());
            pst.setString(2, avis.getNote());
            pst.setString(3, avis.getContenu());
            pst.executeUpdate();
            res = true;
            System.out.println("Avis ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;

    }
    
    public Avis rechercherAvisParId(int id) {
        try {
            String requete = "SELECT * FROM Avis WHERE idAvis = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Avis(rs.getInt("idAvis"), rs.getString("Auteur"), rs.getString("Note"), rs.getString("Contenu"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null; // Avis non trouvé
    }
 public List<Avis> rechercherTousLesAvis() {
        List<Avis> listeAvis = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Avis";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Avis avis = new Avis(rs.getInt("idAvis"), rs.getString("Auteur"), rs.getString("Note"), rs.getString("Contenu"));
                listeAvis.add(avis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return listeAvis;
    }
   public boolean modifierAvis(Avis avis) {
        boolean res = false;
        try {
            String requete = "UPDATE Avis SET auteur=?, note=?,contenu=?  WHERE idAvis=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, avis.getAuteur());
            pst.setString(2, avis.getNote());
            pst.setString(3, avis.getContenu());
            
            pst.setInt(4, avis.getIdAvis()); // Assurez-vous d'avoir un ID valide pour l'utilisateur que vous voulez modifier
            pst.executeUpdate();
            res =true;
            System.out.println("avis modifié avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

 
    
    
    
     public boolean supprimerAvis(int id) {
        boolean res= false;
        try {
            String requete = "DELETE FROM avis WHERE idAvis = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Avis supprimé avec succès !");
                res = true;
            } else {
                System.out.println("L'avis avec l'ID " + id + " n'a pas été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

   /* public boolean modifierAvis(Avis avis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
  

