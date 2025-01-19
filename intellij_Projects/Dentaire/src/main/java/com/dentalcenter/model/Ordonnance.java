
package com.dentalcenter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicaments;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
