/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import apex.helpex.main.Helpex;
import apex.helpex.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ProfileUpdateController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField num;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField bio;
    @FXML
    private TextField adresse;
    @FXML
    private TextField pdp;
    @FXML
    private TextField tarif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateprofile(ActionEvent event) {
                    UserService us = new UserService();
 Helpex.loggedUser.setNom(nom.getText());
  Helpex.loggedUser.setPrenom(prenom.getText());
 Helpex.loggedUser.setAdresse(adresse.getText());
 Helpex.loggedUser.setNum_tel(num.getText());
 Helpex.loggedUser.setPdp(pdp.getText());
 Helpex.loggedUser.setTarif(Integer.parseInt(tarif.getText()));

                    us.updateProfile(Helpex.loggedUser);
        
      
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("Profile.fxml"));
      
        try {
            Parent homeNode = homeLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(ProfileUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
           
      
    
    }    
}
