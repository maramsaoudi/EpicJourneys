package OffreSpecialEvenment;

import OffreSpecialEvenment.OffreSpecialEvenment.NiveauCarte;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.CarteFidelite;


public class OffreEvenmentMain {
        public static Date currentDate = new Date(System.currentTimeMillis());


    Connection cnx2;  
    public static void main(String[] args) throws Exception {   
        OffreSpecialEvenementCrud cnx2 = new OffreSpecialEvenementCrud(); 
        OffreSpecialEvenment offre = new OffreSpecialEvenment("Bronze",currentDate, "Description", "Destination", 1, "ImageURL", 100.0f, "Titre", NiveauCarte.silver); 
        //cnx2.ajouterOffreSpecialEvenment(offre); 
        
        cnx2.modifierOffreSpecialEvenment(offre, 1);

//MailSender.sendMail("achraf.boubaker@esprit.tn"); 
 



    }
    }   

