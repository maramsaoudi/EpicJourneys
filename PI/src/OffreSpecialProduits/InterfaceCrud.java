/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OffreSpecialProduits;

import java.util.List;

/**
 *
 * @author desig
 */
public interface InterfaceCrud {
    public interface InterfaceCRUD<T> {
    public void ajouterEntities(T t);
    public List<T> listeDesEntities();
    public void modifierEntities(T t);
    public void supprimerEntities(T t);
}

}
