package services;


import Interfaces.InterfaceTasks;
import entites.Accompagnement;
import entites.Item;
import entites.Tasks;
import entites.User;
import utils.ConnexionJDBC;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TasksService implements InterfaceTasks {



    /////////////enregister les tasks dans accompagnements AddTasks(version2) /////////////////
    @Override
    public void AjouterTask(Tasks tasks ) {

        //here id user
        String requete = "INSERT INTO `tasks`( `titre`, `start_date`, `end_date`, `is_valid`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, tasks.getTitre());
            pst.setDate(2,tasks.getStart_date());
            pst.setDate(3, tasks.getEnd_date());
            pst.setBoolean(4, tasks.isIs_valid());
            int rowsInserted =  pst.executeUpdate();
            System.out.println("Done!");
            if (rowsInserted > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("Inserted row with ID: " + id);
                        tasks.setId(id);
                        AjouterAccompagnement(tasks);
                    }
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void AjouterAccompagnement(Tasks task){
        //here id user & id user_pro
        Accompagnement accompagnement = new Accompagnement();
        accompagnement.setId_task(task.getId());
        String requette = "INSERT INTO `accompagnement`(`task_id`, `user_id`,  `is_accepted`) VALUES (?,?,?)";
        try {
            PreparedStatement pst =ConnexionJDBC.getInstance().getCnx().prepareStatement(requette);
            pst.setInt(1,task.getId());
            pst.setInt(2,10);

            pst.setBoolean(3, false);
            pst.executeUpdate();
            System.out.println("task created and added to the accompagnement");


        } catch (SQLException e) {
            throw new RuntimeException() ;

        }
    }

    @Override
    public void EditerTask(Tasks tasks ,int id ) {
        String req ="UPDATE `tasks` SET `titre`= ?,`start_date`= ?,`end_date`= ?,`is_valid`= ? WHERE id= ?" ;
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(req);
            pst.setString(1, tasks.getTitre());
            pst.setDate(2,tasks.getStart_date());
            pst.setDate(3, tasks.getEnd_date());
            pst.setBoolean(4, tasks.isIs_valid());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Done MODIFICATION TASK !");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void SupprimerTask(int id) throws SQLException {
        String sql = "DELETE FROM Tasks WHERE id= '"+id+"'";

            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx().prepareStatement(sql);
            Statement st = ConnexionJDBC.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            System.out.println("le task :  " +"  "+id+" " +"est supprimer avec succés...");

    }

    @Override
    public ArrayList<Tasks> listerTasks() {
        ArrayList<Tasks> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM tasks";
            Statement st = ConnexionJDBC.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Tasks tasks = new Tasks();
                tasks.setId(rs.getInt(1));
                tasks.setTitre(rs.getString("titre"));
                tasks.setStart_date(rs.getDate("start_date"));
                tasks.setEnd_date(rs.getDate("end_date"));
                tasks.setIs_valid(rs.getBoolean("is_valid"));
                myList.add(tasks);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ArrayList<Tasks> listerTasksofUser(int id_user) {
return null ;
    }

    @Override
    public ArrayList<Tasks> listerTasksofUser_pro(int id_user_pro) {
        return null;
    }

    @Override
    public ArrayList<Item> listeIemsTasks(int id_tasks) {
        return null;
    }
}
