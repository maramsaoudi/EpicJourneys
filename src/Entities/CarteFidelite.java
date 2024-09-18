/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Connection;
import java.util.Date;

public class CarteFidelite {
    public enum NiveauCarte {
        gold,
        silver,
        bronze
    }

    public enum EtatCarte {
        active,
        suspended,
        inactive,
    }

    Connection cnx2;
    private int IdCarte, PtsFidelite = 0, IdClient;
    private Date DateDebut = new Date(), DateFin;
    private EtatCarte EtatCarte;
    public NiveauCarte NiveauCarte;

    public CarteFidelite() {

    }

    public CarteFidelite(int IdClient, int PtsFidelite, Date DateDebut, Date DateFin, NiveauCarte NiveauCarte,
            EtatCarte EtatCarte) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.PtsFidelite = PtsFidelite;
        this.IdClient = IdClient;
        this.NiveauCarte = NiveauCarte;
        this.EtatCarte = EtatCarte;
    }

    public CarteFidelite(int IdCarte, int PtsFidelite, int IdClient, Date DateDebut, Date DateFin,
            CarteFidelite.NiveauCarte NiveauCarte) {
        this.IdCarte = IdCarte;
        this.PtsFidelite = PtsFidelite;
        this.IdClient = IdClient;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.NiveauCarte = NiveauCarte;
    }

    public int getIdCarte() {
        return IdCarte;
    }

    public void setIdCarte(int IdCarte) {
        this.IdCarte = IdCarte;
    }

    public int getPtsFidelite() {
        return PtsFidelite;
    }

    public void setPtsFidelite(int PtsFidelite) {
        this.PtsFidelite = PtsFidelite;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public NiveauCarte getNiveauCarte() {
        return NiveauCarte;
    }

    public void setNiveauCarte(NiveauCarte NiveauCarte) {
        this.NiveauCarte = NiveauCarte;
    }

    public EtatCarte getEtatCarte() {
        return EtatCarte;
    }

    public void setEtatCarte(EtatCarte EtatCarte) {
        this.EtatCarte = EtatCarte;
    }

    public Connection getCnx2() {
        return cnx2;
    }

    public void setCnx2(Connection cnx2) {
        this.cnx2 = cnx2;
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "IdCarte=" + IdCarte + ", PtsFidelite=" + PtsFidelite + ", IdClient=" + IdClient
                + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", EtatCarte=" + EtatCarte + ", NiveauCarte="
                + NiveauCarte + '}';
    }

}
