/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.util.Date;

public class Evenement {

    private String TypeEvenement;
private java.util.Date date_depart;
    private String description;
    private String destination;
    private int guide_id;
    private int IdEvenement;
    private String image;
    private float prix;
    private String titre;
    private String SponsorEvenement;

    

    public Evenement() {
    }

    public Evenement(String TypeEvenement, Date date_depart, String description, String destination, int guide_id, String image, float prix, String titre, String SponsorEvenement) {
        this.TypeEvenement = TypeEvenement;
        this.date_depart = date_depart;
        this.description = description;
        this.destination = destination;
        this.guide_id = guide_id;
        this.image = image;
        this.prix = prix;
        this.titre = titre;
        this.SponsorEvenement = SponsorEvenement;
    }

    @Override
    public String toString() {
        return "Evenement{" + "TypeEvenement=" + TypeEvenement + ", date_depart=" + date_depart + ", description=" + description + ", destination=" + destination + ", guide_id=" + guide_id + ", IdEvenement=" + IdEvenement + ", image=" + image + ", prix=" + prix + ", titre=" + titre + ", SponsorEvenement=" + SponsorEvenement + '}';
    }

   


    

   

    public String getTypeEvenement() {
        return TypeEvenement;
    }

    public void setTypeEvenement(String TypeEvenement) {
        this.TypeEvenement = TypeEvenement;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }

    public int getIdEvenement() {
        return IdEvenement;
    }

    public void setIdEvenement(int IdEvenement) {
        this.IdEvenement = IdEvenement;
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

    public String getSponsorEvenement() {
        return SponsorEvenement;
    }

    public void setSponsorEvenement(String SponsorEvenement) {
        this.SponsorEvenement = SponsorEvenement;
    }

 
    
}

    

