package gui;

import entities.Poste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import services.CRUDPoste;

public class FormController implements Initializable {
    @FXML
    private TextField Titre;
    @FXML
    private TextField Description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
   
    @FXML
    private void AddPoste(ActionEvent event) {
        String titre;
        String description;
        titre = Titre.getText();
        description = Description.getText();
        Poste poste = new Poste(titre,description);
        CRUDPoste p= new CRUDPoste();
        p.ajouterPoste(poste);
    }

}
