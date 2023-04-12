/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author FaroukDev
 */
import entities.Product;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceProducts {

    // Create
    void ajouterProduit(Product p) throws SQLException;

    // Update
    void modifierProduit(Product p, String nomProduit, String etatProduit, String prixProduit, String descriptionProduit, String imagePath, String statusProduit, String localisationProduit, String brand, String details) throws SQLException;

    // Delete
    void supprimerProduit(Product p) throws SQLException;
    
    List<Product> afficherProduct();
    
}
