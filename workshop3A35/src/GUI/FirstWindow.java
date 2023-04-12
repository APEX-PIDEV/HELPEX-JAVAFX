/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author belkn
 */
public class FirstWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent  root = FXMLLoader.load(getClass().getResource("../GUI/gui_Tasks/GUI_Tasks.fxml"));
            Scene scene = new Scene(root,800,500)
                    ;
            primaryStage.setTitle("titre");
            primaryStage.setScene(scene);

            primaryStage.setResizable(true);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FirstWindow.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
