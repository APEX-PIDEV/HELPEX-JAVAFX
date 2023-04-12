package Interfaces;


import entites.Item;
import entites.Tasks;
import java.util.ArrayList;

public interface InterfaceTasks {

    public void AjouterTask(Tasks tasks) ;
    public  void EditerTask( Tasks tasks) ;
    public  void SupprimerTask(int id);
  // for admin
    public ArrayList<Tasks> listerTasks ();
    // for user
    public ArrayList<Tasks> listerTasksofUser (int id_user);
    // for pro user
    public ArrayList<Tasks> listerTasksofUser_pro ( int id_user_pro);


    public  ArrayList <Item> listeIemsTasks (int id_tasks);




}
