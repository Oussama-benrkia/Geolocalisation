package org.app.backend.Vehicule.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.app.backend.User.Dto.UserResp;
import org.app.backend.Vehicule.Dto.VehiculeRequest;
import org.app.backend.Vehicule.Dto.VehiculeRequestUp;
import org.app.backend.Vehicule.Model.Vehicule;
import org.app.backend.Vehicule.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
@RequiredArgsConstructor
public class VehiculeController {
    //inside Vehiculeresp ,there are constructor which change Vehicule to Vehiculeresp use it,

    // return  type  class Vehiculersep ,
    @GetMapping("/{matricule}")
    public ResponseEntity<Vehicule> findByMatricule(@PathVariable String matricule) {
        Vehicule response = vehiculeService.findByMatricule(matricule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // return  type  class Vehiculersep ,
    @GetMapping("/matricule/{matricule}/modele/{modele}")
    public ResponseEntity<List<Vehicule>> findByMatriculeAndModele(@PathVariable String matricule, @PathVariable String modele) {
        List<Vehicule> vehicules_mm = vehiculeService.findByMatriculeAndModele(matricule, modele);
        return ResponseEntity.ok(vehicules_mm);
    }
    // return  list type  class Vehiculersep ,
    @GetMapping
    public ResponseEntity<List<Vehicule>> findAll() {
        List<Vehicule> vehicules = vehiculeService.findAll();
        return ResponseEntity.ok(vehicules);
    }

    // add image .
    // return type  class Vehiculersep ,
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> editVehicule(@PathVariable long id, @ModelAttribute VehiculeRequestUp vehicule) {
        // update parameter of editVehicule which accept long and VehiculeRequest
        Vehicule response = vehiculeService.editVehicule(id, vehicule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // return  type  class Vehiculersep ,
    @PostMapping("/")
    public ResponseEntity<Vehicule> createVehicule(@Valid @ModelAttribute VehiculeRequest vehicule) {
        // update parameter of editVehicule which accept long and VehiculeRequest
        Vehicule response = vehiculeService.createVehicule(vehicule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // return  type  class Vehiculersep ,
    @Transactional
    @DeleteMapping("/{id}")
    public  ResponseEntity<Vehicule> deleteVehicule(@PathVariable long id) {
        Vehicule response = vehiculeService.deleteVehicule(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private final VehiculeService vehiculeService;

}
