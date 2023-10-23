/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Evenement;
import edu.esprit.entities.Sponsor;
import edu.esprit.services.EvenementCrud;
import edu.esprit.services.SponsorCrud;
import edu.esprit.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author mkanz
 */
public class MainClass {
    public static void main(String[] args) {
     
       //Evenement e3 = new Evenement("ECOLOGY",Date.valueOf("2022-2-18"), "description e2...", "Pays e2", "1 mois", 1500, "nature.png", 0, "Nature Escape");
      //ecd.modifierEvenement(e3);
       EvenementCrud ecd = new EvenementCrud();
       ecd.afficherEvenements();
      
   // Evenement e4 = new Evenement();
    //e4.setIdEvenement(4); 
    //ecd.supprimerEvenement(e4);

    }
    
 
}
