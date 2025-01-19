
package com.dentalcenter.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String heure;
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
