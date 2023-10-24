/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys;

import epicjourneys.se.entities.User;
import epicjourneys.se.services.UserCRUD;
import java.util.List;

/*
import epicjourneys.se.entities.Commande;
import epicjourneys.se.entities.Panier;
import epicjourneys.se.entities.Produit;
import epicjourneys.se.services.CommandeCRUD;
import epicjourneys.se.services.PanierCRUD;
import epicjourneys.se.services.ProduitCRUD;
import epicjourneys.se.utils.MyConnection;
import javafx.collections.ObservableList;


public class Epicjourneys {


    public static void main(String[] args) {
        ProduitCRUD produitCRUD = new ProduitCRUD();

        int produitIdToUpdate = 2; 
        ObservableList<Produit> produits = produitCRUD.listeDesEntities1();
        Produit produitToUpdate = null;

        for (Produit produit : produits) {
            if (produit.getId() == produitIdToUpdate) {
                produitToUpdate = produit;
                break;
            }
        }

        if (produitToUpdate != null) {
            // Mettez à jour les attributs du produit
            produitToUpdate.setNom("Nouveau Nom");
           // produitToUpdate.setPrixUnitaire();
            produitToUpdate.setStock(50);
            produitToUpdate.setImage("newimage.jpg");

            // Effectuez la mise à jour en utilisant la classe ProduitCRUD
            produitCRUD.modifierEntities(produitToUpdate);
            System.out.println("Produit mis à jour avec succès : " + produitToUpdate.getNom());
        } else {
            System.out.println("Produit non trouvé avec l'ID : " + produitIdToUpdate);
        }
        


       
        
    }
}*/

      
public class Epicjourneys {

    public static void main(String[] args) {
        UserCRUD userCRUD = new UserCRUD();

        // Test de l'ajout d'un utilisateur
        User newUser = new User("Ayari", "Amira", "amira.ayari@esprit.tn", "password123", "123456789", "2000-08-30", "Femme", User.UserRole.CLIENT);
        userCRUD.ajouterUser(newUser);
        System.out.println("Utilisateur ajouté avec succès : " + newUser.getNom());

        // Test de la liste des utilisateurs
        List<User> utilisateurs = userCRUD.listeDesUtilisateurs();
        for (User utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }

        // Test de la modification d'un utilisateur existant
        if (!utilisateurs.isEmpty()) {
            User utilisateurAModifier = utilisateurs.get(0);
            utilisateurAModifier.setNom("Nouveau Nom");
            utilisateurAModifier.setPrenom("Nouveau Prénom");
            userCRUD.modifierUser(utilisateurAModifier);
            System.out.println("Utilisateur modifié avec succès : " + utilisateurAModifier.getNom());
        }

        // Test de la suppression d'un utilisateur existant
        /*if (utilisateurs.size() > 1) {
            User utilisateurASupprimer = utilisateurs.get(1);
            userCRUD.supprimerUser(utilisateurASupprimer);
            System.out.println("Utilisateur supprimé avec succès : " + utilisateurASupprimer.getNom());
        }*/
    }
}
