/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Centre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.CRUDCentre;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtSiteweb;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> IDColumn;
    @FXML
    private TableColumn<?, ?> NOMColumn;
    @FXML
    private TableColumn<?, ?> ADRESSEColumn;
    @FXML
    private TableColumn<?, ?> EMAILColumn;
    @FXML
    private TableColumn<?, ?> TELEPHONEColumn;
    @FXML
    private TableColumn<?, ?> SITEWEBColumn;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add(ActionEvent event) {
        String nomCentre,adresseCentre,emailCentre,siteWebCentre;
        int telephoneCentre;
            nomCentre = txtNom.getText();
            adresseCentre=txtAdresse.getText();
            emailCentre=txtEmail.getText();
                        telephoneCentre=Integer.parseInt(txtTelephone.getText()) ;

            siteWebCentre=txtSiteweb.getText();
            
            Centre c = new Centre( nomCentre, adresseCentre,emailCentre, telephoneCentre, siteWebCentre);
            CRUDCentre cu = new CRUDCentre();
            cu.ajouterCentre(c);
    }

    @FXML
    private void Delete(ActionEvent event) {
        
    }

    @FXML
    private void Update(ActionEvent event) {
    }
    
}
