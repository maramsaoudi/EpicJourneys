/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialProduits;

/**
 *
 * @author desig
 */
public class Produit {
    private int id;
    private String nomp;
    private float prixu;
    private String image;
    /////////////////////////////////private int stock;///////////////////////////////////

    // Constructors
    public Produit() {
    }

    public Produit(String nomp, float prixu, String image) {
        this.nomp = nomp;
        this.prixu = prixu;
        this.image = image;
    }

    public Produit(int id, String nomp, float prixu, String image) {
        this.id = id;
        this.nomp = nomp;
        this.prixu = prixu;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public float getPrixu() {
        return prixu;
    }

    public void setPrixu(float prixu) {
        this.prixu = prixu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // toString method for debugging or printing
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nomp='" + nomp + '\'' +
                ", prixu=" + prixu +
                ", image='" + image + '\'' +
                '}';
    }
}

    

