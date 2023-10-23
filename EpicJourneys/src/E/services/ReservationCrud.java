/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E.services;

import E.entities.Reservation;
import E.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saif chaalene
 */
public class ReservationCrud {
    Connection cnx2 ;
    public ReservationCrud(){
        cnx2= MyConnection.getInstance().getCnx();
    
    }
    
    public void AjouterReservation2(){
        try {
            String requete = "INSERT INTO RESERVATION  (IdReservation,IdUser,IdEvennement) "
                    + "VALUES ('11','22','33')";
            Statement st =  cnx2.createStatement() ;
            st.executeUpdate(requete);
            System.out.println("reservation ajoute avec succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void AjouterReservation(Reservation r ){
        try {
            String requete2="INSERT INTO RESERVATION (IdReservation,IdUser,IdEvennement) "
                    +"VALUES(?,?,?)";     
            PreparedStatement pst = cnx2.prepareStatement (requete2);
            pst.setInt(1,r.getIdRes());
            pst.setInt(2,r.getIdUser());
            pst.setInt(3,r.getIdEvennement());
            pst.executeUpdate();
                        System.out.println("reservation2  avec succes");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    
    public List<Reservation> AfficherReservation() {
         List<Reservation> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reservation";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reservation p = new Reservation();
                p.setIdRes(rs.getInt(1));
                p.setIdUser(rs.getInt(2));
                p.setIdEvennement(rs.getInt(3));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    }

