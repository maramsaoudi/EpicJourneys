
package OffreSpecialEvenment;

import java.sql.Date;
import java.util.logging.Logger;
import javafx.scene.layout.BackgroundSize;
import org.omg.CORBA.LongHolder;
import pi.CarteFidelite.NiveauCarte;

/**
 *
 */ 
public class OffreSpecialEvenment extends Evenement{  
    private Date date_depart; 
    private String description,image,titre,destination,catégorie; 
    private float prix; 
    private int guide_id; 
    
    
    
    private int IdOffreSpecialEvenment; 

    public OffreSpecialEvenment( String catégorie, Date date_depart, String description, String destination, int guide_id, String image, float prix, String titre,NiveauCarte niveau) {
                this.catégorie = catégorie; 
                this.date_depart=date_depart ;
                this.description=description;
                this.destination=destination; 
                this.guide_id = guide_id;
                this.image = image;
                this.prix = prix;
                this.titre =titre;
                this.niveau = niveau;
    }
    private NiveauCarte niveau;
     
    @Override
 public String getTitre() {
        return titre;
    }  

    @Override
    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImage() {
        return image;
    } 

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
 
    
    public int getIdOffreSpecialEvenment() {
        return IdOffreSpecialEvenment;
    }
    

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }

    @Override
    public float getPrix() {
        return prix;
    }

    @Override
    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public int getGuide_id() {
        return guide_id;
    }

    @Override
    public void setGuide_id(int guide_id) {
        this.guide_id = guide_id;
    }
 



    public OffreSpecialEvenment(NiveauCarte niveau) {
        super(); 
        this.niveau = niveau;
    }


  

    OffreSpecialEvenment() { 
        
    }


    public NiveauCarte getNiveau() {
        return niveau;
    }

    void setNiveau(NiveauCarte niveau) {
        this.niveau = niveau;
    }  
    void setIdOffreSpecialEvenment(int IdOffreSpecialEvenment) 
    { 
                this.IdOffreSpecialEvenment = IdOffreSpecialEvenment;

    }

    
 @Override  
public String toString() { 
    return super.toString() + "Niveau Carte :" + this.niveau; 
    
    
}  
public enum NiveauCarte { 
    bronze , 
    silver , 
    gold, 
}

}

    