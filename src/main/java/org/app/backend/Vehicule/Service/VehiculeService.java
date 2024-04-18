package org.app.backend.Vehicule.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.backend.Vehicule.Model.Vehicule;
import org.app.backend.Vehicule.Rep.VehiculeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    public Vehicule findByMatricule(String matricule) {
        return vehiculeRep.findByMatricule(matricule);
    }


    public List<Vehicule> findByMatriculeAndModele(String matricule, String modele) {
        return vehiculeRep.findByMatriculeAndModele(matricule, modele);
    }

    public List<Vehicule> findAll() {
        return vehiculeRep.findAll();
    }

    public Vehicule createVehicule(Vehicule vehicule) {
        Vehicule existingVehicule = vehiculeRep.findByMatricule(vehicule.getMatricule());
        if (existingVehicule != null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
       return  vehiculeRep.save(vehicule);
    }
    @Transactional
    public Vehicule editVehicule(long id, Vehicule vehicule) {
        Optional<Vehicule> existingVehiculeOptional = vehiculeRep.findById(id);
        if (existingVehiculeOptional.isEmpty()) {
            return null;
        }
        Vehicule existingVehicule = existingVehiculeOptional.get();
        existingVehicule.setMatricule(vehicule.getMatricule());
        existingVehicule.setNom(vehicule.getNom());
        existingVehicule.setModele(vehicule.getModele());
        existingVehicule.setImage(vehicule.getImage());
        existingVehicule.setEtat(vehicule.isEtat());
        existingVehicule.setStatus(vehicule.getStatus());
        Vehicule updatedVehicule = vehiculeRep.save(existingVehicule);
        return updatedVehicule;
    }
    @Transactional
    public Vehicule deleteVehicule(long id) {
        Optional<Vehicule> existingVehiculeOptional = vehiculeRep.findById(id);
        if (existingVehiculeOptional.isEmpty()) {
            return null; // Le véhicule avec l'ID spécifié n'existe pas
        }
        // Le véhicule existe, donc nous pouvons le supprimer
        Vehicule existingVehicule = existingVehiculeOptional.get();
        vehiculeRep.deleteById(id);
        return existingVehicule; // Retourner le véhicule supprimé
    }
    private final VehiculeRep vehiculeRep;

}
