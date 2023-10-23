/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author mkanz
 */
public class Sponsor {
    
   private int IdSponsor;
   private String NomSponsor;
   private String Secteurdactivite; 
   private String adresseSponsor;
   private int numtelSponsor;
   private String emailSponsor;

    public Sponsor() {
    }
   
   
   
   
   

    public Sponsor(int IdSponsor, String NomSponsor, String Secteurdactivite, String adresseSponsor, int numtelSponsor, String emailSponsor) {
        this.IdSponsor = IdSponsor;
        this.NomSponsor = NomSponsor;
        this.Secteurdactivite = Secteurdactivite;
        this.adresseSponsor = adresseSponsor;
        this.numtelSponsor = numtelSponsor;
        this.emailSponsor = emailSponsor;
    }

    public Sponsor(String NomSponsor, String Secteurdactivite, String adresseSponsor, int numtelSponsor, String emailSponsor) {
        this.NomSponsor = NomSponsor;
        this.Secteurdactivite = Secteurdactivite;
        this.adresseSponsor = adresseSponsor;
        this.numtelSponsor = numtelSponsor;
        this.emailSponsor = emailSponsor;
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   

    public int getIdSponsor() {
        return IdSponsor;
    }

    public void setIdSponsor(int IdSponsor) {
        this.IdSponsor = IdSponsor;
    }

    public String getNomSponsor() {
        return NomSponsor;
    }

    public void setNomSponsor(String NomSponsor) {
        this.NomSponsor = NomSponsor;
    }

    public String getSecteurdactivite() {
        return Secteurdactivite;
    }

    public void setSecteurdactivite(String Secteurdactivite) {
        this.Secteurdactivite = Secteurdactivite;
    }

    public String getAdresseSponsor() {
        return adresseSponsor;
    }

    public void setAdresseSponsor(String adresseSponsor) {
        this.adresseSponsor = adresseSponsor;
    }

    public int getNumtelSponsor() {
        return numtelSponsor;
    }

    public void setNumtelSponsor(int numtelSponsor) {
        this.numtelSponsor = numtelSponsor;
    }

    public String getEmailSponsor() {
        return emailSponsor;
    }

    public void setEmailSponsor(String emailSponsor) {
        this.emailSponsor = emailSponsor;
    }
   
   
   
   
   
   
}
