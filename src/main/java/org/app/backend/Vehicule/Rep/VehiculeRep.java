package org.app.backend.Vehicule.Rep;

import org.app.backend.Vehicule.Model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRep extends JpaRepository<Vehicule, Long>{

    Vehicule findByMatricule(String matricule);
    Vehicule deleteByMatricule(String matricule);
    List<Vehicule> findByMatriculeAndModele(String matricule, String modele);


}
