/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author ghofr
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.IUserCRUD;
import services.UserCRUD;
import entities.User;
import java.sql.*;


public class MyConnection {
    String url="jdbc:mysql://localhost:3306/pi";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static MyConnection instance;
    public void insertUser(String nom, String prenom, String email, String motDePasse, String numeroTelephone, String dateNaissance, String genre, boolean estClient, boolean estGuide, boolean estAdmin) {
    // Code pour insérer les données dans la base de données
    // Assurez-vous d'ouvrir une connexion, d'exécuter l'instruction SQL appropriée et de la fermer.
    // Vous devrez gérer les paramètres et les exceptions correctement ici.
}


    public MyConnection() {
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
         return instance;
    }

    public void insertUser(String nom, String prenom, String email, String motDePasse, String numeroTelephone, String dateNaissance, String genre, boolean estClient, boolean estGuide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}