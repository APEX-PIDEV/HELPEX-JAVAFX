/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apex.helpex.services;

import apex.helpex.entities.User;
import apex.helpex.interfaces.UserInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import apex.helpex.main.Helpex;
import apex.helpex.utils.DB;
import apex.helpex.utils.BcryptHasher;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Asus-PC
 */
public class UserService implements UserInterface<User>{

    Connection con;
     Statement stm;
         BcryptHasher hasher;


    public UserService() {
        con = DB.getInstance().getCon();
                hasher = new BcryptHasher();

    }
    
    @Override
    public List<User> afficherProUsers() {
        List<User> listUsers= new ArrayList<>();
        try{
String req1 = "Select * from `user` WHERE roles='[\"ROLE_PRO\"]'" ;        stm = con.createStatement();   
        ResultSet result = stm.executeQuery(req1);
        while (result.next()) {
            User p = new User(result.getInt(1), result.getString("email"), result.getString("roles"),result.getString("nom"),result.getString("prenom"),result.getString("sexe"), result.getString("adresse"),result.getString("num_tel"),result.getString("pdp"),result.getString("bio"),result.getDate("date_naissance"),result.getString("diplome"), result.getInt("tarif"),result.getInt("is_enabled"));
            listUsers.add(p);
        }
        }
        catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return listUsers;
    }
    
     @Override
    public List<User> afficherClients() {
        List<User> listUsers= new ArrayList<>();
        try{
String req1 = "Select * from `user` WHERE roles='[\"ROLE_USER\"]'" ;        stm = con.createStatement();   
        ResultSet result = stm.executeQuery(req1);
        while (result.next()) {
            User p = new User(result.getInt(1), result.getString("email"), result.getString("roles"),result.getString("nom"),result.getString("prenom"),result.getString("sexe"), result.getString("adresse"),result.getString("num_tel"),result.getString("pdp"),result.getString("bio"),result.getDate("date_naissance"),result.getInt("is_enabled"));
            listUsers.add(p);
        }
        }
        catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
        return listUsers;
    }
    

    @Override
    public void delete(User u) {
        try{
String req = "DELETE FROM `user` WHERE `email` = \"" + u.getEmail() + "\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);   }
    catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    
    public void deleteByEmail(String email) throws SQLException {
        String req = "DELETE FROM `user` WHERE `email` = \"" + email + "\"; ";
        stm = con.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public void updateProfile(User u) {
        try{
 String req = "UPDATE `user` SET "
                + " `nom` = '" + u.getNom() + "', "
                + " `prenom` = '" + u.getPrenom() + "', "
                + " `adresse` = '" + u.getAdresse() + "', "
                + " `num_tel` = '" + u.getNum_tel() + "', "
                + " `pdp` = '" + u.getPdp() + "', "
                + " `bio` = '" + u.getBio() + "' "
              
         
                + "WHERE `email` = '" + u.getEmail() + "'";
        if ("[\"ROLE_PRO\"]".equals(u.getRoles())) {
            req = "UPDATE `user` SET "
                    + " `nom` = '" + u.getNom() + "', "
                + " `prenom` = '" + u.getPrenom() + "', "
                + " `adresse` = '" + u.getAdresse() + "', "
                + " `num_tel` = '" + u.getNum_tel() + "', "
                + " `pdp` = '" + u.getPdp() + "', "
                + " `bio` = '" + u.getBio() + "', "
                + " `tarif` = '" + u.getTarif() + "'"
                     + "WHERE `email` = '" + u.getEmail() + "'";
        }
        stm = con.createStatement();
        stm.executeUpdate(req); }
    catch(SQLException ex){
        System.err.println(ex.getMessage());
        }}

    @Override
    public void registerPro(User u) {
 try{
       String req = "INSERT INTO `user` (`email`, `roles`, `password`, `nom`, `prenom`, `sexe`, `adresse`, `num_tel`, `pdp`, `bio`, `date_naissance`, `diplome`, `tarif`, `is_enabled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement pstm = con.prepareStatement(req);
     pstm.setString(1, u.getEmail());
     pstm.setString(2, "[\"ROLE_PRO\"]");
     pstm.setString(3, hasher.hash(u.getPassword()));
     pstm.setString(4, u.getNom());
     pstm.setString(5, u.getPrenom());
     pstm.setString(6, u.getSexe());
     pstm.setString(7, u.getAdresse());
     pstm.setString(8, u.getNum_tel());
     pstm.setString(9, u.getPdp());
     pstm.setString(10, u.getBio());
     pstm.setDate(11, u.getDate_naissance());
     pstm.setString(12, u.getDiplome());
     pstm.setInt(13, 0);
     pstm.setInt(14, 1);
     pstm.executeUpdate();

    }    
    
     catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    
    
 
 
}

    @Override
    public void registerClient(User u) {

 try{
       String req = "INSERT INTO `user` (`email`, `roles`, `password`, `nom`, `prenom`, `sexe`, `adresse`, `num_tel`, `pdp`, `bio`, `date_naissance`, `is_enabled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
     PreparedStatement pstm = con.prepareStatement(req);
     pstm.setString(1, u.getEmail());
     pstm.setString(2, "[\"ROLE_USER\"]");
     pstm.setString(3, hasher.hash(u.getPassword()));
     pstm.setString(4, u.getNom());
     pstm.setString(5, u.getPrenom());
     pstm.setString(6, u.getSexe());
     pstm.setString(7, u.getAdresse());
     pstm.setString(8, u.getNum_tel());
     pstm.setString(9, u.getPdp());
     pstm.setString(10, u.getBio());
     pstm.setDate(11, u.getDate_naissance());
     pstm.setInt(12, 1);
     pstm.executeUpdate();

    }    
    
     catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    
    public boolean isEmailTaken(String email) {
        try {
            String req = "select count(*) from `user` WHERE `email` = \"" + email + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            result.next();
            return result.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    
    
     public boolean login(String email, String password) {
        try {
            String req = "SELECT * FROM `user` WHERE `email` = \"" + email + "\"; ";
            stm = con.createStatement();
            ResultSet result = stm.executeQuery(req);
            User user = new User();
            while (result.next()) {
                user = new User(result.getInt(1), result.getString("email"), result.getString("roles"), result.getString("password"),result.getInt("is_enabled"));
            }
            if (hasher.checkPassword(user.getPassword(), password)) {
                Helpex.loggedUser = user;
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
}
