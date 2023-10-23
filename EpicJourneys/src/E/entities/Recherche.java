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
public class Recherche {
    private int IdRecherche;
    private String DestinationRechercher;
    private String TypeRechercher;
    private Date DateDepartRecherche;
    private float BudgetRechercher;

    public Recherche() {
    }

    public Recherche(String DestinationRechercher, String TypeRechercher,float BudgetRechercher, Date DateDepartRecherche) {
        this.DestinationRechercher = DestinationRechercher;
        this.TypeRechercher = TypeRechercher;
        this.DateDepartRecherche = DateDepartRecherche;
        this.BudgetRechercher = BudgetRechercher;
    }

    public Recherche( String DestinationRechercher, String TypeRechercher,float BudgetRechercher, Date DateDepartRecherche, int IdRecherche) {
        this.IdRecherche = IdRecherche;
        this.DestinationRechercher = DestinationRechercher;
        this.TypeRechercher = TypeRechercher;
        this.DateDepartRecherche = DateDepartRecherche;
        this.BudgetRechercher = BudgetRechercher;
    }
    

    public int getIdRecherche() {
        return IdRecherche;
    }

    public void setIdRecherche(int IdRecherche) {
        this.IdRecherche = IdRecherche;
    }

    public String getDestinationRechercher() {
        return DestinationRechercher;
    }

    public void setDestinationRechercher(String DestinationRechercher) {
        this.DestinationRechercher = DestinationRechercher;
    }

    public String getTypeRechercher() {
        return TypeRechercher;
    }

    public void setTypeRechercher(String TypeRechercher) {
        this.TypeRechercher = TypeRechercher;
    }

    public Date getDateDepartRecherche() {
        return DateDepartRecherche;
    }

    public void setDateDepartRecherche(Date DateDepartRecherche) {
        this.DateDepartRecherche = DateDepartRecherche;
    }

    public float getBudgetRechercher() {
        return BudgetRechercher;
    }

    public void setBudgetRechercher(float BudgetRechercher) {
        this.BudgetRechercher = BudgetRechercher;
    }
    
}
