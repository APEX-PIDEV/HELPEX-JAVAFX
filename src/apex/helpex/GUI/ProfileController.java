/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;
import apex.helpex.entities.User;

import apex.helpex.main.Helpex;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class ProfileController implements Initializable {

    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label adresse;
    @FXML
    private Label num;
    @FXML
    private Label bio;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     */
    
     public void setInfos() {
        this.email.setText(Helpex.loggedUser.getEmail());
      /*  this.prenom.setText(Helpex.loggedUser.getPrenom());
        this.nom.setText(Helpex.loggedUser.getNom());
        this.adresse.setText(Helpex.loggedUser.getAdresse());
        this.num.setText(Helpex.loggedUser.getNum_tel());
        this.bio.setText(Helpex.loggedUser.getBio());*/
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          this.setInfos();    }    

    @FXML
    private void updateprofile(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUpdate.fxml"));
        try {
            Parent root = loader.load();
            email.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
