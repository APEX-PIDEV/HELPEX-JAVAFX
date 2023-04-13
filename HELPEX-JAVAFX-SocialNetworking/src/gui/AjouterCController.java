/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import entities.Poste;
import java.io.IOException;
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
import javafx.scene.layout.AnchorPane;
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
    private TableView<Commentaire> table;
    @FXML
    private TableColumn<Poste,String> IDColumn;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextArea txtDescriptioncommentaire;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Commentaire, String> DESCRIPTIONcolumn;
    private Poste p;

    public Poste getP() {
        return p;
    }

    public void setP(Poste p) {
        this.p = p;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        try {
         
           table();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
}    
        int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
       public void table() throws IOException
      {
     
            Connection conn= MyConnection.getInstance().getConn();
          ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();
       try
       {
           pst = conn.prepareStatement("select id,description FROM `commentaire`");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Commentaire st = new Commentaire();
            st.setId(rs.getInt("id"));
            st.setDescription(rs.getString("description"));
           
            commentaires.add(st);
       }
    }
                table.setItems(commentaires);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                DESCRIPTIONcolumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDescription()));
               
       }
      
       catch (SQLException ex)
       {
           Logger.getLogger(AjouterCController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
                table.setRowFactory(tv -> {
     TableRow<Commentaire> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtDescriptioncommentaire.setText(table.getItems().get(myIndex).getDescription());  
           
        }
     });
        return myRow;
                   });
    
    
      }

       

    @FXML
    private void Add(ActionEvent event) throws IOException {
        String titre,description;
            description=txtDescriptioncommentaire.getText();
            Commentaire c = new Commentaire(description);
            CRUDCommentaire cu = new CRUDCommentaire();
            cu.ajouterCommentaire(c,p);
             table();
    }
    
     
    

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        CRUDCommentaire  rcd = new  CRUDCommentaire ();
           Commentaire c= new Commentaire();   
              c= table.getSelectionModel().getSelectedItem();
              rcd.supprimerCommentaire(c);
              table();
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
         CRUDCommentaire rc = new CRUDCommentaire();
        String var2=txtDescriptioncommentaire.getText();
       Commentaire r =new Commentaire();
      r.setDescription(var2);
        r=table.getSelectionModel().getSelectedItem();
        rc.modifierCommentaire(r,var2);
       table();
    }

       
    
}
