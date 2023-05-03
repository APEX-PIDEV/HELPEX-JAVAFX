/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.CategorieProduit;
import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import services.CrudCategorieProduit;
import services.CrudProduits;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class FormProduitController implements Initializable {

    private TextField nom_cat_produit;
    @FXML
    private ChoiceBox<String> CatProduitChoice;
    @FXML
    private ChoiceBox<String> EtatProdChoices;
    @FXML
    private Button SaveProduit;

    ObservableList<String> CategorieListObservable = FXCollections.observableArrayList();
    
    ObservableList<String> EtatProduitListObservable = FXCollections.observableArrayList("New","Use-New","Good","Broken");
    /**
     * Initializes the controller class.
     */
    
    CategorieProduit Categorie = new CategorieProduit();
    String CategorieName ,Etat; 
    @FXML
    private TextField nom_produit;
    @FXML
    private TextField prix_produit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudCategorieProduit CrudCategorie = new CrudCategorieProduit();
         //CrudProduits productCrud = new CrudProduits();
        List<CategorieProduit> CategoriesList;
        CategoriesList = CrudCategorie.getAllCategories();
         for (CategorieProduit Cat : CategoriesList)
             CategorieListObservable.add(Cat.getNom_cat_produit());
         
                 CatProduitChoice.getItems().addAll(CategorieListObservable);
                 EtatProdChoices.getItems().addAll(EtatProduitListObservable);
                 
        CatProduitChoice.setValue(CategorieListObservable.get(0));
        EtatProdChoices.setValue(EtatProduitListObservable.get(0));
        CatProduitChoice.setOnAction(this::getCatChoice);
        EtatProdChoices.setOnAction(this::getEtatChoice);
    }    
    
    private void getCatChoice (ActionEvent event) {
        //System.out.println(CatProduitChoice.getValue());
        /*CrudCategorieProduit CrudCategorie = new CrudCategorieProduit();
        
        Categorie = CrudCategorie.getByNomCategorie(CatProduitChoice.getValue());*/
        
        CategorieName = CatProduitChoice.getValue();

    }
    
       private void getEtatChoice (ActionEvent event) {
        
        Etat = EtatProdChoices.getValue();
    }
    

    @FXML
    private void AddProduit(ActionEvent event) throws SQLException {
        
        CrudProduits P = new CrudProduits();
        CrudCategorieProduit C = new CrudCategorieProduit();
        
        String NomProduit = nom_produit.getText();
        String PrixProduit = prix_produit.getText();
        
        Produit Product = new Produit(C.getByNomCategorie(CategorieName), NomProduit, Etat, PrixProduit, true);
        System.out.println(C.getByNomCategorie(CategorieName));
        P.addProduit(Product, C.getByNomCategorie(CategorieName));
        
        
        //Notifier
        String title = "Product  added";
        String message = "The Product "+nom_produit.getText()+" has been added successfully.";
            NotificationType notificationType = NotificationType.SUCCESS;
            TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
        trayNotification.showAndDismiss(Duration.seconds(5));
        
    }
    
}
