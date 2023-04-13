/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Commentaire;
import entities.Poste;
import interfaces.InterfaceCommentaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyConnection;


/**
 *
 * @author USER
 */
public class CRUDCommentaire implements InterfaceCommentaire{
    Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    @Override
    public void ajouterCommentaire(Commentaire c,Poste P) {
         try {
                    String req="INSERT INTO `commentaire`(`description`, `poste_id`) VALUES ('"+c.getDescription()+"','"+P.getId()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("commentaire ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("commentaire non ajouter");        }
    }

    @Override
    public void modifierCommentaire(Commentaire c, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCommentaire(Commentaire c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
   

    
    
}
