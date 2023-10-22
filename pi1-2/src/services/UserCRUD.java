/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import entities.User; // Assurez-vous d'importer la classe User depuis le package entites
import Utils.MyConnection; // Assurez-vous d'importer la classe MyConnection
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import entities.UserRole;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 *
 * @Override
 *
 * @author ghofr
 */
public class UserCRUD implements IUserCRUD<User> {

    private Connection cnx = MyConnection.getInstance().getCnx();

    public UserCRUD() {

    }

    public boolean ajouterUtilisateur(User t) {
        boolean res = false;
        try {
            String requete = "INSERT INTO user (nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre , role) VALUES (?, ?, ?, ?, ?, ?, ? , ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMotDePasse());
            pst.setString(5, t.getNumeroTelephone());
            pst.setString(6, t.getDateNaissance());
            pst.setString(7, t.getGenre());
            pst.setString(8, t.getRole().toString());
            pst.executeUpdate();
            res = true;
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;

    }

    public boolean modifierUtilisateur(User t) {
        boolean res = false;
        try {
            String requete = "UPDATE user SET nom=?, prenom=?, email=?, motDePasse=?, numeroTelephone=?, dateNaissance=?, genre=?, role=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMotDePasse());
            pst.setString(5, t.getNumeroTelephone());
            pst.setString(6, t.getDateNaissance());
            pst.setString(7, t.getGenre());
            pst.setString(8, t.getRole().toString());
            pst.setInt(9, t.getId()); // Assurez-vous d'avoir un ID valide pour l'utilisateur que vous voulez modifier
            pst.executeUpdate();
            res =true;
            System.out.println("Utilisateur modifié avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public User rechercherUtilisateurParId(int id) {
        try {
            String requete = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setMotDePasse(rs.getString("motDePasse"));
                utilisateur.setNumeroTelephone(rs.getString("numeroTelephone"));
                utilisateur.setDateNaissance(rs.getString("dateNaissance"));
                utilisateur.setGenre(rs.getString("genre"));
                utilisateur.setRole(UserRole.valueOf(rs.getString("role"))); // Récupérez le rôle
                return utilisateur;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null; // Retournez null si l'utilisateur n'a pas été trouvé
    }

    public boolean supprimerUtilisateur(int id) {
        boolean res= false;
        try {
            String requete = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
                res = true;
            } else {
                System.out.println("L'utilisateur avec l'ID " + id + " n'a pas été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public List<User> rechercherTousLesUtilisateurs() {
        List<User> utilisateurs = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user";
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            while (rs.next()) {
                User utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setMotDePasse(rs.getString("motDePasse"));
                utilisateur.setNumeroTelephone(rs.getString("numeroTelephone"));
                utilisateur.setDateNaissance(rs.getString("dateNaissance"));
                utilisateur.setGenre(rs.getString("genre"));
                utilisateur.setRole(UserRole.valueOf(rs.getString("role")));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    @Override
    public boolean isEmailAlreadyUsed(String email) {
        boolean isUsed = false;
        try {
            String requete = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isUsed = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isUsed;
    }

    public ObservableList<User> getUsersByRole(UserRole role) {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM user WHERE role = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, role.toString());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String motDePasse = rs.getString("motDePasse");
                String numeroTelephone = rs.getString("numeroTelephone");
                String dateNaissance = rs.getString("dateNaissance");
                String genre = rs.getString("genre");
                UserRole userRole = UserRole.valueOf(rs.getString("role"));

                User user = new User(nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre, userRole);
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return userList;
    }

    public User getUserByCredentials(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email = ? AND motDePasse = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String genre = rs.getString("genre");
                UserRole userRole = UserRole.valueOf(rs.getString("role"));

                user = new User(nom, prenom, email, password, null, null, genre, userRole);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return user;
    }
}
