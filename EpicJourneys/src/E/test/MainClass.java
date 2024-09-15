/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.test;

import E.entities.Commentaire;
import E.entities.Recherche;
import E.entities.Reservation;
import E.services.CommentaireCrud;
import E.services.RechercheEvennementCrud;
import E.services.ReservationCrud;
import E.utils.MyConnection;
import java.sql.Date;
import java.time.Period;

/**
 *
 * @author saif chaalene
 */
public class MainClass {
    public static void main (String[] args){
        MyConnection mc = MyConnection.getInstance() ;
       //ReservationCrud rcd= new ReservationCrud();    
       ///Reservation r2 =new Reservation(99);
       //acd.AjouterReservation2(r2);
       
   //  Commentaire c=new Commentaire("validation esprit");
     //  CommentaireCrud cc= new CommentaireCrud();
       //cc.AjouterCommantaire(c);
      // Commentaire c2=new Commentaire();      
      // c2.setIdCommentaire(2);
       //cc.SuprimerCommantaire(5);
       //cc.ModifierCommentaire(5, c);
       
        RechercheEvennementCrud rec = new RechercheEvennementCrud();
        System.out.println(rec.o());
    
     // Date dateDepart1 = new Date(22/12/2023);
     //  Recherche rc=new Recherche("bizerte","food",200f,dateDepart1);
     //  RechercheEvennementCrud are =new RechercheEvennementCrud();
     // are.AjouterRechercheEvennement(rc);
        //System.out.println(rcd.AfficherReservation());
       
    }
}
