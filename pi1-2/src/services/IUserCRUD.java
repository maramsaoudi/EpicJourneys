/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.util.List;
import services.UserCRUD;
import Utils.MyConnection;
import entities.UserRole;
import javafx.collections.ObservableList;
/**
 *
 * @author ghofr
 */
public interface IUserCRUD <utilisateur> {
     public boolean ajouterUtilisateur(User t);
     public boolean modifierUtilisateur (User t);
     public User rechercherUtilisateurParId(int id);
     public boolean supprimerUtilisateur(int id); // Ajoutez cette méthode pour supprimer un utilisateur
     public List<User> rechercherTousLesUtilisateurs();
     public boolean isEmailAlreadyUsed(String email);
     public ObservableList<User> getUsersByRole(UserRole role);
     
     /* User recupererUtilisateur(int id);
    List<User> recupererTousLesUtilisateurs();
    void mettreAJourUtilisateur(User utilisateur);
    void supprimerUtilisateur(int id);*/
}
