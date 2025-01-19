
package com.dentalcenter.repository;

import com.dentalcenter.model.FicheTraitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FicheTraitementRepository extends JpaRepository<FicheTraitement, Long> {

    List<FicheTraitement> findAllByPatientId(Long patientId);
}
