package home;

import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.OrganisationCRUD;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    public TextField nom;
    public TextField email;
    public TextField desc;
    public TextField numero;
    public TextField document;
    public TextField logo;
    public TextField payment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void AddOrganisation(ActionEvent event) {
        String description;
        String emailOrg;
        String numero_tel;
        String documentOrg;
        String payment_info;
        String nomOrg;
        String logoOrg;
        nomOrg = nom.getText();
        emailOrg = email.getText();
        description = desc.getText();
        numero_tel = numero.getText();
        documentOrg = document.getText();
        payment_info = payment.getText();
        logoOrg = logo.getText();

        Organisation organisation = new Organisation(description, emailOrg, numero_tel, documentOrg, payment_info, nomOrg, logoOrg);
        OrganisationCRUD organisationCRUD=new OrganisationCRUD();
        organisationCRUD.ajouterOrg(organisation);

    }

}
