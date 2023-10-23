/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.services;

import E.entities.Commentaire;
import E.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saif chaalene
 */
public class CommentaireCrud {
        Connection cnx ;
    public CommentaireCrud(){
        cnx= MyConnection.getInstance().getCnx();
    
    }
    
    public void AjouterCommantaire( Commentaire c){
        String rq="INSERT INTO commentaire (contenu)"
                 + "VALUE (?)";
        
        try {
            PreparedStatement ps= cnx.prepareStatement(rq);
            ps.setString(1, c.getContenu());
            ps.executeUpdate();
             System.out.println("commentaire ajoutee");
            
        } catch (SQLException ex) {
System.err.println(ex.getMessage());        }
        
    }
    
    public void ModifierCommentaire (int IdCommentaireModifier ,Commentaire c){
  
   String rqt = "UPDATE commentaire SET IdCommentaire = ?, contenu = ? WHERE IdCommentaire =? ";
        try {
            
            PreparedStatement pr = cnx.prepareStatement(rqt);

       
            pr.setInt(1, IdCommentaireModifier);
            pr.setString(2, c.getContenu());
            pr.setInt(3, IdCommentaireModifier);
            pr.executeUpdate();
            System.out.println("lignes modifiées ");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }

    
        
       public void SuprimerCommantaire( int IdSup){
               try {
            String requete2 = "DELETE FROM commentaire WHERE IdCommentaire =?";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, IdSup);
            pst.executeUpdate();
            System.out.println("commentaire supprimé ! ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       }
}
    

