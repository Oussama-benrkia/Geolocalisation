package org.app.backend.Vehicule.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.backend.User.Model.User;
import org.app.backend.Vehicule.Model.Enum.Status;
import org.app.backend.Vehicule.Model.Enum.Type;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String matricule;
    private String nom;
    private String modele;
    private boolean etat;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Type type;
    @CreationTimestamp
    private LocalDateTime date_crt;
    @UpdateTimestamp
    private LocalDateTime date_upt;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}