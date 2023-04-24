package GUI.gui_Tasks;

import api.pdfGenerator;
import com.lowagie.text.DocumentException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entites.Accompagnement;
import entites.Tasks;
import entites.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.xhtmlrenderer.pdf.ITextRenderer;
import services.AccompagnementService;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.twilio.Twilio;

import com.twilio.type.PhoneNumber;

import javax.swing.plaf.ButtonUI;

public class AccompagnementButton implements Initializable {


    @FXML
    MenuButton accompagnment_button ;
    @FXML
    Button pdf ;
    @FXML
    Button sms ;

    private User myUser=new User(10) ;
    private User thisUser =new User(8);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAccompagnment_button();


    }

    public void setAccompagnment_button(){
        AccompagnementService accompagnementService = new AccompagnementService();
        List<Tasks> myTasks = accompagnementService.lister_tasks_for_user(myUser.getId()) ;
        //    accompagnment_button.
        for(Tasks task :myTasks){
            int idTask = task.getId();

            MenuItem menuItem = new MenuItem(task.getTitre());
            menuItem.setOnAction(e->{
                System.out.println("bon baya");
                accompagnementService.EnvoierAccompagnementPartie3(new Accompagnement(idTask,false,myUser,thisUser)) ;
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "ACCOMPAGNEMENT ENVOYER !!", ButtonType.OK);
                    a.showAndWait();
                    accompagnment_button.getItems().remove(menuItem);

            });
            accompagnment_button.getItems().add(menuItem);
        }

    }



    public void createpdf(javafx.event.ActionEvent actionEvent)  {


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            //fileChooser.setInitialFileName("example.pdf");
            File outputFile = fileChooser.showSaveDialog(null);
            if (outputFile == null) {
                return; // User canceled the dialog
            }

            try {
                OutputStream outputStream = new FileOutputStream(outputFile);

                ITextRenderer renderer = new ITextRenderer();
                String html ="helloo";
                renderer.setDocumentFromString("hello" );
                renderer.layout();
                renderer.createPDF(outputStream);

                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static final String ACCOUNT_SID = "AC5a8aebca0ead5f90b81f948c9f9c12c2";
        public static final String AUTH_TOKEN = "c1534113c9053a2f99ecdc19f4c8523b";

        public void sendSms(){
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                            new PhoneNumber("+21627938112"), // destination phone number
                            new PhoneNumber("+16205368369"), // your Twilio phone number
                            "Hello from Apex helpex !  you have a new task from me open it ") // message body
                    .create();

            System.out.println("Message SID: " + message.getSid());
        }

}
