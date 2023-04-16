/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import entities.Poste;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CRUDPoste;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label titre_poste;
    @FXML
    private Label description_poste;
    @FXML
    private Button detail;
    @FXML
    private Label id_poste;
    @FXML
    private Button modifier;
    @FXML
    private Button delete;

    public Label getId_poste() {
        return id_poste;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modify(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
            AnchorPane newInterface = loader.load();
            FormController newInterfaceController = loader.getController();
            Poste P=new Poste();
            Stage stage = new Stage();
            String t=titre_poste.getText();
            String c=description_poste.getText();
            newInterfaceController.Description.setText(c);
            newInterfaceController.Titre.setText(t);
            P.setId(Integer.parseInt(id_poste.getText()));
            newInterfaceController.setP(P);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
       
    }
      private void refresh() {   
          
    }

    @FXML
    private void Delete(ActionEvent event) {
        Poste P=new Poste();
        P.setId(Integer.parseInt(id_poste.getText()));
         CRUDPoste t= new CRUDPoste();
        t.supprimerPoste(P);
        
    }

    @FXML
    private void Detail(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterC.fxml"));
            AnchorPane newInterface = loader.load();
            AjouterCController newInterfaceController = loader.getController();
            Poste P=new Poste();
            P.setId(Integer.parseInt(id_poste.getText()));
            Commentaire c=new Commentaire();
            c.setP(P);
            newInterfaceController.setC(c);
             Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();

    }
    
}
