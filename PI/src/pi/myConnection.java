package pi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class myConnection {
    Connection cnx;   
    public static myConnection instance; 
    public String url="jdbc:mysql://localhost:3306/epicJourneys";  
    public String login="root";  
    public String pwd="";  
    private myConnection()
    {  
        try{ 
            cnx=DriverManager.getConnection(url,login,pwd);  
            System.out.println("Connexion établie!") ;
             
        }  
        catch(SQLException ex ) { 
        System.out.println(ex.getMessage());  
        }    
       
    } 
 public Connection getCnx(){ 
     return cnx; 
 }
public static myConnection getInstance(){ 
    System.out.println(instance == null);
    if (instance == null) { 
        instance = new myConnection(); 
    } 
    return instance;   
}    
 

 
}
