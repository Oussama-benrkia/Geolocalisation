package org.app.backend.Vehicule.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.backend.Vehicule.Model.Enum.Status;
import org.app.backend.Vehicule.Model.Enum.Type;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeRequest {
    @NotEmpty
    private String matricule;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String modele;
    @NotEmpty
    private boolean etat;
    @NotEmpty
    private boolean status;
    @NotEmpty
    private String type;
    @NotNull
    private MultipartFile image;
}
