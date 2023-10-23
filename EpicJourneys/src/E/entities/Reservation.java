/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.entities;

/**
 *
 * @author saif chaalene
 */
public class Reservation {
    private int IdRes ;
    private int IdUser ;
    private int IdEvennement;

    public Reservation() {
    }

    
    public Reservation(int IdRes, int IdClient, int IdEvennement) {
        this.IdRes = IdRes;
        this.IdUser = IdUser;
        this.IdEvennement = IdEvennement;
    }

    public Reservation(int IdUser, int IdEvennement) {
        this.IdUser = IdUser;
        this.IdEvennement = IdEvennement;
    }

     public Reservation( int IdEvennement) {
      
        this.IdEvennement = IdEvennement;
    }
    public int getIdRes() {
        return IdRes;
    }

    public void setIdRes(int IdRes) {
        this.IdRes = IdRes;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdEvennement() {
        return IdEvennement;
    }

    public void setIdEvennement(int IdEvennement) {
        this.IdEvennement = IdEvennement;
    }

    @Override
    public String toString() {
        return "reservation{" + "IdRes=" + IdRes + ", IdClient=" + IdUser + ", IdEvennement=" + IdEvennement + '}';
    }
   
    
    
    
}
