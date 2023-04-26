package gui;

import com.sun.org.apache.xpath.internal.operations.Or;
import entities.Poste;
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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.control.TextField;
//import jdk.nashorn.internal.parser.JSONParser;
import services.CRUDPoste;
import utils.MyConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class cc implements Initializable {

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
    @FXML
    private Label NumPostes;
    @FXML
    private TextField searchfield;

    public VBox getPnItems() {
        return pnItems;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        for (Poste poste : posteList)
            LoadItem(poste);
        NumPostes.setText(String.valueOf(posteList.size()));
                 pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();

    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
         pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
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
    public void LoadItem(Poste poste){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));

        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label titreLabel = (Label) itemPane.lookup("#titre_poste");
        Label descriptionLabel = (Label) itemPane.lookup("#description_poste");
        Label idLabel = (Label) itemPane.lookup("#id_poste");
        titreLabel.setText(poste.getTitre());
        descriptionLabel.setText(poste.getDescription());
        idLabel.setText(String.valueOf(poste.getId()));
        pnItems.getChildren().add(itemPane);
    }
    @FXML
    private void AjouterInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPoste.fxml"));
        AnchorPane newInterface = loader.load();
        AjouterPosteController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    public void refresh() {

        pnItems.getChildren().clear();
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        for (Poste poste : posteList)
            LoadItem(poste);
    }

    @FXML
    private void Refresh(ActionEvent event) {
        refresh();
    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Api.fxml"));
        AnchorPane newInterface = loader.load();
        ApiController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
        
    }

    @FXML
    private void search(MouseEvent event) {
        CRUDPoste CRUDposte=new CRUDPoste();
        List<Poste> posteList;
        posteList=CRUDposte.afficherPoste();
        pnItems.getChildren().clear();
        for (Poste poste : posteList)
        {
            if (poste.getTitre().contains(searchfield.getText()))
            LoadItem(poste);
        }
    }

}
