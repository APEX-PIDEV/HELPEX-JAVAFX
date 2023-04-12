/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class SignUpController implements Initializable {

    @FXML
    private Button BtnSignupP;
    @FXML
    private TextField Unom;
    @FXML
    private TextField Uprenom;
    @FXML
    private RadioButton Uhomme;
    @FXML
    private RadioButton Ufemme;
    @FXML
    private TextField Uadresse;
    @FXML
    private TextField Unumtel;
    @FXML
    private TextField Uemail;
    @FXML
    private PasswordField Umdp;
    @FXML
    private HBox Updp;
    @FXML
    private TextArea Ubio;
    @FXML
    private DatePicker Udate;
    @FXML
    private TextField Udiplome;
    @FXML
    private TextField Utarif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SignUpPro(ActionEvent event) {
    }
    
}
