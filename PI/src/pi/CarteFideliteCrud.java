
package pi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pi.CarteFidelite.NiveauCarte; 


public class CarteFideliteCrud {  
 public static ArrayList<String> mails = new ArrayList(); 

  private static CarteFideliteCrud instance;


    private Connection cnx2;

    public CarteFideliteCrud() {
        cnx2 = myConnection.getInstance().getCnx();
    }

   public static CarteFideliteCrud getInstance() {
 
        if (instance == null) {
            instance = new CarteFideliteCrud();
        }
        return instance;
    }


    
    
    public int ajouterPts(float price, int IdCarte) {
    CarteFidelite carte = getCarteFideliteById(IdCarte); 

    if (carte != null && carte.getEtatCarte() == CarteFidelite.EtatCarte.active) {
        int pts = Math.round(price / 100.00f);
        
        incrementDateFin(carte);

        return pts;
    } else {
        System.out.println("Carte inactive or not found");
        return 0;
    }
}

public CarteFidelite getCarteFideliteById(int IdCarte) {
    try {
        String sql = "SELECT * FROM CarteFidelite WHERE IdCarte = ?";
        Connection connection = cnx2;
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, IdCarte);  // Set the IdCarte parameter
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            CarteFidelite f = new CarteFidelite();
            f.setIdCarte(IdCarte);
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.EtatCarte etatValue = CarteFidelite.EtatCarte.valueOf(etatStringValue);
            f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            CarteFidelite.NiveauCarte niveau = NiveauCarte.valueOf(NiveauCarteString);
            f.setNiveauCarte(niveau);
            return f;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}

public boolean incrementDateFin(CarteFidelite carte) {
    String updateQuery = "UPDATE CarteFidelite SET DateFin = DATE_ADD(DateFin, INTERVAL 1 YEAR) WHERE IdCarte = ?";

    try (Connection connection = cnx2;
        PreparedStatement st = cnx2.prepareStatement(updateQuery)) {
        st.setInt(1, carte.getIdCarte()); // Use the IdCarte from the CarteFidelite object
        int rowsUpdated = st.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

/*public boolean ajouterCarteFidelite2() 
{ 
    String requete = "INSERT INTO CarteFidelite (PtsFidelite, DateDebut, DateFin, id, NiveauCarte)"+ "VALUES (0,11/10/2023,11/10/2024, 1,bronze )";
        try { 
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete); 
            return true; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
            return false; 
        }
    
}*/
     
public int parseClientID(int IdCarte) {
    try (Connection connection = cnx2;
         PreparedStatement preparedStatement = cnx2.prepareStatement("SELECT id FROM CarteFidelite WHERE IdCarte = ?")) {
        preparedStatement.setInt(1, IdCarte);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); 
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0;
}
    
    
public boolean ajouterCarteFidelite(int id) {
    try {
        // Check if the client already has a loyalty card
        String checkQuery = "SELECT IdCarte FROM CarteFidelite WHERE id = ? LIMIT 1";
        PreparedStatement checkStmt = cnx2.prepareStatement(checkQuery);
        checkStmt.setInt(1, id);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Client already has a loyalty card
            System.out.println("Client already has a loyalty card");
            return false;
        } else {
            // Client does not have a loyalty card, so add one
            String insertQuery = "INSERT INTO CarteFidelite (PtsFidelite, DateDebut, DateFin, id, NiveauCarte, EtatCarte) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = cnx2.prepareStatement(insertQuery);

            // Set initial values for the loyalty card
            insertStmt.setInt(1, 0);

            Calendar calendar = Calendar.getInstance();
            Date DateDebut = calendar.getTime();
            calendar.add(Calendar.YEAR, 1);
            Date DateFin = calendar.getTime();

            insertStmt.setDate(2, new java.sql.Date(DateDebut.getTime()));
            insertStmt.setDate(3, new java.sql.Date(DateFin.getTime()));
            insertStmt.setInt(4, id);
            insertStmt.setString(5, "bronze");
            insertStmt.setString(6, "active");

            int rowsAffected = insertStmt.executeUpdate();

            return rowsAffected > 0; 
            
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

public ArrayList<CarteFidelite> afficherCarte(){  
    ArrayList<CarteFidelite> myList = new ArrayList<>(); 
    Statement st;      
    String requete3 = "SELECT * FROM cartefidelite"; 

        try {
            st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);  
            while (rs.next()) { 
                CarteFidelite f = new CarteFidelite(); 
                f.setIdCarte(rs.getInt(1));  
                f.setPtsFidelite(rs.getInt(2));  
                f.setDateDebut(rs.getDate(3)); 
                f.setDateFin(rs.getDate(4));   
                f.setid(rs.getInt(5));
                String EtatCarteString = rs.getString(6); // Assuming this string represents an enum value
                CarteFidelite.EtatCarte EtatCarteValue = CarteFidelite.EtatCarte.valueOf(EtatCarteString); 
                f.setEtatCarte(EtatCarteValue);
                String etatStringValue= rs.getString(7); 
                CarteFidelite.NiveauCarte etatValue=NiveauCarte.valueOf(etatStringValue); 
                f.setNiveauCarte(etatValue); 

                myList.add(f); 
            }

        } 
        catch (SQLException ex) {
               System.err.println(ex.getMessage()); 
               
            }
        return myList ;  }  


protected void afficherCarte(ArrayList<CarteFidelite> list ) 
{  
    for (CarteFidelite carte:list)  
    {  
        System.out.println(carte.toString()); 
    }
    
}
public CarteFidelite chercherCarte(int IdCarte) {  
    List<CarteFidelite> myList = new ArrayList<>();     
    String requete3 = "SELECT * FROM CarteFidelite WHERE IdCarte= " +IdCarte;  
    Connection connection = cnx2; 
        try {  
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            CarteFidelite f = new CarteFidelite(); 
            if (rs.next()) 
            { 
            f.setIdCarte(IdCarte);
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.NiveauCarte etatValue = NiveauCarte.valueOf(etatStringValue);
            f.setNiveauCarte(etatValue);
            String EtatCarteString = rs.getString(7);
            CarteFidelite.EtatCarte EtatCarteValue = CarteFidelite.EtatCarte.valueOf(EtatCarteString);
            f.setEtatCarte(EtatCarteValue);
            return f;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    
        return null ;  }    

public boolean modifierCarte(CarteFidelite carte,int IdCarte) {
        try {
            Connection connection = cnx2;
            String requete = "UPDATE user SET, PtsFidelite = ? , DateDebut = ? , DateFin = ?, id = ? , NiveauCarte= ? , EtatCarte = ? WHERE id= " + IdCarte ; 
            
            
            PreparedStatement pst = cnx2.prepareStatement(requete);
            
            pst.setInt(1, carte.getPtsFidelite());
            pst.setDate(2, (java.sql.Date) carte.getDateDebut());
            pst.setDate(3, (java.sql.Date) carte.getDateFin());
            pst.setInt(4, carte.getid());
            CarteFidelite.NiveauCarte niveauString = carte.getNiveauCarte(); 
            String Value1 = niveauString.toString(); 
            pst.setString(5,Value1); 
            CarteFidelite.EtatCarte etatString = carte.getEtatCarte();
            String Value2 = etatString.toString(); 

            pst.setString(6,Value2);
            int rowsUpdated=pst.executeUpdate();
            return rowsUpdated>0;} 
         catch (SQLException ex) {
            return false; 
        }}


public int SuspendCarte(int idCarte) {
    CarteFidelite f = new CarteFidelite();
    f = getCarteFideliteById(idCarte);

if (f != null && f.getEtatCarte() != CarteFidelite.EtatCarte.valueOf("suspended")) { 
    String updateQuery = "UPDATE CarteFidelite SET EtatCarte = ? WHERE IdCarte = ?";

        try (Connection connection = cnx2;
             PreparedStatement st = connection.prepareStatement(updateQuery)) {
            st.setString(1, "suspended");
            st.setInt(2, idCarte);
            int rowsUpdated = st.executeUpdate(); 
            
        if (rowsUpdated > 0) {
            System.out.println("successfully updated"); 
            return 1;  
        } 
        else 
        { 
            return -3; 
        }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -2;
        }
    } else if (f== null) {
        System.out.println("Card not found");
        return 0;
    } 
    else {
    System.out.println("Card already Suspended");
    return -1;
}}


public boolean SupprimerCarte(int id) { 
    CarteFidelite f = getCarteFideliteById(id); 
    if (f!=null){
    try (Connection connection = cnx2;
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CarteFidelite WHERE IdCarte = ?")) {
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Carte Successfully deleted");
            return true; 
        } else {
            System.out.println("Carte not found or failed to delete");
            return false; 
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
        return false; 
    }} 
    else 
    { 
        System.out.println("Card not found "); 
        return false;  }} 
    
    public static ObservableList<CarteFidelite> getCarteFidelites()
    { Connection cnx2=myConnection.getInstance().getCnx();  
    ObservableList<CarteFidelite> list = FXCollections.observableArrayList(); 
        try {
            PreparedStatement st = cnx2.prepareStatement("select * from CarteFidelite"); 
            ResultSet rs = st.executeQuery(); 
            while (rs.next()){ 
            CarteFidelite f = new CarteFidelite();
            f.setIdCarte(rs.getInt("IdCarte"));
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.EtatCarte etatValue = CarteFidelite.EtatCarte.valueOf(etatStringValue);
            f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            CarteFidelite.NiveauCarte NiveauCarteValue = NiveauCarte.valueOf(NiveauCarteString);
            f.setNiveauCarte(NiveauCarteValue);
            list.add(f);  
            System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return list; }
    
    public  ObservableList<CarteFidelite> listeDesEntites1() {
    ObservableList<CarteFidelite> myList = FXCollections.observableArrayList();

    try {
        // Create an SQL query to select all records from the "CarteFidelite" table.
        String requete = "SELECT * FROM CarteFidelite";

        // Create a database connection statement.
        Statement st = myConnection.getInstance().getCnx().createStatement();

        // Execute the SQL query and obtain a result set.
        ResultSet rs = st.executeQuery(requete);

        // Iterate through the result set and create CarteFidelite objects.
        while (rs.next()) {
            CarteFidelite f = new CarteFidelite();
            // Set the properties of the CarteFidelite object from the result set.
            f.setIdCarte(rs.getInt("IdCarte"));
            f.setPtsFidelite(rs.getInt(2));
            f.setDateDebut(rs.getDate(3));
            f.setDateFin(rs.getDate(4));
            f.setid(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            CarteFidelite.EtatCarte etatValue = CarteFidelite.EtatCarte.valueOf(etatStringValue);
            f.setEtatCarte(etatValue);
            String NiveauCarteString = rs.getString(7);
            CarteFidelite.NiveauCarte NiveauCarteValue = NiveauCarte.valueOf(NiveauCarteString);
            f.setNiveauCarte(NiveauCarteValue);

            // Add the CarteFidelite object to the list.
            myList.add(f);
        }
    } catch (SQLException ex) {
        // Handle any potential SQL exceptions.
        System.out.println(ex.getMessage());
    }
    return myList; 
}

    public int ActivateCarte(int idCarte) {
    CarteFidelite f = new CarteFidelite();
    f = getCarteFideliteById(idCarte);

if (f != null && f.getEtatCarte() != CarteFidelite.EtatCarte.valueOf("active")) { 
    String updateQuery = "UPDATE CarteFidelite SET EtatCarte = ? WHERE IdCarte = ?";

        try (Connection connection = cnx2;
             PreparedStatement st = connection.prepareStatement(updateQuery)) {
            st.setString(1, "active");
            st.setInt(2, idCarte);
            int rowsUpdated = st.executeUpdate(); 
            
        if (rowsUpdated > 0) {
            System.out.println("successfully updated"); 
            return 1;  
        } 
        else 
        { 
            return -3; 
        }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return -2;
        }
    } else if (f== null) {
        System.out.println("Card not found");
        return 0;
    } 
    else {
    System.out.println("Card already activated");
    return -1;
}} 
  public float  calculate(int PtsFidelite,float price )  
  { 
       return PtsFidelite/100.2f ;  
  } 
  
  
  
public int UpgradeCarte(int IdCarte) {
    String requete = "SELECT ptsFidelite, niveauCarte FROM CarteFidelite WHERE IdCarte = ?";
    try {
        PreparedStatement st = cnx2.prepareStatement(requete);
        st.setInt(1, IdCarte);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            int ptsFidelite = rs.getInt("ptsFidelite");
            NiveauCarte niveauCarte = NiveauCarte.valueOf(rs.getString("niveauCarte"));

            if (ptsFidelite > 1000 && niveauCarte != NiveauCarte.gold) {
                if (niveauCarte == NiveauCarte.bronze) {
                    niveauCarte = NiveauCarte.silver;
                } else if (niveauCarte == NiveauCarte.silver) {
                    niveauCarte = NiveauCarte.gold;
                }
                ptsFidelite -= 1000;
                
                String updateQuery = "UPDATE CarteFidelite SET niveauCarte = ?, ptsFidelite = ? WHERE IdCarte = ?";
                PreparedStatement updateStatement = cnx2.prepareStatement(updateQuery);
                updateStatement.setString(1, niveauCarte.toString());
                updateStatement.setInt(2, ptsFidelite);
                updateStatement.setInt(3, IdCarte);
                updateStatement.executeUpdate();
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());    }
    return 0;
}
    
    
  

} 
 






