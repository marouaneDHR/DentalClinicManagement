package com.dentalcenter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double montantTotal;
    private Double montantPaye;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FicheTraitement> fichesTraitement = new ArrayList<>();;

    @PrePersist
    @PreUpdate
    private void setPatientFromFichesTraitement() {
        if (fichesTraitement != null && !fichesTraitement.isEmpty()) {
            this.patient = fichesTraitement.get(0).getPatient();
        }
    }
}
