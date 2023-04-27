package GUI.user_Pro;

import entites.DataSingleton;
import entites.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ItemService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemProUser implements Initializable {
    @FXML
    private Label accomswitch;

    @FXML
    private VBox vbox;
    private StringProperty firstNameString = new SimpleStringProperty();
    DataSingleton dataSingleton = DataSingleton.instance ;

    public ItemProUser(){}
    public ItemProUser (String s) {
        firstNameString.set(s);
    }

    public void setStringValue(String value) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listerItems();
    }
    public  void listerItems (){

        ItemService itemService = new ItemService();
        ArrayList<Item>items=itemService.listerItemById(dataSingleton.getValue());
        for (int i = 0; i <items.size(); i++) {
            System.out.println("thheeeeeeeee"+items.size());
            if (items.size()==0){
                Label label = new Label(" pas de items attribués :) ") ;
                vbox.getChildren().add(label);
            }else {
            CheckBox checkBox = new CheckBox(items.get(i).getTitre()+"                               "+items.get(i).getTime()); // create checkbox
            int finalI = i;
            int finalI1 = i;
            checkBox.setOnMouseClicked(event -> {checkBox.setSelected(checkBox.isSelected());
                itemService.completeItem(items.get(finalI1).getId());
            vbox.getChildren().remove(checkBox);

            });

            vbox.getChildren().add(checkBox); // add checkbox to VBox

        }}
}



    @FXML
    public void switchingAccompagnemment()  {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accompagnement_UserPro.fxml"));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            Stage currentStage = (Stage) accomswitch.getScene().getWindow();
            currentStage.setWidth(1350);
            currentStage.setHeight(890);
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }}




}
