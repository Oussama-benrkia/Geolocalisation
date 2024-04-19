package org.app.backend.Vehicule.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeRequestUp {
    @NotNull
    private String matricule;
    @NotNull
    private String nom;
    @NotNull
    private String modele;
    @NotNull
    private boolean etat;
    @NotNull
    private String status;
    @NotNull
    private String type;
    @NotNull
    private MultipartFile image;
}
