/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.services;

import E.entities.Commentaire;
import E.entities.Recherche;
import E.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saif chaalene
 */
public class RechercheEvennementCrud {
        Connection cnx3 ;
    public RechercheEvennementCrud(){
        cnx3= MyConnection.getInstance().getCnx();
    
    }
       public void AjouterRechercheEvennement( Recherche rc){
        String rq="INSERT INTO rechercheevennement (DestinationRechercher,TypeRechercher,BudgetRechercher,DateDepartRechercher,IdRecherche)"
                 + "VALUE (?,?,?,?,?)";
        
        try {
            PreparedStatement pss= cnx3.prepareStatement(rq);
            pss.setString(1, rc.getDestinationRechercher());
            pss.setFloat(3, rc.getBudgetRechercher());
            pss.setDate(4, rc.getDateDepartRecherche());
            pss.setString(2, rc.getTypeRechercher());
            pss.setInt(5, rc.getIdRecherche());
            pss.executeUpdate();
             System.out.println("recherche ajoutee");
            
        } catch (SQLException ex) {
System.err.println(ex.getMessage());        }
    }
     public List<Recherche> o(){
         List<Recherche> myListr = new ArrayList<>();
        try {
            String requete = "SELECT * FROM rechercheevennement";
            Statement stt = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rsss = stt.executeQuery(requete);
            while(rsss.next()){
                Recherche p = new Recherche();
                p.setTypeRechercher(rsss.getString(2));
                p.setDestinationRechercher(rsss.getString(1));
               
                myListr.add(p);
            }
        } 
           catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    return myListr;
       }
           
}
