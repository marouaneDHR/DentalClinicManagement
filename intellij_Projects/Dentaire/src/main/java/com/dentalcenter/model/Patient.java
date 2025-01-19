
package com.dentalcenter.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Ordonnance> ordonnances;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Facture> factures;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVous;
}
