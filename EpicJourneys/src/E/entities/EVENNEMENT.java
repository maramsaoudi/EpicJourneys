/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.entities;

import java.sql.Date;

/**
 *
 * @author saif chaalene
 */
public class EVENNEMENT {
      // private String TypeEvenement;
    //private Date date_depart;
    //private String description;
   // private String destination;
   // private int guide_id;
    //private int IdEvenement;
    private String image;
    private float prix;
    private String titre;
    //private String SponsorEvenement;
    private String ville;
    private String duree;

    public EVENNEMENT() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

}
