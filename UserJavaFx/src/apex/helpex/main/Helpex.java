/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.main;

import apex.helpex.services.UserService;
import apex.helpex.utils.DB;
import apex.helpex.entities.User;
import java.sql.Date;




/**
 *
 * @author Asus-PC
 */
public class Helpex {
     public static void main(String[] args) {
        //DB db= new DB();
        UserService U= new UserService();
        Date date2 = Date.valueOf("2000-10-11");
        //User pro = new User("pro@example.com", "[\"ROLE_PRO\"]","5555", "mim", "Mo", "Femme", "Gob", "55655853", "fjfj", "fhhf", date2 , "hhhhhhh", 55, 1);

        User client = new User("client@example.com", "","5555", "mim", "Mo", "Femme", "Gob", "55655853", "fjfj", "fhhf", date2 , 1);
        //System.out.println(U.afficherClients());
        U.registerClient(client);
        
    }
}
