/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.entities;

public class Panier {
    private int id;
    private String nomp;
    private String client;
    private String Date_de_Commande;
    private String Adresse_de_Livraison;
    private String Frais_de_Livraison;

    // Constructeur
    public Panier(int id, String nomp, String client, String Date_de_Commande, String Adresse_de_Livraison, String Frais_de_Livraison) {
        this.id = id;
        this.nomp = nomp;
        this.client = client;
        this.Date_de_Commande = Date_de_Commande;
        this.Adresse_de_Livraison = Adresse_de_Livraison;
        this.Frais_de_Livraison = Frais_de_Livraison;
    }
    
        // Constructeur
    public Panier(String nomp, String client, String Date_de_Commande, String Adresse_de_Livraison, String Frais_de_Livraison) {
        this.id = id;
        this.nomp = nomp;
        this.client = client;
        this.Date_de_Commande = Date_de_Commande;
        this.Adresse_de_Livraison = Adresse_de_Livraison;
        this.Frais_de_Livraison = Frais_de_Livraison;
    }

    public Panier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters et Setters
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate_de_Commande() {
        return Date_de_Commande;
    }

    public void setDate_de_Commande(String Date_de_Commande) {
        this.Date_de_Commande = Date_de_Commande;
    }

    public String getAdresse_de_Livraison() {
        return Adresse_de_Livraison;
    }

    public void setAdresse_de_Livraison(String Adresse_de_Livraison) {
        this.Adresse_de_Livraison = Adresse_de_Livraison;
    }

    public String getFrais_de_Livraison() {
        return Frais_de_Livraison;
    }

    public void setFrais_de_Livraison(String Frais_de_Livraison) {
        this.Frais_de_Livraison = Frais_de_Livraison;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", nomp='" + nomp + '\'' +
                ", client='" + client + '\'' +
                ", Date_de_Commande='" + Date_de_Commande + '\'' +
                ", Adresse_de_Livraison='" + Adresse_de_Livraison + '\'' +
                ", Frais_de_Livraison='" + Frais_de_Livraison + '\'' +
                '}';
    }
}
