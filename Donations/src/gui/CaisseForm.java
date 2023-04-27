package gui;

import entities.CaisseOrganisation;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CaisseOrganisationCRUD;

import java.net.URL;
import java.util.ResourceBundle;

public class CaisseForm implements Initializable {
    public TextArea CaisseDesc;
    public TextField CaisseGoal;
    public Button Formbtn;

    int organisationId;
    int caisseId;

    private boolean update;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public void AddCaisseOrganisation(ActionEvent event){

        String description;
        float goal;
        goal = Float.parseFloat(CaisseGoal.getText());
        description = CaisseDesc.getText();
        if(update){
            CaisseOrganisationCRUD caisseOrganisationCRUD=new CaisseOrganisationCRUD();
            caisseOrganisationCRUD.modifierCaisse(caisseId,goal,description);
        }else {
            CaisseOrganisation caisseOrganisation = new CaisseOrganisation(organisationId,0,goal,description);
            CaisseOrganisationCRUD caisseOrganisationCRUD=new CaisseOrganisationCRUD();
            caisseOrganisationCRUD.ajouterCaisse(caisseOrganisation);
        }

    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
    }

    void setTextField(CaisseOrganisation caisseOrganisation,int organisationId,int caisseId) {
        this.organisationId=organisationId;
        this.caisseId=caisseId;
        CaisseGoal.setText(String.valueOf(caisseOrganisation.getGoal()));
        CaisseDesc.setText(caisseOrganisation.getDescription());

        if(update){
            Formbtn.setText("Modifier");
        }else
            Formbtn.setText("Ajouter");
    }
}
