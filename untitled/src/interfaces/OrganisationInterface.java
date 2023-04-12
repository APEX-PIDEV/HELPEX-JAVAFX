package interfaces;

import entities.Organisation;

import java.util.List;

public interface OrganisationInterface {
    void ajouterOrg(Organisation organisation);
    void modifierOrg(Organisation organisation, String description, String email, String numero_tel, String document, String payment_info, String nom, String logo);
    void supprimerOrg(Organisation organisation);
    List<Organisation> afficherOrg();
}
