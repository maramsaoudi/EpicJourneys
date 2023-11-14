/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ghofr
 */
public class Avis {
     private int idAvis;
    private String auteur;
    private String note;
    private String contenu;

    
    public Avis() {
        
    }
    
    public Avis(int id, String auteur, String note, String contenu) {
        this.idAvis = id;
        this.auteur = auteur;
        this.note = note;
        this.contenu = contenu;
    }

    public Avis(String auteur, String note, String contenu) {
        this.auteur = auteur;
        this.note = note;
        this.contenu = contenu;
    }

    
  

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + idAvis +
                ", auteur='" + auteur + '\'' +
                ", note=" + note +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
   

