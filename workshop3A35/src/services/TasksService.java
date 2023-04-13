package services;


import Interfaces.InterfaceTasks;
import entites.Item;
import entites.Tasks;
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
        String requete = "INSERT INTO `tasks`( `titre`, `start_date`, `end_date`, `is_valid`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = ConnexionJDBC.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, tasks.getTitre());
            pst.setDate(2,tasks.getStart_date());
            pst.setDate(3, tasks.getEnd_date());
            pst.setBoolean(4, tasks.isIs_valid());
            pst.executeUpdate();
            System.out.println("Done!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return null;
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
