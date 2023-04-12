package home;

import com.sun.org.apache.xpath.internal.operations.Or;
import entities.Organisation;
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
import services.OrganisationCRUD;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        List<Organisation> organisationList;
        organisationList=organisationCRUD.afficherOrg();
        for (Organisation organisation : organisationList)
            LoadItem(organisation);

    }


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
    public void LoadItem(Organisation organisation){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
        Pane itemPane = null;
        try {
            itemPane = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        Label nameLabel = (Label) itemPane.lookup("#nom_org");
        Label emailLabel = (Label) itemPane.lookup("#email_organisation");
        Label numeroLabel = (Label) itemPane.lookup("#num_tel_organisation");
        Label docLabel = (Label) itemPane.lookup("#document_organisation");
        Label logoLabel = (Label) itemPane.lookup("#logo_org");


        nameLabel.setText(organisation.getNom());
        emailLabel.setText(organisation.getEmail());
        numeroLabel.setText(organisation.getNumero_tel());
        docLabel.setText(organisation.getDocument());
        logoLabel.setText(organisation.getLogo());
        pnItems.getChildren().add(itemPane);
    }
    @FXML
    private void AjouterInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
        AnchorPane newInterface = loader.load();
        FormController newInterfaceController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(newInterface));
        stage.setOnHidden((event1) -> refresh());
        stage.show();
    }
    private void refresh() {

        pnItems.getChildren().clear();
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        List<Organisation> organisationList;
        organisationList=organisationCRUD.afficherOrg();
        for (Organisation organisation : organisationList)
            LoadItem(organisation);
    }

}
