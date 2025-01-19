
package com.dentalcenter.repository;

import com.dentalcenter.model.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {
    @Query("SELECT o FROM Ordonnance o WHERE o.patient.id = :patientId")
    List<Ordonnance> findByPatientId(@Param("patientId") Long patientId);

}
