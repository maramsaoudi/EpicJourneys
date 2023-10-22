/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;


import entities.Avis;
import java.util.List;
import services.AvisCRUD;
import Utils.MyConnection;

import java.util.List;

/**
 *
 * @author ghofr
 */
public class MainAvis {
       public static void main(String[] args) {
        // Crée une instance de AvisCRUD
        AvisCRUD avisCRUD = new AvisCRUD();

        // Crée quelques avis
   /*     Avis avis1 = new Avis( "Auteur 1", "5", "C'est un bon produit.");
        Avis avis2 = new Avis( "Auteur 2", "3", "Ce produit est moyen.");
        Avis avis3 = new Avis( "Auteur 3", "5", "Excellent produit !");
        
        // Ajoute les avis à la liste
        avisCRUD.ajouterAvis(avis1);
        avisCRUD.ajouterAvis(avis2);
        avisCRUD.ajouterAvis(avis3);*/

        // Affiche tous les avis
   /*    List<Avis> tousLesAvis = avisCRUD.rechercherTousLesAvis();
        for (Avis avis : tousLesAvis) {
            System.out.println(avis);
        }*/

        // Recherche un avis par ID
        int idAvisRecherche = 2;
        Avis avisRecherche = avisCRUD.rechercherAvisParId(idAvisRecherche);
        if (avisRecherche != null) {
            System.out.println("Avis trouvé : " + avisRecherche);
        } else {
            System.out.println("Avis non trouvé avec l'ID " + idAvisRecherche);
        } 

        // Modifie un avis
     /*   int idAvisAModifier = 1;
        Avis avisModifie = new Avis(idAvisAModifier, "Auteur Modifié", "5", "C'est un excellent voyage !");
        avisCRUD.modifierAvis(avisModifie);

        // Supprime un avis
    /*    int idAvisASupprimer = 3;
        avisCRUD.supprimerAvis(idAvisASupprimer);

        // Affiche à nouveau tous les avis
        System.out.println("Après les modifications :");
        tousLesAvis = avisCRUD.rechercherTousLesAvis();
        for (Avis avis : tousLesAvis) {
            System.out.println(avis);
        }*/
    }
}
 

