/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import entities.Centre;
import services.CRUDCentre;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class Help {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                MyConnection cc =  MyConnection.getInstance()  ;

        Centre c = new Centre("centre1", "mourouj", "centre1@gmail.com", 221133,"www.centre.com");
        CRUDCentre centre1=new  CRUDCentre() ;
        centre1.ajouterCentre(c);
        System.out.println(centre1.affichercentre());
    }
    
}
