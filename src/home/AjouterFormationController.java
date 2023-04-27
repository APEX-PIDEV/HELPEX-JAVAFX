/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import apex.helpex.utils.JavaMail;
import entities.Centre;
import entities.Formation;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDCentre;
import services.CRUDFormation;
import utils.MyConnection;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.HostServices;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterFormationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtNomF;
    @FXML
    private TextField txtcouF;
    @FXML
    private TextField txtnombredeplaceF;
    @FXML
    private TextField txtdureeF;
    @FXML
    private TextField txtdescriptionF;
    @FXML
    private Button btnAddF;
    @FXML
    private Button btnDeleteF;
    @FXML
    private Button btnUpdateF;
    @FXML
    public TableView<Formation> tableF;
 
    @FXML
    public TableColumn<Formation,String> IDColumn;
    @FXML
    public TableColumn<Formation,String> NOMColumnF;
    @FXML
    public TableColumn<Formation,String> DESCRIPTIONColumn;
    @FXML
    public TableColumn<Formation,String> COUTColumn;
    @FXML
    public TableColumn<Formation,String> PLACEColumn;
    @FXML
    public TableColumn<Formation,String> DUREEColumn;
    @FXML
    private TextField txt_id_centre;
    
    private Centre F;
    @FXML
    private Button btnimprimer;
    @FXML
    private Button inscribtn;
    @FXML
    private Button btnlocalisation;
    @FXML
    private TextField rechercher;

    public Centre getF() {
        return F;
    }

    public void setF(Centre F) {
        this.F = F;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   }    

    @FXML
    
    
    private void Add(ActionEvent event) {
       String nomFormation; String descriptionFormation; float coutFormation; int NombreDePlace; String duree;int id_centre;
            nomFormation = txtNomF.getText();
            descriptionFormation=txtdescriptionF.getText();
            duree=txtdureeF.getText();
                        NombreDePlace=Integer.parseInt(txtnombredeplaceF.getText()) ;
                                              //  coutFormation=Integer.parseInt(txtcouF.getText()) ;
                                            //  id_centre=Integer.parseInt(txt_id_centre.getText());
                                              Centre centre_jointure=new Centre();
                                              CRUDCentre  crudcentre=new CRUDCentre();
                                            //  System.out.println(id_centre);
                                              centre_jointure= crudcentre.findbyid(F.getId());
                                             System.out.println(centre_jointure); 
                                              


           // siteWebCentre=txtSiteweb.getText();
            
            Formation c = new Formation( nomFormation, descriptionFormation,5, NombreDePlace, duree,centre_jointure);
            CRUDFormation cu = new CRUDFormation();
            cu.ajouterFormation(c);
             table();
             
    }

  

    @FXML
    //    public Formation(String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {
//    public void modifierFormation(Formation f, String nomFormation, String descriptionFormation, float coutFormation, int NombreDePlace, String duree) {

    private void Update(ActionEvent event) {
    CRUDFormation rc = new CRUDFormation();
        String var1=txtNomF.getText();
        String var2=txtdescriptionF.getText();
        String var3=txtcouF.getText();
//        float var3float=Integer.parseInt(var3);
        String var4=txtnombredeplaceF.getText();
//        int var5=Integer.parseInt(var4);
        String var6=txtdureeF.getText();
       Formation r =new Formation();
        r.setNomFormation(var1);
      r.setDescriptionFormation(var2);
      //r.getCoutFormation(var3);
    //  r.getNombreDePlace(var5);
      r.setDuree(var6);
        r=tableF.getSelectionModel().getSelectedItem();
        rc.modifierFormation(r,var1,var2,5,2,var6);
       table();
      // rc.generatePDF(r);
    
    }
    
    int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      ObservableList<Formation> formations = FXCollections.observableArrayList();

     
      public void table()
      {
             try
       {
           Connection conn= MyConnection.getInstance().getConn();
          //  formations = FXCollections.observableArrayList();

           pst = conn.prepareStatement("SELECT `id`, `nom_formation`, `description_formation`, `cout_formation`, `nombre_de_place`, `duree` FROM `formation`WHERE id_centre_id='"+F.getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Formation st = new Formation();
            st.setId(rs.getInt("id"));
          
            st.setNomFormation(rs.getString("nom_formation"));
            st.setDescriptionFormation(rs.getString("description_formation"));
            st.setCoutFormation(rs.getFloat("cout_formation"));
                        st.setNombreDePlace(rs.getInt("nombre_de_place"));
                        st.setDuree(rs.getString("duree"));
           
            formations.add(st);
       }
      
    
           
                tableF.setItems(formations);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
       IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

NOMColumnF.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomFormation()));
           DESCRIPTIONColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescriptionFormation()));
               // newInterfaceController.COUTColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCoutFormation()));
                                 COUTColumn.setCellValueFactory(new PropertyValueFactory<>("cout_formation"));

               DUREEColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDuree()));
          //    TELEPHONEColumn.setCellValueFactory(f -> f.getValue().courseProperty());
                  PLACEColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));
               
       }
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                tableF.setRowFactory(tv -> {
     TableRow<Formation> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  tableF.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(tableF.getItems().get(myIndex).getId()));
           txtNomF.setText(tableF.getItems().get(myIndex).getNomFormation());
        //   txtTelephone.setText(table.getItems().get(myIndex));
           txtdescriptionF.setText(tableF.getItems().get(myIndex).getDescriptionFormation());
           txtdureeF.setText(tableF.getItems().get(myIndex).getDuree());
                          //   txtnombredeplaceF.setCellValueFactory(new PropertyValueFactory<>("nombre_de_place"));

           //txtEmail.setText(tableF.getItems().get(myIndex).getEmailCentre());

                          
                        
                          
        }
     });
     
        return myRow;
                   });
    
    
      }
      
        @FXML
    private void Delete(ActionEvent event) {
     CRUDFormation  rcd = new  CRUDFormation();
           Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
              System.out.println(c);
              rcd.supprimerFormation(c);
              table();
    }

    @FXML
    private void imprimerF(ActionEvent event) {
             CRUDFormation  rcd = new  CRUDFormation();
        Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
              rcd.generatePDF(c);
         
    }

    @FXML
    private void inscriptionF(ActionEvent event) {
          Formation c= new Formation();
              c= tableF.getSelectionModel().getSelectedItem();
                try {
                //send email to emailField.getText()
                JavaMail.sendMail("ahmedbelhajhassen22@gmail.com",c);
            } catch (Exception ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void localisationF(ActionEvent event) {
        Hyperlink link = new Hyperlink();
link.setText("https://www.google.com/maps/");


    }
    
   
    
}
