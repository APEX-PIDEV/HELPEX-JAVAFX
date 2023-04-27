/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import apex.helpex.utils.JavaMail;
import entities.Centre;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.CRUDCentre;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ItemController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label nom_centre;
    @FXML
    private Label adresse_centre;
    @FXML
    private Label email_centre;
    @FXML
    private Label telephone_centre;
    @FXML
    private Label site_web_centre;
    @FXML
    private Button button_modifier_centre;
    @FXML
    private Label id_centre;
    @FXML
    private Button supprimer_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierCentreBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
            AnchorPane newInterface = loader.load();
            FormController newInterfaceController = loader.getController();
            Centre P=new Centre();
            Stage stage = new Stage();
            String nomcentre=nom_centre.getText();
            String adressecentre=adresse_centre.getText();
            String emailcentre=email_centre.getText();
           // String var=telephone_centre.getText();
      //  int telephonecentre=Integer.parseInt(var);
          //  int telephocentre=telephone_centre.getText();
            String siteweb=site_web_centre.getText();
            
            newInterfaceController.txtNom.setText(nomcentre);
            newInterfaceController.txtAdresse.setText(adressecentre);
            newInterfaceController.txtEmail.setText(emailcentre);
            newInterfaceController.txtSiteweb.setText(siteweb);
                     //   P.setTelephoneCentre(Integer.parseInt(telephone_centre.getText()));
                       // P.setId(Integer.parseInt(id_centre.getText()));

            P.setId(Integer.parseInt(id_centre.getText()));
            System.out.println(P.getId());
            newInterfaceController.setC(P);
            stage.setScene(new Scene(newInterface));
            stage.setOnHidden((event1) -> refresh());
            stage.show();
    }

    private void refresh() {
    }

    @FXML
    private void supprimerBtn(ActionEvent event) {
            Centre c=new Centre();
            c.setId(Integer.parseInt(id_centre.getText()));
            CRUDCentre crudcentre=new CRUDCentre();
            crudcentre.supprimerCentre(c);
    }

    @FXML
    private void detail(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterF.fxml"));
            AnchorPane newInterface = loader.load();
            try {
                //send email to emailField.getText()
                JavaMail.sendMail("ahmedbelhajhassen22@gmail.com");
            } catch (Exception ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            AjouterFormationController newInterfaceController = loader.getController();
            Centre P=new Centre();
            P.setId(Integer.parseInt(id_centre.getText()));
            newInterfaceController.setF(P);
             Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    
}
