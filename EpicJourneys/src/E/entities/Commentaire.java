/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.entities;

/**
 *
 * @author saif chaalene
 */
public class Commentaire {
    private int IdCommentaire ;
    private String contenu ; 
    private int IdUser ;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public Commentaire() {
    }

    public Commentaire(String contenu) {
        this.contenu = contenu;
    }

    public int getIdCommentaire() {
        return IdCommentaire;
    }

    public void setIdCommentaire(int IdCommentaire) {
        this.IdCommentaire = IdCommentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
}
