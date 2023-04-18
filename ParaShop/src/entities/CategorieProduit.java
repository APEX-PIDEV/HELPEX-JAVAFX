/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author FaroukDev
 */
public class CategorieProduit {
    private int id ; 
    private String nom_cat_produit ;

    public CategorieProduit() {
    }

    public CategorieProduit(String nom_cat_produit) {
        this.nom_cat_produit = nom_cat_produit;
    }
    
    
    
    
    public CategorieProduit(int id, String nom_cat_produit) {
        this.id = id;
        this.nom_cat_produit = nom_cat_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_produit() {
        return nom_cat_produit;
    }

    public void setNom_cat_produit(String nom_cat_produit) {
        this.nom_cat_produit = nom_cat_produit;
    }

    @Override
    public String toString() {
        return "CategorieProduit{" + "id=" + id + ", nom_cat_produit=" + nom_cat_produit + '}';
    }

    
    
    
    
}
