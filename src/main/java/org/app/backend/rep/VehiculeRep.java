package org.app.backend.rep;

import org.app.backend.model.Vehicule;
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
