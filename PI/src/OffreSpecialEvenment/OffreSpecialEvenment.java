
package OffreSpecialEvenment;

import java.util.logging.Logger;
import javafx.scene.layout.BackgroundSize;
import org.omg.CORBA.LongHolder;
import pi.CarteFidelite.NiveauCarte;

/**
 *
 * @author desig
 */
public class OffreSpecialEvenment extends Evenement{ 
    private NiveauCarte niveau;

    public OffreSpecialEvenment(NiveauCarte niveau) {
        super(); 
        this.niveau = niveau;
    }

    OffreSpecialEvenment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NiveauCarte getNiveau() {
        return niveau;
    }

    void setNiveau(NiveauCarte niveau) {
        this.niveau = niveau;
    } 
 @Override  
public String toString() { 
    return super.toString() + "Niveau Carte :" + this.niveau; 
    
    
}  
public enum NiveauCarte { 
    bronze , 
    silver , 
    gold, 
}


}

    