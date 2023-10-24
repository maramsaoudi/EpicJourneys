/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.services;

/**
 *
 * @author Amira
 */

import epicjourneys.se.entities.User;
import epicjourneys.se.entities.User.UserRole;
import epicjourneys.se.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD {
    private Connection connection;

    public UserCRUD() {
        connection = MyConnection.getInstance().getCnx();
    }

    public void ajouterUser(User user) {
        try {
            String requete = "INSERT INTO user (nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getMotDePasse());
            pst.setString(5, user.getNumeroTelephone());
            pst.setString(6, user.getDateNaissance());
            pst.setString(7, user.getGenre());
            pst.setString(8, user.getRole().name());
            pst.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> listeDesUtilisateurs() {
        List<User> utilisateurs = new ArrayList<>();
        try {
            String requete = "SELECT id, nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre, role FROM user";
            PreparedStatement pst = connection.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User utilisateur = new User(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("motDePasse"),
                    rs.getString("numeroTelephone"),
                    rs.getString("dateNaissance"),
                    rs.getString("genre"),
                    UserRole.valueOf(rs.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    public void modifierUser(User user) {
        try {
            String requete = "UPDATE user SET nom = ?, prenom = ?, email = ?, motDePasse = ?, numeroTelephone = ?, dateNaissance = ?, genre = ?, role = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, user.getNom());
            pst.setString(2, user.getPrenom());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getMotDePasse());
            pst.setString(5, user.getNumeroTelephone());
            pst.setString(6, user.getDateNaissance());
            pst.setString(7, user.getGenre());
            pst.setString(8, user.getRole().name());
            pst.setInt(9, user.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerUser(User user) {
        try {
            String requete = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouterCommandeAUtilisateur(int userId, int commandeId) {
        try {
            String requete = "INSERT INTO UserCommande (idUser, idCommande) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, userId);
            pst.setInt(2, commandeId);
            pst.executeUpdate();
            System.out.println("Commande ajoutée à l'utilisateur avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerCommandeAUtilisateur(int userId, int commandeId) {
        try {
            String requete = "DELETE FROM UserCommande WHERE idUser = ? AND idCommande = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, userId);
            pst.setInt(2, commandeId);
            pst.executeUpdate();
            System.out.println("Commande supprimée de l'utilisateur avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User getUserById(int userId) {
    User user = null;
    // Utilisez une requête SQL pour récupérer l'utilisateur à partir de la table User
    String sql = "SELECT * FROM User WHERE id = ?";

    try {
           Connection connection = MyConnection.getInstance().getCnx();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Construisez un objet User à partir des données récupérées
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setNom(resultSet.getString("nom"));
            // Ajoutez d'autres attributs ici
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return user;
}
 //To change body of generated methods, choose Tools | Templates.
    
     public int getUserIdFromDatabase(String userName) {
        int userId = -1; // Valeur par défaut si l'utilisateur n'est pas trouvé

        // Utilisez une requête SQL pour récupérer l'ID de l'utilisateur à partir de la base de données
        String sql = "SELECT id FROM User WHERE nom = ?";
        
        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userId;
    }
}
