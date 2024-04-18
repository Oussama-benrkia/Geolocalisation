package org.app.backend.Vehicule.Dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.app.backend.Vehicule.Model.Enum.Status;
import org.app.backend.Vehicule.Model.Enum.Type;
import org.app.backend.Vehicule.Model.Vehicule;
import org.springframework.web.multipart.MultipartFile;

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
    private List<String> image;
    @SneakyThrows
    public VehiculeResp(Vehicule p){
        ObjectMapper mapper = new ObjectMapper();
        this.id=p.getId();
        this.user=p.getUser().getId();
        this.matricule=p.getMatricule();
        this.nom=p.getNom();
        this.modele=p.getModele();
        this.etat= p.isEtat();
        this.status=p.getStatus();
        this.type=p.getType();
        this.image= List.of(mapper.readValue(p.getImage(), String[].class));
    }
}
