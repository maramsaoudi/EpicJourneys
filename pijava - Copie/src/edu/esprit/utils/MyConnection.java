/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import com.mysql.jdbc.MySQLConnection;
import edu.esprit.entities.Evenement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mkanz
 */
public class MyConnection {

  
  
    
    
    
    private final String URL = "jdbc:mysql://localhost:3306/db_mariem";
    private final String USER = "root";
    private final String PASSWORD = "";
    private static MyConnection instance;
    private Connection cnx;
    
    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

 
    
    
      
    
    
    
    
}
