/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys;

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
        


        CommandeCRUD commandeCRUD = new CommandeCRUD();

        // Ajouter une commande
        Commande nouvelleCommande = new Commande("valise rouge", "client ali");
        commandeCRUD.ajouterEntities(nouvelleCommande);

        // Afficher la liste des commandes
        System.out.println("Liste des commandes :");
        for (Commande commande : commandeCRUD.listeDesEntities()) {
            System.out.println(commande);
        }

        // Modifier une commande (assurez-vous d'avoir un ID valide)
        Commande commandeAModifier = commandeCRUD.listeDesEntities().get(0); // Remplacez par un ID valide
        commandeAModifier.setNomp("Valise rose");
        commandeAModifier.setClient("Client2");
        commandeCRUD.modifierEntities(commandeAModifier);

        // Afficher à nouveau la liste des commandes
        System.out.println("Liste des commandes après modification :");
        for (Commande commande : commandeCRUD.listeDesEntities()) {
            System.out.println(commande);
        }

        // Supprimer une commande (assurez-vous d'avoir un ID valide)
        //Commande commandeASupprimer = commandeCRUD.listeDesEntities().get(4); // Remplacez par un ID valide
        //commandeCRUD.supprimerEntities(commandeASupprimer);

        // Afficher la liste des commandes après suppression
        System.out.println("Liste des commandes après suppression :");
        for (Commande commande : commandeCRUD.listeDesEntities()) {
            System.out.println(commande);
        }
        //////////////
        /*
        PanierCRUD panierCRUD = new PanierCRUD();
         // Ajouter un panier
        Panier nouveauPanier = new Panier("Nom du produit", "Nom du client", "Date de commande", "Adresse de livraison", "Frais de livraison");
        panierCRUD.ajouterEntities(nouveauPanier);

        // Afficher la liste des paniers
        System.out.println("Liste des paniers :");
        for (Panier panier : panierCRUD.listeDesEntities()) {
            System.out.println(panier);
        }

        // Modifier un panier (assurez-vous d'avoir un ID valide)
        Panier panierAModifier = panierCRUD.listeDesEntities().get(0); // Remplacez par un ID valide
        panierAModifier.setNomp("Nouveau nom du produit");
        panierAModifier.setClient("Nouveau nom du client");
        panierCRUD.modifierEntities(panierAModifier);

        // Afficher à nouveau la liste des paniers
        System.out.println("Liste des paniers après modification :");
        for (Panier panier : panierCRUD.listeDesEntities()) {
            System.out.println(panier);
        }

        // Supprimer un panier (assurez-vous d'avoir un ID valide)
        Panier panierASupprimer = panierCRUD.listeDesEntities().get(0); // Remplacez par un ID valide
        panierCRUD.supprimerEntities(panierASupprimer);

        // Afficher la liste des paniers après suppression
        System.out.println("Liste des paniers après suppression :");
        for (Panier panier : panierCRUD.listeDesEntities()) {
            System.out.println(panier);
        }*/
        
    }
}

        
           
        
        
        
    
    
    
    

