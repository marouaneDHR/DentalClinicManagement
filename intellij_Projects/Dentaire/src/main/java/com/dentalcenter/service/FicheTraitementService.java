package com.dentalcenter.service;

import com.dentalcenter.model.FicheTraitement;
import com.dentalcenter.repository.FicheTraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheTraitementService {
    @Autowired
    private FicheTraitementRepository ficheTraitementRepository;

    public List<FicheTraitement> getAllFichesTraitement() {
        return ficheTraitementRepository.findAll();
    }

    public FicheTraitement getFicheTraitementById(Long id) {
        return ficheTraitementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fiche de traitement non trouvée"));
    }

    public FicheTraitement saveFicheTraitement(FicheTraitement ficheTraitement) {
        if (ficheTraitement.getFacture() != null) {
            ficheTraitement.setFacture(ficheTraitement.getFacture());
        }
        return ficheTraitementRepository.save(ficheTraitement);
    }

    public void deleteFicheTraitement(Long id) {
        ficheTraitementRepository.deleteById(id);
    }

    public List<FicheTraitement> getFichesTraitementByPatient(Long patientId) {
        return ficheTraitementRepository.findAllByPatientId(patientId);
    }
}
