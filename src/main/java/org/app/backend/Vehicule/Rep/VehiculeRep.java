package org.app.backend.Vehicule.Rep;

import org.app.backend.Vehicule.Model.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRep extends JpaRepository<Vehicule, Long>{

    Vehicule findByMatricule(String matricule);
    List<Vehicule> findByMatriculeAndModele(String matricule, String modele);
    List<Vehicule>findAllByNomContainingOrMatriculeContainingOrModeleContaining(String re,String r,String m);


    Page<Vehicule>findAllByNomContainingOrMatriculeContainingOrModeleContaining(String re, String r, String m,Pageable p);




}
