package GUI.user_Pro;

import entites.Accompagnement;
import entites.Item;
import entites.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import services.AccompagnementService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AccompagnementUserPro  implements Initializable {
    @FXML
    private TableColumn<?, ?> accepterId;

    @FXML
    private TableView<Accompagnement> accompagnement_Table;

    @FXML
    private TableColumn<?, ?> clientId;
    @FXML
    private Label number ;

    @FXML
    private TableColumn<?, ?> taskCid;

    @FXML
    private HBox hboxAccompagnement;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mesAccompagnments();

    }

    public void mesAccompagnments (){
        AccompagnementService accompagnementService = new AccompagnementService();
        System.out.println(accompagnementService.lister_accompagnment_for_pro(8));
        number.setText(String.valueOf(accompagnementService.lister_accompagnment_for_pro(8).size()));

        ObservableList<Accompagnement> accompagnementObservableList = FXCollections.observableArrayList(accompagnementService.lister_accompagnment_for_pro(8));
        TableColumn<Accompagnement, String> nomColumn = new TableColumn<Accompagnement, String>("User");
        nomColumn.setCellValueFactory(cellFactory-> new SimpleStringProperty(cellFactory.getValue().getUser().getNom()+ "  " +cellFactory.getValue().getUser().getPrenom() + " vous à envoyer une demande d'accompagnement "));


        ////////////
        TableColumn<Accompagnement, Void> buttonColumn = new TableColumn<Accompagnement,Void>("#");
        TableColumn<Accompagnement, Void> buttonColumn1 = new TableColumn<Accompagnement,Void>("#");
        buttonColumn.setCellFactory(param -> new TableCell<Accompagnement,Void>() {
            private final Button AccepterButton = new Button("accepter");

            private final Pane pane = new Pane(AccepterButton);


            {
                AccepterButton.setOnAction(event -> {
                    Accompagnement accompagnement = getTableView().getItems().get(getIndex());

                    accompagnementService.accepterAccompagnement(accompagnement);
                    mesAccompagnments();
                });


            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
        buttonColumn1.setCellFactory(param -> new TableCell<Accompagnement,Void>() {

            private final Button RejeterButton = new Button("rejeter");
            private final Pane pane = new Pane(
                     RejeterButton);


            {

                RejeterButton.setOnAction(event -> {
                    Accompagnement accompagnement = getTableView().getItems().get(getIndex());
                    System.out.println("rejeter"+accompagnement.getId());
                    Accompagnement accompagnement1 = getTableView().getItems().get(getIndex());

                    accompagnementService.retirer_accompagnement(accompagnement1);
                    //hboxAccompagnement.getChildren().remove(accompagnement_Table);
                   // pane.getChildren().add(accompagnement_Table);
                    mesAccompagnments();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });


        accompagnement_Table.getColumns().addAll(nomColumn,buttonColumn,buttonColumn1);
        accompagnement_Table.setItems(accompagnementObservableList);
    }
}
