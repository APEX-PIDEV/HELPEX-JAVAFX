package gui;

import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.OrganisationCRUD;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    public TextField nom;
    public TextField email;
    public TextField desc;
    public TextField numero;
    public TextField document;
    public TextField logo;
    public TextField payment;
    public Button Formbtn;
    Organisation organisation = null;
    private boolean update;
    int OrganisationId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
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
        if(update){
            OrganisationCRUD organisationCRUD=new OrganisationCRUD();
            organisationCRUD.modifierOrg(OrganisationId,description,emailOrg,numero_tel,documentOrg,payment_info,nomOrg,logoOrg);
        }else {
            Organisation organisation = new Organisation(description, emailOrg, numero_tel, documentOrg, payment_info, nomOrg, logoOrg);
            OrganisationCRUD organisationCRUD=new OrganisationCRUD();
            organisationCRUD.ajouterOrg(organisation);
        }


    }
    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField(Organisation organisation) {
        OrganisationId = organisation.getId();
        nom.setText(organisation.getNom());
        email.setText(organisation.getEmail());
        desc.setText(organisation.getDescription());
        numero.setText(organisation.getNumero_tel());
        document.setText(organisation.getDocument());
        payment.setText(organisation.getPayment_info());
        logo.setText(organisation.getLogo());
    }

}
