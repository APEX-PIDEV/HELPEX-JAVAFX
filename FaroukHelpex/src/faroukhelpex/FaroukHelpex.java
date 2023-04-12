/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faroukhelpex;

/**
 *
 * @author FaroukDev
 */

import utils.MyConnection;
import entities.Product;
import java.sql.SQLException;
import services.CrudProduct;
import java.time.LocalDateTime;


public class FaroukHelpex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         MyConnection cc =  MyConnection.getInstance()  ;
         try {
         Product product1 = new Product(22, 1, 1, "hello", "New", "$1099", "A powerful 5G smartphone from Apple", "/images/iphone12pro.jpg", "Available", "San Francisco, CA", "Apple", LocalDateTime.of(2022, 3, 1, 10, 30), LocalDateTime.of(2022, 3, 1, 10, 30), "5G supported, OLED screen, A14 Bionic chip", true);

Product product2 = new Product(2, 1, 4, "Dell XPS 13", "Used - Like New", "$999", "A lightweight and powerful laptop for productivity", "/images/dellxps13.jpg", "Sold", "New York, NY", "Dell", LocalDateTime.of(2022, 3, 5, 9, 15), LocalDateTime.of(2022, 3, 6, 14, 30), "Intel Core i7, 16GB RAM, 512GB SSD", true);

    CrudProduct testProduct = new CrudProduct();
    
    testProduct.ajouterProduit(product1);
     System.out.println(testProduct.afficherProduct());
    
         }catch (SQLException ex) {
        ex.printStackTrace();
    }

    }
    
}
