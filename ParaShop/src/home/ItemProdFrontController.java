/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ItemProdFrontController implements Initializable {

    @FXML
    private HBox itemP;
    @FXML
    private Label nom_produit;
    @FXML
    private Label id_categorie_produit;
    @FXML
    private Label etat_produit;
    @FXML
    private Label PrixProduit;
    @FXML
    private Label Created_At;
    @FXML
    private Label Updated_At;
    @FXML
    private Label Authorisation;
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
    }

    @FXML
    private void modifierProduitBtn(ActionEvent event) {
    }

    @FXML
    private void detail(ActionEvent event) {
    }
    
}
