/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import jdk.internal.org.objectweb.asm.tree.analysis.SimpleVerifier;
import org.omg.CORBA.LongHolder;

public class CarteFidelite {   
    private int id_carte,pts_fidelite=0,ID_client; 
    private Date date_debut=new Date(),date_fin;  
    private etatCarte etat_carte; 
    private niveauCarte niveau_carte; 

    CarteFidelite(int i, int i0, Date parseDate, Date parseDate0, niveauCarte niveauCarte) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public niveauCarte getNiveau_carte() {
        return niveau_carte;
    }

    public void setNiveau_carte(niveauCarte niveau_carte) {
        this.niveau_carte = niveau_carte;
    }

    public etatCarte getEtat_carte() {
        return etat_carte;
    }

    public void setEtat_carte(etatCarte etat_carte) {
        this.etat_carte = etat_carte;
    }

    public CarteFidelite(int ID_client,int pts_fidelite, Date date_debut, Date date_fin, niveauCarte niveau_carte,etatCarte etat_carte) {
        Calendar calendar=Calendar.getInstance(); 
        this.date_debut=calendar.getTime(); 
        calendar.add(Calendar.YEAR,1); 
        this.date_fin=calendar.getTime(); 
        this.pts_fidelite = pts_fidelite;
        this.ID_client = ID_client;
        this.niveau_carte = niveau_carte; 
        this.etat_carte=etat_carte; 
    }

