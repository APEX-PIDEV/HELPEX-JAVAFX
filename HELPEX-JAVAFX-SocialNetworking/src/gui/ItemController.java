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
import javafx.util.Duration;
import services.CRUDPoste;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private Label id_poste;
    @FXML
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private Button detail;
    @FXML
    private Button share;

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
         String title = "Poste Deleted";
         String message = "The Poste has been deleted successfully.";
    NotificationType notificationType = NotificationType.SUCCESS;
    TrayNotification trayNotification = new TrayNotification(title, message, notificationType);
    trayNotification.showAndDismiss(Duration.seconds(5));
        
    }

    @FXML
    private void Detail(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterC.fxml"));
            AnchorPane newInterface = loader.load();
            AjouterCController newInterfaceController = loader.getController();
            Poste P=new Poste();
            P.setId(Integer.parseInt(id_poste.getText()));
          
            newInterfaceController.setP(P);
             Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();

    }

    @FXML
    private void Share(ActionEvent event) {

                        String accessToken = "EAACRyMBXvYEBALxcsCdHBzSHpZAOhMJGUVSo6zEXxCuL9m6K4dgDHkKTImwZCMtgB2jXG59n6nB8Mu8TfTawhb3KtPyhhzyel2moLbBRZAaqY2HZBJmz5gaGQCRsYkfziyMowNtht8cxfvy2VuYUpove6m0Mj3N1DVfxttY0ggjfmxR2qkKn";

                        FacebookClient client = new DefaultFacebookClient(accessToken);

                        try {
                            FacebookType response = client.publish("116954624708499" + "/photos", FacebookType.class,
                                    BinaryAttachment.with(titre_poste.getText(),new FileInputStream(new File("C:\\test.png"))),
                                    Parameter.with("message", description_poste.getText()));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
}
