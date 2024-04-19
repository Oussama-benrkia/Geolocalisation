package org.app.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private String etat;
    @NotEmpty
    private String status;
    @NotEmpty
    private String type;
    @NotNull
    private MultipartFile image;
}