    public CarteFidelite() { 
        
    }
   /* public int ajouterPts(float price,CarteFidelite carte){  
       
            String requete3 = "SELECT etat_carte FROM CarteFidelite WHERE id_carte= " + id_carte;
            Connection connection = new myConnection().getCnx() ;
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);

        try {
            PreparedStatement st = connection.prepareStatement()        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            if (carte.etat_carte==etatCarte.active)
            {
            int pts= Math.round(price/100.00f);
            incrementDateFin(carte);
            return pts ;
            }
            else { 
                System.out.println("Carte inactive"); 
                return 0;}
}*/

    
    
    

public boolean incrementDateFin(CarteFidelite carte) {
    String updateQuery = "UPDATE CarteFidelite SET date_fin = DATE_ADD(date_fin, INTERVAL 1 YEAR) WHERE id_carte = ?";

    try (Connection connection = new myConnection().getCnx();
        PreparedStatement st = connection.prepareStatement(updateQuery)) {
        st.setInt(1, carte.getid_carte()); // Use the id_carte from the CarteFidelite object
        int rowsUpdated = st.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}


    
    
    
    public CarteFidelite(int id_carte, int pts_fidelite, int ID_client, Date date_debut, Date date_fin, niveauCarte niveau_carte) {
        this.id_carte = id_carte;
        this.pts_fidelite = pts_fidelite;
        this.ID_client = ID_client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.niveau_carte = niveau_carte;
    }

    public int getid_carte() {
        return id_carte;
    }

    public void setid_carte(int id_carte) {
        this.id_carte = id_carte;
    }

    public int getPts_fidelite() {
        return pts_fidelite;
    }

    public void setPts_fidelite(int pts_fidelite) {
        this.pts_fidelite = pts_fidelite;
    }

    public int getID_client() {
        return ID_client;
    }

    public void setID_client(int ID_client) {
        this.ID_client = ID_client;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


    public void setniveau_carte(niveauCarte niveau_carte) {
        this.niveau_carte = niveau_carte;
    } 

/*public boolean ajouterCarteFidelite2() 
{ 
    String requete = "INSERT INTO CarteFidelite (pts_fidelite, date_debut, date_fin, ID_client, niveau_carte)"+ "VALUES (0,11/10/2023,11/10/2024, 1,bronze )";
        try { 
            Statement st = new myConnection().getCnx().createStatement();
            st.executeUpdate(requete); 
            return true; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
            return false; 
        }
    
}*/
     
 public int parseClientID(int id_carte) 
 {  
    List<CarteFidelite> myList = new ArrayList<>();     
    String requete3 = "SELECT * FROM CarteFidelite WHERE id_carte= " + id_carte;  
    Connection connection = new myConnection().getCnx(); 
        try {  
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            CarteFidelite f = new CarteFidelite(); 
            if (rs.next()) 
            { 
            f.setid_carte(rs.getInt(1));
            f.setPts_fidelite(rs.getInt(2));
            f.setDate_debut(rs.getDate(3));
            f.setDate_fin(rs.getDate(4));
            f.setID_client(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            niveauCarte etatValue = niveauCarte.valueOf(etatStringValue);
            f.setniveau_carte(etatValue);
            String etatCarteString = rs.getString(7);
            etatCarte etatCarteValue = etatCarte.valueOf(etatCarteString);
            f.setEtat_carte(etatCarteValue);
            return f.ID_client;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
            return 0 ; 
     
     
 }
    
    
public boolean ajouterCarteFidelite() {
    String requete = "INSERT INTO CarteFidelite (pts_fidelite, date_debut, date_fin, ID_client, niveau_carte, etat_carte) VALUES (?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement pst = new myConnection().getCnx().prepareStatement(requete);
        pst.setInt(1, 0);

        // Initialize date_debut and date_fin using Calendar
        Calendar calendar = Calendar.getInstance();
        Date date_debut = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date date_fin = calendar.getTime();

        pst.setDate(2, new java.sql.Date(date_debut.getTime()));
        pst.setDate(3, new java.sql.Date(date_fin.getTime()));
        pst.setInt(4, this.getID_client());
        pst.setString(5, "bronze");
        pst.setString(6, "active");

        // Execute the SQL statement
        int rowsAffected = pst.executeUpdate();

        // Check if the insertion was successful (rowsAffected will be greater than 0)
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false if there was an exception
    }
}

public List<CarteFidelite> afficherCarte(){  
    List<CarteFidelite> myList = new ArrayList<>(); 
    Statement st;      
    String requete3 = "SELECT * FROM CarteFidelite"; 

        try {
            st = new myConnection().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete3);  
            while (rs.next()) { 
                CarteFidelite f = new CarteFidelite(); 
                f.setid_carte(rs.getInt(1));  
                f.setPts_fidelite(rs.getInt(2));  
                f.setDate_debut(rs.getDate(3)); 
                f.setDate_fin(rs.getDate(4));   
                f.setID_client(rs.getInt(5));
                String etatStringValue= rs.getString(6); 
                niveauCarte etatValue=niveauCarte.valueOf(etatStringValue); 
                f.setniveau_carte(etatValue); 
                String etatCarteString = rs.getString(7); // Assuming this string represents an enum value
                etatCarte etatCarteValue = etatCarte.valueOf(etatCarteString); // Convert to uppercase to handle case insensitivity
                f.setEtat_carte(etatCarteValue);
                myList.add(f); 
            }

        } 
        catch (SQLException ex) {
               System.err.println(ex.getMessage()); 
               
            }
        return null ; 

        
    }  

    @Override
    public String toString() {
        return "CarteFidelite{" + "id_carte=" + id_carte + ", pts_fidelite=" + pts_fidelite + ", ID_client=" + ID_client + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", niveau_carte=" + niveau_carte + '}';
    }

private void afficherCarte(ArrayList<CarteFidelite> list ) 
{  
    for (CarteFidelite carte:list)  
    {  
        System.out.println(carte.toString()); 
    }
    
}
public enum niveauCarte { 
    gold,
    silver, 
    bronze
}  
public enum etatCarte 
{ 
    active, 
    suspended, 
    inactive, 
    
}
public CarteFidelite chercherCarte(int id_carte) {  
    List<CarteFidelite> myList = new ArrayList<>();     
    String requete3 = "SELECT * FROM CarteFidelite WHERE id_carte= " +id_carte;  
    Connection connection = new myConnection().getCnx(); 
        try {  
            Statement st = connection.createStatement(); 
            ResultSet rs = st.executeQuery(requete3);
            CarteFidelite f = new CarteFidelite(); 
            if (rs.next()) 
            { 
            f.setid_carte(rs.getInt(1));
            f.setPts_fidelite(rs.getInt(2));
            f.setDate_debut(rs.getDate(3));
            f.setDate_fin(rs.getDate(4));
            f.setID_client(rs.getInt(5));
            String etatStringValue = rs.getString(6);
            niveauCarte etatValue = niveauCarte.valueOf(etatStringValue);
            f.setniveau_carte(etatValue);
            String etatCarteString = rs.getString(7);
            etatCarte etatCarteValue = etatCarte.valueOf(etatCarteString);
            f.setEtat_carte(etatCarteValue);
            return f;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    
        return null ;  }    

public boolean modifierUtilisateur(CarteFidelite carte,int id_carte) {
        try {
            Connection connection = new myConnection().getCnx();
            String requete = "UPDATE user SET, pts_fidelite = ? , date_debut = ? , date_fin = ?, ID_client = ? , niveau_carte= ? , etat_carte = ? WHERE id= " + id_carte ; 
            
            
            PreparedStatement pst = connection.prepareStatement(requete);
            
            pst.setInt(1, carte.getPts_fidelite());
            pst.setDate(2, (java.sql.Date) carte.getDate_debut());
            pst.setDate(3, (java.sql.Date) carte.getDate_fin());
            pst.setInt(4, carte.getID_client());
            niveauCarte niveauString = carte.getNiveau_carte(); 
            String Value1 = niveauString.toString(); 
            pst.setString(5,Value1); 
            etatCarte etatString = carte.getEtat_carte();
            String Value2 = etatString.toString(); 

            pst.setString(6,Value2);
            int rowsUpdated=pst.executeUpdate();
            return rowsUpdated>0;} 
         catch (SQLException ex) {
            return false; 
        }}

/*public void suspendCarte(id_Carte) 
{

}*/
}


