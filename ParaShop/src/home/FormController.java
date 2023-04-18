/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.CategorieProduit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import services.CrudCategorieProduit;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class FormController implements Initializable {

    @FXML
    public TextField nom_cat_produit;

    /**
     * Initializes the controller class.
     */
    
     private CategorieProduit c;
     
     public CategorieProduit getC() {
        return c;
    }
    
     
     public void setC(CategorieProduit c) {
        this.c = c;
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddCategorieProduit(ActionEvent event) {
        String NouveauCategorieProduit = nom_cat_produit.getText();
        CategorieProduit c = new CategorieProduit(NouveauCategorieProduit);
        CrudCategorieProduit CP = new CrudCategorieProduit();
        CP.addCategorie(c);
    }

    @FXML
    private void modifierCategorieProduit(ActionEvent event) {
        
        String NouveauCategorieProduit = nom_cat_produit.getText();
        CrudCategorieProduit CP = new CrudCategorieProduit();
        CP.updateCategorie(c);
        
        
    }
    
}
