package com.dentalcenter.service;

import com.dentalcenter.model.Facture;
import com.dentalcenter.model.FicheTraitement;
import com.dentalcenter.repository.FactureRepository;
import com.dentalcenter.repository.FicheTraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private FicheTraitementRepository ficheTraitementRepository;

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Facture getFactureById(Long id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée"));
    }

    public Facture saveFacture(Facture facture) {

        for (FicheTraitement fiche : facture.getFichesTraitement()) {
            fiche.setFacture(facture);
        }
        return factureRepository.save(facture);
    }

    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }
}
