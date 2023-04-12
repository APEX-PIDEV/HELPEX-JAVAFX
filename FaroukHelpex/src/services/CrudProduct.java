/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author FaroukDev
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import entities.Product;
import interfaces.InterfaceProducts;

import utils.MyConnection;
 
public class CrudProduct implements InterfaceProducts {
    

 Statement ste;
    Connection conn= MyConnection.getInstance().getConn();

    // Create
    @Override
    public void ajouterProduit(Product p) throws SQLException {
    String sql = "INSERT INTO produits(categorie_produit_id, user_id, nom_produit, etat_produit, prix_produit, description_produit, image_path, status_produit, localisation_produit, brand, created_at, updated_at, details, authorisation) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?)";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, p.getCategoryId());
    statement.setInt(2, p.getUserId());
    statement.setString(3, p.getName());
    statement.setString(4, p.getCondition());
    statement.setString(5, p.getPrice());
    statement.setString(6, p.getDescription());
    statement.setString(7, p.getImagePath());
    statement.setString(8, p.getStatus());
    statement.setString(9, p.getLocation());
    statement.setString(10, p.getBrand());
    statement.setString(11, p.getDetails());
    statement.setBoolean(12, p.isAuthorization());

    statement.executeUpdate();
}


    // Update
    @Override
    public void modifierProduit(Product p, String nomProduit, String etatProduit, String prixProduit, String descriptionProduit, String imagePath, String statusProduit, String localisationProduit, String brand, String details) throws SQLException {
        String sql = "UPDATE produits SET nom_produit = ?, etat_produit = ?, prix_produit = ?, description_produit = ?, image_path = ?, status_produit = ?, localisation_produit = ?, brand = ?, updated_at = NOW(), details = ? WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, nomProduit);
        statement.setString(2, etatProduit);
        statement.setString(3, prixProduit);
        statement.setString(4, descriptionProduit);
        statement.setString(5, imagePath);
        statement.setString(6, statusProduit);
        statement.setString(7, localisationProduit);
        statement.setString(8, brand);
        statement.setString(9, details);
        statement.setInt(10, p.getId());

        statement.executeUpdate();
    }

    // Delete
    @Override
    public void supprimerProduit(Product p) throws SQLException {
        String sql = "DELETE FROM produits WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, p.getId());

        statement.executeUpdate();
    }

// Read
@Override
public List<Product> afficherProduct() {
    List<Product> productList = new ArrayList<>();

    try {
        String sql = "SELECT * FROM produits";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Product p = new Product();
            p.setId(resultSet.getInt("id"));
            p.setCategoryId(resultSet.getInt("categorie_produit_id"));
            p.setUserId(resultSet.getInt("user_id"));
            p.setName(resultSet.getString("nom_produit"));
            p.setCondition(resultSet.getString("etat_produit"));
            p.setPrice(resultSet.getString("prix_produit"));
            p.setDescription(resultSet.getString("description_produit"));
            p.setImagePath(resultSet.getString("image_path"));
            p.setStatus(resultSet.getString("status_produit"));
            p.setLocation(resultSet.getString("localisation_produit"));
            p.setBrand(resultSet.getString("brand"));
            p.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            p.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
            p.setDetails(resultSet.getString("details"));
            p.setAuthorization(resultSet.getBoolean("authorisation"));

            productList.add(p);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return productList;
}


}