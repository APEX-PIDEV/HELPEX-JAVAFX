/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.CategorieProduit;
import java.io.IOException;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CrudCategorieProduit;
import entities.Produit;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label nom_cat_produit;
    @FXML
    private Label id_categorie_produit;
    @FXML
    private Button supprimer_btn;
    @FXML
    private Button button_modifier_centre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerBtn(ActionEvent event) {
        
        
        
        CategorieProduit c=new CategorieProduit();
            c.setId(Integer.parseInt(id_categorie_produit.getText()));
                CrudCategorieProduit crudcentre=new CrudCategorieProduit();
            crudcentre.deleteCategorie(c.getId());
            
    }

    @FXML
    private void modifierCentreBtn(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
        AnchorPane newInterface = loader.load();
        FormController newInterfaceController = loader.getController();
        
        CategorieProduit CatProduit = new CategorieProduit();
        
        Stage stage = new Stage();
        
        
        
        String nom_cat_prod=nom_cat_produit.getText();
        
        newInterfaceController.nom_cat_produit.setText(nom_cat_prod);
        
        
        CatProduit.setId(Integer.parseInt(id_categorie_produit.getText()));
        System.out.println(CatProduit.getId());
            newInterfaceController.setC(CatProduit);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
        
    }

    @FXML
    private void detail(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Produits.fxml"));
        AnchorPane newInterface = loader.load();
            ProduitsCntroller newInterfaceController = loader.getController();
            
            CategorieProduit CP = new CategorieProduit();
            CP.setId(Integer.parseInt(id_categorie_produit.getText()));
            
            Produit P = new Produit ();
            P.setCategoryProduit(CP);
            
            newInterfaceController.setC(P);
            Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
        
        
            
            
            
            
            //i was here ! 
    }
    
    
    
}
