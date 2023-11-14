/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import services.UserCRUD;
import entities.User;

import entities.UserRole;
import java.util.List;


/**
 *
 * @author ghofr
 */
public class MainClass {
    private static User utilisateur ;
    
  public static void main(String[] args)  {
        utilisateur = new User() ;
        UserCRUD UserCRUD = new UserCRUD();
        
        

        // Créer un utilisateur à ajouter
       
  /*    User utilisateur = new User();
        utilisateur.setNom("userrr1");
        utilisateur.setPrenom("user1");
        utilisateur.setEmail("user@gmail.com");
        utilisateur.setMotDePasse("789541365kk");
        utilisateur.setNumeroTelephone("21569874");
        utilisateur.setDateNaissance("2001-05-11");
        utilisateur.setGenre("Femme");
         utilisateur.setRole(UserRole.CLIENT);
         
        System.out.println("Utilisateur créé avec succès !");
        UserCRUD.ajouterUtilisateur(utilisateur);   
       
        /************************/
       
      /*  Evenement evenement1 = new Evenement("Événement 1");
        Evenement evenement2 = new Evenement("Événement 2");
       // Evenement evenement3 = new Evenement("Événement 3");
        utilisateur.reserverEvenement(evenement1);
        utilisateur.reserverEvenement(evenement2);
        //utilisateur.reserverEvenement(evenement3);

        Evenement evenement3 = new Evenement("Événement 3");
        //utilisateur.reserverEvenement(evenement3);

        // Vérifiez si un code promo a été généré après la réservation du troisième événement
        if (utilisateur.getCodePromo() != null) {
            System.out.println("Le code promo a été généré automatiquement : " + utilisateur.getCodePromo().getCode());
        } else {
            System.out.println("Le code promo n'a pas été généré automatiquement.");
        }

        Evenement evenement4 = new Evenement("Événement 4");
        utilisateur.reserverEvenement(evenement4);

        // Vérifiez à nouveau si un code promo a été généré après la réservation du quatrième événement
        if (utilisateur.getCodePromo() != null) {
            System.out.println("Le code promo a été généré automatiquement : " + utilisateur.getCodePromo().getCode());
        } else {
            System.out.println("Le code promo n'a pas été généré automatiquement.");
        }
    }*/

 
    
   /*   utilisateur.setId(24); //   l'ID a modifier
    utilisateur.setNom("NouveauNom");
    utilisateur.setPrenom("NouveauPrenom");
    utilisateur.setEmail("nouveau@email.com");
    utilisateur.setMotDePasse("NouveauMotDePasse");
    utilisateur.setNumeroTelephone("NouveauNumero");
    utilisateur.setDateNaissance("NouvelleDate");
    utilisateur.setGenre("NouveauGenre");
    utilisateur.setRole(UserRole.CLIENT); 
    // Appelez la méthode de modification
    UserCRUD.modifierUtilisateur(utilisateur);

    System.out.println("Utilisateur modifié avec succès !");
  }  
      
      /*************************/
 /*    User utilisateurRecherche = UserCRUD.rechercherUtilisateurParId(24);
        if (utilisateurRecherche != null) {
            System.out.println("Utilisateur trouvé :");
            System.out.println("ID : " + utilisateurRecherche.getId());
            System.out.println("Nom : " + utilisateurRecherche.getNom());
            System.out.println("Prénom : " + utilisateurRecherche.getPrenom());
            System.out.println("Email : " + utilisateurRecherche.getEmail());
            System.out.println("Numéro de téléphone : " + utilisateur.getNumeroTelephone());
            System.out.println("Date de naissance : " + utilisateur.getDateNaissance());
            System.out.println("Genre : " + utilisateur.getGenre());
            System.out.println("Rôle : " + utilisateurRecherche.getRole());
            
        } else {
            System.out.println("Utilisateur non trouvé."); 
        }
        
       /**************************************/ 
        
    // suprimer User par ID
  /*  int idUtilisateurASupprimer = 7; //  l'ID a supprimer
    UserCRUD.supprimerUtilisateur(idUtilisateurASupprimer);
    System.out.println("Utilisateur supprimé avec succès !");*/

    
    /******************************/
  //rechercher tous les utilisateurs 
  List<User> tousLesUtilisateurs = UserCRUD.rechercherTousLesUtilisateurs();
for (User utilisateur : tousLesUtilisateurs) {
    // Traitez chaque utilisateur selon mes besoins
    System.out.println("ID : " + utilisateur.getId());
    System.out.println("Nom : " + utilisateur.getNom());
    System.out.println("Prénom : " + utilisateur.getPrenom());
    System.out.println("Email : " + utilisateur.getEmail());
    System.out.println("Numéro de téléphone : " + utilisateur.getNumeroTelephone());
    System.out.println("Date de naissance : " + utilisateur.getDateNaissance());
    System.out.println("Genre : " + utilisateur.getGenre());
    System.out.println("Rôle : " + utilisateur.getRole());
    
}

  

 
  
  
  } 


  }

