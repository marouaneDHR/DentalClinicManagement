
package com.dentalcenter.repository;

import com.dentalcenter.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAllByPatientId(Long patientId);
}
