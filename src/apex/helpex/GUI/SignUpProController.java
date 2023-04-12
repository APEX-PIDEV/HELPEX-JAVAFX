/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import apex.helpex.entities.User;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import apex.helpex.utils.Validations;
import java.sql.Date;


/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class SignUpProController implements Initializable {

    @FXML
    private Button BtnSignupP;
    @FXML
    private TextField Unom;
    @FXML
    private TextField Uprenom;
    @FXML
    private TextField Uadresse;
    @FXML
    private TextField Unumtel;
    @FXML
    private TextField Uemail;
    @FXML
    private TextArea Ubio;
    @FXML
    private DatePicker Udate;
    @FXML
    private TextField Udiplome;
    @FXML
    private TextField Utarif;
    @FXML
    private TextField Uconfirmmdp;
    @FXML
    private TextField Umdp;
    @FXML
    private Label errormsg;
    @FXML
    private TextField Usexe;
    @FXML
    private TextField Updp;
  
   
     



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SignUpPro(ActionEvent event) {
        
         UserService us = new UserService();
        if (Uemail.getText().equals("") || Umdp.getText().equals("") || Uconfirmmdp.getText().equals("") || Udiplome.getText().equals("")) {
            errormsg.setText("All fields are required!");
        }else if (!Validations.isEmailValid(Uemail.getText())) {
            errormsg.setText("Email adress is not valid!");
        } 
        else if (Umdp.getText().length() < 6) {
            errormsg.setText("Password is too weak!");
        }
        else if (!Uconfirmmdp.getText().equals(Umdp.getText())) {
            errormsg.setText("Passwords does not match!");
        }
       
        else if (us.isEmailTaken(Uemail.getText())){
            errormsg.setText("Email is already taken!");
        }
        else {
String email= Uemail.getText() ;
     String password= Umdp.getText() ;
     String nom= Unom.getText();
 String prenom =Uprenom.getText();
  String sexe=Usexe.getText();
  String adresse=Uadresse.getText();
  String num_tel=Unumtel.getText();
  String pdp=Updp.getText();
  String bio=Ubio.getText();
 Date date_naissance= Date.valueOf(Udate.getValue());
  String diplome=Udiplome.getText();
 int tarif = Integer.parseInt(Utarif.getText());

          
            User u = new User(email, "[\"ROLE_PRO\"]",password,nom,prenom,sexe,adresse,num_tel,pdp,bio,date_naissance,diplome,tarif,1);
            
           us.registerPro(u);
          
        }
        
        
    }


    
}
