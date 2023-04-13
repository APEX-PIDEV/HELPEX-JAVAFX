/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemUserController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Label email;
    @FXML
    private Label sexe;
    @FXML
    private Label adresse;
    @FXML
    private Label num_tel;
    @FXML
    private Label enabled;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void delete(ActionEvent event) {
      /*  ProductsService cs = new ProductsService();
        int id = Integer.parseInt(productId.getText());
        try {
            cs.delete(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            HomeController controller= loader.getController();
            controller.changePage("Products");
            
            productId.getScene().setRoot(root);

        } catch (Exception ex) {
            Logger.getLogger(CategoryItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

   
    
}
