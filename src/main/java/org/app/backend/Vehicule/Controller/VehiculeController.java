package org.app.backend.Vehicule.Controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.app.backend.User.Dto.UserResp;
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

    @GetMapping("/{matricule}")
    public ResponseEntity<Vehicule> findByMatricule(@PathVariable String matricule) {
        Vehicule response = vehiculeService.findByMatricule(matricule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/matricule/{matricule}/modele/{modele}")
    public ResponseEntity<List<Vehicule>> findByMatriculeAndModele(@PathVariable String matricule, @PathVariable String modele) {
        List<Vehicule> vehicules_mm = vehiculeService.findByMatriculeAndModele(matricule, modele);
        return ResponseEntity.ok(vehicules_mm);
    }
    @GetMapping
    public ResponseEntity<List<Vehicule>> findAll() {
        List<Vehicule> vehicules = vehiculeService.findAll();
        return ResponseEntity.ok(vehicules);
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> editVehicule(@PathVariable long id, @RequestBody Vehicule vehicule) {
        Vehicule response = vehiculeService.editVehicule(id, vehicule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        Vehicule response = vehiculeService.createVehicule(vehicule);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
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
