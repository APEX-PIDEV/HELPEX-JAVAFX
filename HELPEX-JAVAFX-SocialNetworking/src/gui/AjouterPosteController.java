/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Poste;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CRUDPoste;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterPosteController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private TextArea Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
          String titre;
        String description;
        titre = Titre.getText();
        description = Description.getText();
        Poste p =new Poste(titre,description);
        CRUDPoste t= new CRUDPoste();
        t.ajouterPoste(p);
    }
    
}
