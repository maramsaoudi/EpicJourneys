package pi;
import java.sql.Connection;
import pi.CarteFidelite.niveauCarte;

public class PI { 
    Connection cnx2;

    public static void main(String[] args) { 
        boolean x; 
        
        myConnection mc = new myConnection();
        
        parseDate dateParser = new parseDate(); // Create an instance of parseDate
        
        CarteFidelite f1 = new CarteFidelite(0, 0, dateParser.parseDate("2023-04-10"), dateParser.parseDate("2024-04-10"), niveauCarte.bronze); 
        
        // Call the ajouterCarteFidelite method directly on the f1 object
        x = f1.ajouterCarteFidelite();
    }
}
