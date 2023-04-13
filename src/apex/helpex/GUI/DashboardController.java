/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.GUI;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import apex.helpex.entities.User;
import apex.helpex.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private VBox pnItems = null;
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
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserService us=new UserService();
        List<User> usersList;
        usersList=us.afficherProUsers();
        for (User user : usersList)
            LoadItem(user);

    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }
    public void LoadItem(User user){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemUser.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label emailLabel = (Label) itemPane.lookup("#email");
        Label sexeLabel = (Label) itemPane.lookup("#sexe");
                Label adresseLabel = (Label) itemPane.lookup("#adresse");
                        Label numLabel = (Label) itemPane.lookup("#num_tel");
        Label enabledLabel = (Label) itemPane.lookup("#enabled");


        

        
        emailLabel.setText(user.getEmail());
      sexeLabel.setText(user.getSexe());
        adresseLabel.setText(user.getAdresse());
         numLabel.setText(user.getNum_tel());
                 enabledLabel.setText(user.getNum_tel());
        pnItems.getChildren().add(itemPane);
    }
    
    private void refresh() {

        pnItems.getChildren().clear();
        UserService us=new UserService();
                List<User> usersList;

           usersList=us.afficherProUsers();
 for (User user : usersList)
            LoadItem(user);
    }

}
