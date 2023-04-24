package services;


import Interfaces.InterfaceAccompagnement;
import entites.Accompagnement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entites.Tasks;
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

    public void EnvoierAccompagnementPartie3(Accompagnement accompagnement) {
        String req = "UPDATE `accompagnement` SET accompagnement.user_pro_id= ? WHERE " +
                " `task_id`=? and `user_id`=? ";
        PreparedStatement pst = null;
        try {
            pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setInt(1,accompagnement.getUser_pro().getId());
            pst.setInt(2,accompagnement.getId_task());
            pst.setInt(3, accompagnement.getUser().getId());

            pst.executeUpdate();
            System.out.println("accompagnement envoyer!");

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


     public List<Accompagnement> lister_accompagnement_for_admin (){
        List<Accompagnement> myList = new ArrayList<>();
        String sql="SELECT * FROM accompagnement";
         try {
             Statement st = ConnexionJDBC.getInstance().getCnx()
                     .createStatement();
             ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
                 Accompagnement accompagnement =new Accompagnement();
                 accompagnement.setId(rs.getInt(1));
                 accompagnement.setId_task(rs.getInt(2));
                 accompagnement.getUser().setId(rs.getInt(3));
                 accompagnement.getUser().setId(rs.getInt(4));
                 accompagnement.setIs_accepted(rs.getBoolean(5));

                 myList.add(accompagnement);

             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return myList ;

     }
     public  ArrayList<Tasks> lister_tasks_for_user( int myUser){
        //user_id
        ArrayList<Tasks> tasks = new ArrayList<>();
         try {
             Statement stmt = ConnexionJDBC.getInstance().getCnx()
                     .createStatement();
             ResultSet rs = stmt.executeQuery("SELECT  tasks.id, tasks.titre FROM `accompagnement` JOIN tasks on accompagnement.task_id=tasks.id where accompagnement.user_id='"+myUser+"' AND accompagnement.is_accepted=0 AND accompagnement.user_pro_id is null");
             while(rs.next()) {
                 Tasks t = new Tasks();
                 t.setId(rs.getInt(1));
                 t.setTitre(rs.getString(2));
                 tasks.add(t) ;
             }

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
        return  tasks ;

     }

    public List<Accompagnement> lister_accompagnment_for_user(){return null;}
    public List<Accompagnement> lister_accompagnment_for_pro(){return null ;


    }

}
