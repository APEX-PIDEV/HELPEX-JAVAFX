/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import apex.helpex.entities.Filiere;
import apex.helpex.entities.User;
import apex.helpex.main.Helpex;
import apex.helpex.services.FiliereService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Asus-PC
 */
public class FiliereController implements Initializable {

    @FXML
    private Label currentUser;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label title;
    @FXML
    private VBox pnItems;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     currentUser.setText(Helpex.loggedUser.getEmail());
 FiliereService us=new FiliereService();
        List<Filiere> fList;
      fList=us.afficherf();
        for (Filiere fil : fList)
            LoadItemf(fil);    }    

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (event.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (event.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(event.getSource()==btnSignout)
        {
           Helpex.loggedUser = new User();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                this.title.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public void LoadItemf(Filiere f){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemF.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label idLabel = (Label) itemPane.lookup("#id");
        Label nfLabel = (Label) itemPane.lookup("#nom filiere");
                Label descLabel = (Label) itemPane.lookup("#desc");
                    

      idLabel.setText(Integer.parseInt((f.getId())));
        nfLabel.setText(f.getNomF());
         descLabel.setText(f.getDescF());
        pnItems.getChildren().add(itemPane);
    }

    @FXML
    private void filieres(ActionEvent event) {
    }
    
}
