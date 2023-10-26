/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;

/**
 *
 * @author SYB
 */
public class reclamation {
    private int idReclamation;
    private int Id;
    private String textReclamation;
    private String cibleReclamation;
    private String EtatReclamation;
    private Date dateReclamation;
    
public reclamation () {
}
    
    

    public reclamation(int idReclamation, int Id, String textReclamation, String cibleReclamation, String EtatReclamation, Date dateReclamation) {
        this.idReclamation = idReclamation;
        this.Id = Id;
        this.textReclamation = textReclamation;
        this.cibleReclamation = cibleReclamation;
        this.EtatReclamation = EtatReclamation;
        this.dateReclamation = dateReclamation;

    }

    public reclamation(int Id, String textReclamation, String cibleReclamation, String EtatReclamation, Date dateReclamation) {
        this.Id = Id;
        this.textReclamation = textReclamation;
        this.cibleReclamation = cibleReclamation;
        this.EtatReclamation = EtatReclamation; 
        this.dateReclamation = dateReclamation;

    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTextReclamation() {
        return textReclamation;
    }

    public void setTextReclamation(String textReclamation) {
        this.textReclamation = textReclamation;
    }



    public String getCibleReclamation() {
        return cibleReclamation;
    }

    public void setCibleReclamation(String cibleReclamation) {
        this.cibleReclamation = cibleReclamation;
    }

    public String getEtatReclamation() {
        return EtatReclamation;
    }

    public void setEtatReclamation(String EtatReclamation) {
        this.EtatReclamation = EtatReclamation;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    @Override
    public String toString() {
        return "reclamation{" + "idReclamation=" + idReclamation + ", Id=" + Id + ", textReclamation=" + textReclamation + ", cibleReclamation=" + cibleReclamation + ", EtatReclamation=" + EtatReclamation + ", dateReclamation=" + dateReclamation + '}';
    }
    
    

    
    
    
    
    

  

    
    

   
    
}
