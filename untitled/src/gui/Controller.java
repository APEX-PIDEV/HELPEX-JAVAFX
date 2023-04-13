package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Organisation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.OrganisationCRUD;
import utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    public TableView<Organisation> Organisations;
    public TableColumn<Organisation, Object> Nomorg;
    public TableColumn<Organisation, Object> EmailOrg;

    public TableColumn<Organisation, Object> PayOrg;

    public TableColumn<Organisation, Object> LogoOrg;
    public TableColumn<Organisation, String> ActionOrg;
    public TableColumn<Organisation, Object> NumeroTelOrg;
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

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Organisation organisation = null ;

    ObservableList<Organisation>  organisationObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    @FXML
    private void AddOrgView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Form.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.setOnHidden((event1) -> refreshTable());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

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
   @FXML
   private void refreshTable() {
       organisationObservableList.clear();
       try {
           query = "SELECT * FROM `organisation`";
           preparedStatement = connection.prepareStatement(query);
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               organisationObservableList.add(new Organisation(
                       resultSet.getInt("id"),
                       resultSet.getString("description_organisation"),
                       resultSet.getString("email_organisation"),
                       resultSet.getString("num_tel_organisation"),
                       resultSet.getString("document_organisation"),
                       resultSet.getString("payment_info"),
                       resultSet.getString("nom_org"),
                       resultSet.getString("logo_org")
                       )
               );
               Organisations.setItems(organisationObservableList);

           }


       } catch (SQLException ex) {
           Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

    private void loadData() {

        connection = MyConnection.getInstance().getConn();
        refreshTable();

        Nomorg.setCellValueFactory(new PropertyValueFactory<>("nom"));
        EmailOrg.setCellValueFactory(new PropertyValueFactory<>("email"));
        LogoOrg.setCellValueFactory(new PropertyValueFactory<>("logo"));
        NumeroTelOrg.setCellValueFactory(new PropertyValueFactory<>("numero_tel"));
        PayOrg.setCellValueFactory(new PropertyValueFactory<>("payment_info"));

        Callback<TableColumn<Organisation, String>, TableCell<Organisation, String>> cellFoctory = (TableColumn<Organisation, String> param) -> {
            final TableCell<Organisation, String> cell = new TableCell<Organisation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                organisation = Organisations.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `organisation` WHERE id  ="+organisation.getId();
                                connection = MyConnection.getInstance().getConn();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            organisation = Organisations.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Form.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            FormController addOrganisationController = loader.getController();
                            addOrganisationController.setUpdate(true);
                            addOrganisationController.setTextField(organisation);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setOnHidden((event1) -> refreshTable());
                            stage.show();

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ActionOrg.setCellFactory(cellFoctory);
        Organisations.setItems(organisationObservableList);


    }


}
