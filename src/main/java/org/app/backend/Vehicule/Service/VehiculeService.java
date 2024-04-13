package org.app.backend.Vehicule.Service;

import jakarta.transaction.Transactional;
import org.app.backend.Vehicule.Model.Vehicule;
import org.app.backend.Vehicule.Rep.VehiculeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {

    public Vehicule findByMatricule(String matricule) {
        return vehiculeRep.findByMatricule(matricule);
    }
@Transactional
    public Vehicule deleteByMatricule(String matricule) {
        return vehiculeRep.deleteByMatricule(matricule);
    }

    public List<Vehicule> findByMatriculeAndModele(String matricule, String modele) {
        return vehiculeRep.findByMatriculeAndModele(matricule, modele);
    }

    public List<Vehicule> findAll() {
        return vehiculeRep.findAll();
    }

    public int addVehicule(Vehicule vehicule) {
        Vehicule existingVehicule = vehiculeRep.findByMatricule(vehicule.getMatricule());
        if (existingVehicule != null) {
            return -1;
        }
        vehiculeRep.save(vehicule);
        return 0;
    }
    @Transactional
    public int editVehicule(String matricule, Vehicule vehicule) {
        Vehicule existingVehicule = vehiculeRep.findByMatricule(matricule);
        if (existingVehicule == null) {
            return -1;
        }
        existingVehicule.setMatricule(vehicule.getMatricule());
        existingVehicule.setNom(vehicule.getNom());
        existingVehicule.setModele(vehicule.getModele());
        existingVehicule.setImage(vehicule.getImage());
        existingVehicule.setEtat(vehicule.isEtat());
        existingVehicule.setStatus(vehicule.getStatus());
        vehiculeRep.save(existingVehicule);
        return 0;
    }
    @Autowired
    private VehiculeRep vehiculeRep;

}
