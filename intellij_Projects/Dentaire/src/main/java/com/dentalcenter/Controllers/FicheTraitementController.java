package com.dentalcenter.Controllers;

import com.dentalcenter.model.FicheTraitement;
import com.dentalcenter.service.FicheTraitementService;
import com.dentalcenter.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fichestraitement")
public class FicheTraitementController {
    @Autowired
    private FicheTraitementService ficheTraitementService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listFichesTraitement(Model model) {
        model.addAttribute("currentPage", "fichestraitement");

        model.addAttribute("fichesTraitement", ficheTraitementService.getAllFichesTraitement());
        return "fichestraitement/list";
    }

    @GetMapping("/new")
    public String createFicheTraitementForm(Model model) {
        model.addAttribute("currentPage", "fichestraitement");

        model.addAttribute("ficheTraitement", new FicheTraitement());
        model.addAttribute("patients", patientService.getAllPatients());
        return "fichestraitement/form";
    }

    @PostMapping
    public String saveFicheTraitement(@ModelAttribute("ficheTraitement") FicheTraitement ficheTraitement) {
        ficheTraitementService.saveFicheTraitement(ficheTraitement);
        return "redirect:/fichestraitement";
    }

    @GetMapping("/edit/{id}")
    public String editFicheTraitementForm(@PathVariable Long id, Model model) {
        model.addAttribute("currentPage", "fichestraitement");

        model.addAttribute("ficheTraitement", ficheTraitementService.getFicheTraitementById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        return "fichestraitement/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteFicheTraitement(@PathVariable Long id) {
        ficheTraitementService.deleteFicheTraitement(id);
        return "redirect:/fichestraitement";
    }
}
