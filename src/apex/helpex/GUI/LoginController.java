/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mdp;
    @FXML
    private TextField email;
    @FXML
    private Button btnlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) {
    }

    void setemail(String email) {
this.email.setText(email);    }

    @FXML
    private void signupchoose(ActionEvent event) {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("SignUpChoose.fxml")) ;
              try {
                  Parent root= loader.load();
                  email.getScene().setRoot(root);

              } catch (IOException ex) {
        System.out.println("Error: "+ ex.getMessage());
                }
    }
    
}
