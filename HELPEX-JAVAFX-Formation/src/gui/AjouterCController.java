/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import entities.Poste;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDCommentaire;
import services.CRUDPoste;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterCController implements Initializable {

 
    @FXML
    private TableView<Poste> table;
    @FXML
    private TableColumn<Poste,String> IDColumn;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableColumn<Poste,String> TITREColumn;
    @FXML
    private TableColumn<Poste,String> DESCRIPTIONColumn;
    @FXML
    private TextArea txtDescriptioncommentaire;
    @FXML
    private Button btnAddcomment;
    @FXML
    private TableView<Commentaire> tablecommentaire;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Commentaire, String> ID2Column;
    @FXML
    private TableColumn<Commentaire, String> DESCRIPTION2Column;
    @FXML
    private TableColumn<Commentaire, String> POSTIDcolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
table();   }    
        int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
       public void table()
      {
          
    Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Poste> postes = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("select id,titre,description,categorie_id FROM `poste`");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Poste st = new Poste();
            st.setId(rs.getInt("id"));
            st.setTitre(rs.getString("titre"));
            st.setDescription(rs.getString("description"));
           
            postes.add(st);
       }
    }
                table.setItems(postes);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                TITREColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getTitre()));
                DESCRIPTIONColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescription()));
               
       }
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                table.setRowFactory(tv -> {
     TableRow<Poste> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtTitre.setText(table.getItems().get(myIndex).getTitre());
           txtDescription.setText(table.getItems().get(myIndex).getDescription());  
           
        }
     });
        return myRow;
                   });
    
    
      }
       

    @FXML
    private void Add(ActionEvent event) {
        String titre,description;
            titre = txtTitre.getText();
            description=txtDescription.getText();
            Poste c = new Poste( titre, description);
            CRUDPoste cu = new CRUDPoste();
            cu.ajouterPoste(c);
             table();
    }
    
     
    

    @FXML
    private void Delete(ActionEvent event) {
         CRUDPoste  rcd = new  CRUDPoste ();
           Poste c= new Poste();   
              c= table.getSelectionModel().getSelectedItem();
              rcd.supprimerPoste(c);
              table();
    }

    @FXML
    private void Update(ActionEvent event) {
          CRUDPoste rc = new CRUDPoste();
        String var1=txtTitre.getText();
        String var2=txtDescription.getText();
       Poste r =new Poste();
        r.setTitre(var1);
      r.setDescription(var2);
        r=table.getSelectionModel().getSelectedItem();
        rc.modifierPoste(r,var1,var2);
       table();
    }

    @FXML
    private void Addcomment(ActionEvent event) {
     
        Poste r =new Poste();
        String var1=txtTitre.getText();
        String var2=txtDescription.getText();
        String var3=txtDescriptioncommentaire.getText();
        r.setTitre(var1);
        r.setDescription(var2);
        r=table.getSelectionModel().getSelectedItem();
        Commentaire c= new Commentaire(var3,r);
        CRUDCommentaire rc = new CRUDCommentaire();
        rc.ajouterCommentaire(c, r);

    }
       
    
}
