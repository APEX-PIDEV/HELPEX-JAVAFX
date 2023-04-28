/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import entities.Produit;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CrudProduits;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author FaroukDev
 */
public class ProduitsCntroller implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TextArea txtNomProduit;
    @FXML
    private TextArea txtEtatProduit;
    @FXML
    private TextArea txtPrixProduit;
    @FXML
    private TextArea txtAuthorisationProduit;
    @FXML
    public TableView<Produit> table;
    @FXML
    public TableColumn<Produit, String> NomProduitColumn;
    @FXML
    public TableColumn<Produit, String> EtatProduitColumn;
    @FXML
    public TableColumn<Produit, String> PrixProduitColumn;
    @FXML
    private TableColumn<Produit, String> CreatedAtColumn;
    @FXML
    private TableColumn<Produit, String> UpdatedAtColumn;
    @FXML
    private TableColumn<Produit, Boolean> AuthorisationColumn;
    @FXML
    private Label NumPostes;
    @FXML
    private Button btnAdaa;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    
    private Produit C;
    
    int myIndex;
    PreparedStatement pst;
      Connection con;
      int id;
      
      ObservableList<Produit> produits = FXCollections.observableArrayList();
    @FXML
    private TextField searchfieldProd;
    @FXML
    private Button StatProd;
    
    
       public Produit getC() {
        return C;
    }

    public void setC(Produit c) {
        this.C = c;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //table();
        //advanced_search();
        
    }   
    
        public void table() 
      {
          
         // MyConnection conn= MyConnection.getInstance();
          
         //CrudProduits ProduitList = new CrudProduits( conn);
         
          
           
          
          //for (Produit p : ProduitList.getProduitByCatProduit(C.getCategoryProduit())) {
          /*for (Produit p : ProduitList.getAllProduit()) {
              produits.add(p);
          }*/
         
                  try
       {
           Connection conn= MyConnection.getInstance().getConn();
           ObservableList<Produit> produits = FXCollections.observableArrayList();
            PreparedStatement pst;
           pst = conn.prepareStatement("select nom_produit,etat_produit,prix_produit FROM produits WHERE categorie_produit_id='"+C.getCategoryProduit().getId()+"'");  
           ResultSet rs = pst.executeQuery();
        while (rs.next())
        {
            Produit st = new Produit();
            st.setNomProduit(rs.getString("nom_produit"));
            st.setEtatproduit(rs.getString("etat_produit"));
            st.setPrixProduit(rs.getString("prix_produit"));
           
            produits.add(st);
       }
      
    
           
                table.setItems(produits);
                //IDColumn.setCellValueFactory(f -> f.getValue().idProperty());
              //  IDColumn.setCellValueFactory(f -> new ReadOnlyIntegerWrapper(f.getValue().getId()).asObject());

              NomProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNomProduit()));
                EtatProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getEtatproduit()));
                  PrixProduitColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getPrixProduit()));
                  
               
       }
      
     
      
       catch (SQLException ex)
       {
           Logger.getLogger(ProduitsCntroller.class.getName()).log(Level.SEVERE, null, ex);
       }
                  
          
          table.setRowFactory(tv -> {
     TableRow<Produit> myRow = new TableRow<>();
     myRow.setOnMouseClicked (event ->
     {
        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
        {
           myIndex =  table.getSelectionModel().getSelectedIndex();
           id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
           txtNomProduit.setText(table.getItems().get(myIndex).getNomProduit());
        //   txtTelephone.setText(table.getItems().get(myIndex));
           txtEtatProduit.setText(table.getItems().get(myIndex).getEtatproduit());
           txtPrixProduit.setText(table.getItems().get(myIndex).getPrixProduit());
           txtAuthorisationProduit.setText(Boolean.toString(table.getItems().get(myIndex).isAuthorization()));

                          
                        
                          
        }
     });
        return myRow;
                   });

    
     //advanced_search();
      }
        
        

    @FXML
    private void Add(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        Connection con = MyConnection.getInstance().getConn();
        CrudProduits  rcd = new  CrudProduits((MyConnection)con);
           Produit c= new Produit();
              c= table.getSelectionModel().getSelectedItem();
              rcd.deleteProduite(c.getId());
              table();
    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        
        CrudProduits  rcd = new  CrudProduits();
        String var1=txtNomProduit.getText();
        String var2=txtEtatProduit.getText();
        String var3=txtPrixProduit.getText();
        String var4=txtAuthorisationProduit.getText();
        Boolean var5=Boolean.parseBoolean(var4);
        
       Produit r =new Produit();
       r= table.getSelectionModel().getSelectedItem();
       //Produit rtest =new Produit();
       //rtest = r ;
        //System.out.println("Before update = "+rtest);
        r.setNomProduit(var1);
      r.setEtatproduit(var2);
      r.setPrixProduit(var3);
      r.setAuthorization(var5);
        //r=table.getSelectionModel().getSelectedItem();
        rcd.updateProduit(r);
       table();
    }
    
    public void advanced_search() {
        FilteredList<Produit> filteredData = new FilteredList<>(produits, b -> true);
        searchfieldProd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Produit.getNomProduit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else return Produit.getEtatproduit().toLowerCase().contains(lowerCaseFilter); // Filter matches last name.
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);


        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }
    
    @FXML
    public void Stat (ActionEvent event){
        FXMLLoader loader1 = new FXMLLoader ();
        loader1.setLocation(getClass().getResource("statProd.fxml"));
        try {
            loader1.load();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }




        Parent parent = loader1.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    
    
    
    
}
