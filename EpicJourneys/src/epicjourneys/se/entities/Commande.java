/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epicjourneys.se.entities;


public class Commande {
    private int id;
    private String nomp;    
    private String client;

     
 
    public Commande(int id, String nomp, String client) {
        this.id = id;
        this.nomp = nomp;
        this.client = client;
    }
    
    public Commande( String nomp, String client) {
        this.nomp = nomp;
        this.client = client;
    }

    public Commande() {
        
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

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", nomp='" + nomp + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}