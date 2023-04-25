package GUI.user_Pro;

import entites.Tasks;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.TasksService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MesTasksPro  implements Initializable {
    @FXML
    private GridPane mesTasksGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listerTasks();

    }
    public void listerTasks(){
        TasksService tasksService = new TasksService() ;
        ArrayList<Tasks> tasks = tasksService.listerTasks();
        for (int i = 0; i < tasks.size(); i++){
            Image image ;
            Label title = null;
            if (tasks.get(i).isIs_valid()){
                image  = new Image("images/folderyes.png");


            }
            else {
                image  = new Image("images/folderno.png");
            }

            title = new Label(tasks.get(i).getTitre());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            title.setGraphic(imageView);

            //////////////event////////////
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Editer");
            MenuItem deleteItem = new MenuItem("supprimer");

            int finalI1 = i;


            Label finalTitle = title;

            ///////////tootltip////////////
            Tooltip tooltip = new Tooltip(tasks.get(i).getStart_date()+"/"+tasks.get(i).getEnd_date());
            Tooltip.install(finalTitle, tooltip);

            ////////////tooltip///////////
            editItem.setOnAction(event -> {
                MakeDialog(tasks.get(finalI1), finalTitle)  ;          });
            int finalI = i;


            deleteItem.setOnAction(event -> {
                System.out.println(tasks.get(finalI).getId());
                try {
                    tasksService.SupprimerTask(tasks.get(finalI).getId());
                } catch (SQLException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "task contient des items , supprimer tous les items pour pouvoir le supprimer!!", ButtonType.OK);
                    a.showAndWait();
                    throw new RuntimeException(e);
                }


                gridBox.getChildren().clear();
                gridBox.layout();
                gridBox.requestLayout();
                listerTasks();

            });



            contextMenu.getItems().addAll(editItem,deleteItem);

// Attach the context menu to the label
            title.setContextMenu(contextMenu);


            /////////////event////////////
            gridBox.add(title, i % 3, i / 3);
        }



// set the properties of the grid pane
        gridBox.setHgap(10);
        gridBox.setVgap(10);

    }
}
