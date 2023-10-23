/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.util.List;

/**
 *
 * @author ghofr
 */
public interface IAvisCRUD {
   void ajouterAvis(Avis avis);
    void modifierAvis(Avis avis);
    void supprimerAvis(int idAvis);
    Avis rechercherAvisParId(int idAvis);
    List<Avis> rechercherTousLesAvis(); 
}
