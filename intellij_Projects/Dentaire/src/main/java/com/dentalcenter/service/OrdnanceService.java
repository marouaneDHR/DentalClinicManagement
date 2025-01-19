package com.dentalcenter.service;

import com.dentalcenter.model.Ordonnance;
import com.dentalcenter.repository.OrdonnanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdnanceService {
    @Autowired
    private OrdonnanceRepository ordonnanceRepository;

    public List<Ordonnance> getAllOrdonnances() {
        return ordonnanceRepository.findAll();
    }

    public List<Ordonnance> getOrdonnancesByPatientId(Long patientId) {
        return ordonnanceRepository.findByPatientId(patientId);
    }

    public Ordonnance getOrdonnanceById(Long id) {
        return ordonnanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordonnance non trouvée"));
    }

    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.save(ordonnance);
    }

    public void deleteOrdonnance(Long id) {
        ordonnanceRepository.deleteById(id);
    }
}
