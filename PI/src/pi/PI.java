package pi;
import com.mysql.jdbc.MySQLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import pi.CarteFidelite.NiveauCarte;

public class PI { 

    Connection cnx2;

    public static void main(String[] args) {  
         
        boolean x;   
        
 

 CarteFideliteCrud cnx3 = new CarteFideliteCrud();
 

cnx3.ajouterCarteFidelite(101); 


        
        
        /*myConnection mc = myConnection.getInstance(); 
        parseDate dateParser = new parseDate(); 
        
        CarteFidelite f1 = new CarteFidelite(0, 0, dateParser.parseDate("2023-04-10"), dateParser.parseDate("2024-04-10"), niveauCarte.bronze); 
        
        x = f1.ajouterCarteFidelite(); */ 
        
        
    }
}
