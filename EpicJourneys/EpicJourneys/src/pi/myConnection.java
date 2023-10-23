package pi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class myConnection {
    Connection cnx;    
    public String url="jdbc:mysql://localhost:3306/cartefidelite";  
    public String login="root";  
    public String pwd="";  
    public static myConnection instance; 
    public myConnection()
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
    if (instance == null) { 
        instance = new myConnection(); 
    } 
    return instance; 
}     
 
}
