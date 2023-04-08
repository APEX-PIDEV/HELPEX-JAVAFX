/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Centre;
import interfaces.InterfaceCentre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CRUDCentre implements InterfaceCentre{
    Statement ste;
    Connection conn= MyConnection.getInstance().getConn();
    

    @Override
    public void ajouterCentre(Centre c) {
        try {
                    String req="INSERT INTO `centre`(`nomCentre`, `adresseCentre`, `emailCentre`, `telephoneCentre`, `siteWebCentre`) VALUES ('"+c.getNomCentre()+"','"+c.getAdresseCentre()+"','"+c.getEmailCentre()+"','"+c.getTelephoneCentre()+"','"+c.getSiteWebCentre()+"')";

            ste=conn.createStatement();
                ste.executeUpdate(req);

            System.out.println("centre ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("centre non ajouter");        }
    }

    @Override
    public void ajouterCentre2(Centre c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierCentre(Centre c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerCentre(Centre c) {
         try {
        String req = "DELETE FROM centre WHERE id='"+c.getId()+"'";
        ste=conn.createStatement();
        ste.executeUpdate(req);
        System.out.println("centre supprimé avec succès");
    } catch (SQLException ex) {
        System.out.println("erreur lors de la suppression du centre");
    }
    }

    @Override
    public List<Centre> affichercentre() {
List<Centre> list=new ArrayList<>();


String req="SELECT * FROM `centre` "; 
Statement ste;
        try {
            
            ste = conn.createStatement();
                    ResultSet RS=ste.executeQuery(req);
                    while(RS.next()){
                    Centre c =new Centre();
                    c.setId(RS.getInt(1));
                    c.setNomCentre(RS.getString("nomCentre"));
                                        c.setAdresseCentre(RS.getString("adresseCentre"));

                                                            c.setEmailCentre(RS.getString("emailCentre"));

                                                          c.setTelephoneCentre(RS.getInt("telephoneCentre"));
                                        c.setSiteWebCentre(RS.getString("siteWebCentre"));
list.add(c);

                    }
            System.out.println("affichage");
        } catch (SQLException ex) {
            System.out.println("erreur d afficahge");        }
return list;
    }
    
    
}
