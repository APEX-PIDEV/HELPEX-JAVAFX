package Interfaces;

import entites.Accompagnement;


public interface InterfaceAccompagnement {


    //creation
    public void EnvoierAccompagnement(Accompagnement accompagnement);
    //is_valid==true

    public void accepterAccompagnement(Accompagnement accompagnement);
   //supprimer
    public  void retirer_accompagnement (Accompagnement accompagnement);
}
