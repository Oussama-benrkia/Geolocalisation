package org.app.backend.dto;

import lombok.*;
import org.app.backend.model.enumm.Status;
import org.app.backend.model.enumm.Type;
import org.app.backend.model.Vehicule;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeResp {
    private Long id;
    private String matricule;
    private String nom;
    private String modele;
    private boolean etat;
    private Status status;
    private Type type;
    private Long user;
    private String image;
    private List<Double> lastpostion;
    @SneakyThrows
    public VehiculeResp(Vehicule p){
        this.id=p.getId();
        if (p.getUser() != null) {
            this.user = p.getUser().getId();  // Assuming you have a field to store the user ID
        } else {
            this.user = null;  // or handle accordingly
        }
        this.matricule=p.getMatricule();
        this.nom=p.getNom();
        this.modele=p.getModele();
        this.etat= p.isEtat();
        this.status=p.getStatus();
        this.type=p.getType();
        this.image=p.getImage();
        lastpostion=null;
    }
}
