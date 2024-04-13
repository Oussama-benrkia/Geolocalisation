package org.app.backend.Vehicule.Controller;

import jakarta.transaction.Transactional;
import org.app.backend.Vehicule.Model.Vehicule;
import org.app.backend.Vehicule.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
public class VehiculeController {

    @GetMapping("/matricule/{matricule}")
    public Vehicule findByMatricule(@PathVariable String matricule) {
        return vehiculeService.findByMatricule(matricule);
    }

    @Transactional
    @DeleteMapping("/matricule/{matricule}")
    public Vehicule deleteByMatricule(@PathVariable String matricule) {
        return vehiculeService.deleteByMatricule(matricule);
    }

    @GetMapping("/matricule/{matricule}/modele/{modele}")
    public List<Vehicule> findByMatriculeAndModele(@PathVariable String matricule, @PathVariable String modele) {
        return vehiculeService.findByMatriculeAndModele(matricule, modele);
    }

    @GetMapping("/")
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @PostMapping("/")
    public int addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.addVehicule(vehicule);
    }


    @Transactional
    @PutMapping("{matricule}")
    public int editVehicule(@PathVariable String matricule, @RequestBody Vehicule vehicule) {
        return vehiculeService.editVehicule(matricule, vehicule);
    }

    @Autowired
    private VehiculeService vehiculeService;

}
