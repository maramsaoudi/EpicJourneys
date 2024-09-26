/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.entities;


public class Produit {
    private int id;
    private String nom;
    private String prixUnitaire;
    private int stock;
    private String image;

    // Constructeur
    public Produit(int id, String nom, String prixUnitaire, int stock, String image) {
        this.id = id;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.image = image;
    }
        public Produit( String nom, String prixUnitaire, int stock, String image) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
        this.image = image;
    }
        
       public Produit( String nom, String prixUnitaire, int stock) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
    }
    
        
    public Produit() {
    }

    // Méthodes d'accès (getters) pour chaque attribut
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }
    

    // Méthodes de modification (setters) si nécessaire
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

