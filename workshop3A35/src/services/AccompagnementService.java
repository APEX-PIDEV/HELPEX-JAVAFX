package services;


import Interfaces.InterfaceAccompagnement;
import entites.Accompagnement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnexionJDBC;
import utils.MyConnection;

public class AccompagnementService implements InterfaceAccompagnement {


    @Override
    public void EnvoierAccompagnement(Accompagnement accompagnement) {
        String requete = "INSERT INTO `accompagnement`( `task_id`, `user_id`, `user_pro_id`, `is_accepted`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1,667);
            pst.setInt(1,accompagnement.getId_task());
            pst.setInt(2, accompagnement.getUser().getId());
            pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.setBoolean(4, false);
            pst.executeUpdate();
            System.out.println("Done envoie d' accompagnement!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void EnvoierAccompagnementPartie2(Accompagnement accompagnement) {
        String requete = "INSERT INTO `accompagnement`( `task_id`, `user_id`, `user_pro_id`, `is_accepted`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1,667);
            pst.setInt(1,accompagnement.getId_task());
            pst.setInt(2, accompagnement.getUser().getId());
            pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.setBoolean(4, true);
            pst.executeUpdate();
            System.out.println("Done enregistrement  d' accompagnement!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void accepterAccompagnement(Accompagnement accompagnement) {
        String req = "UPDATE `accompagnement` SET `is_accepted`=1 WHERE " +
                " `task_id`=? and `user_id`=? and `user_pro_id`=? ";
        PreparedStatement pst = null;
        try {
            pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1,accompagnement.getId_task());
            pst.setInt(2, accompagnement.getUser().getId());
            pst.setInt(3,accompagnement.getUser_pro().getId());
            pst.executeUpdate();
            System.out.println("accompagnement accepté!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void retirer_accompagnement(Accompagnement accompagnement) {
        String sql = "DELETE FROM accompagnement WHERE  `task_id`= '"+ accompagnement.getId_task()+"' and `user_id`= '"+accompagnement.getUser().getId()+"' and `user_pro_id`='"+accompagnement.getUser_pro().getId()+"' ";
        try {

            Statement st = ConnexionJDBC.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            System.out.println(" accompagnemment supprimer avec succés...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
