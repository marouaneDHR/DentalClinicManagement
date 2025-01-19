package com.dentalcenter.Controllers;

import com.dentalcenter.model.Facture;
import com.dentalcenter.model.FicheTraitement;
import com.dentalcenter.service.FactureService;
import com.dentalcenter.service.FicheTraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private FicheTraitementService ficheTraitementService;

    @GetMapping
    public String listFactures(Model model) {
        model.addAttribute("currentPage", "factures");

        model.addAttribute("factures", factureService.getAllFactures());
        model.addAttribute("fichesTraitement", ficheTraitementService.getAllFichesTraitement());
        return "factures/list";
    }

    @GetMapping("/new")
    public String createFactureForm(Model model) {
        model.addAttribute("currentPage", "factures");

        model.addAttribute("facture", new Facture());
        model.addAttribute("fichesTraitement", ficheTraitementService.getAllFichesTraitement());
        return "factures/form";
    }

    @PostMapping
    public String saveFacture(@ModelAttribute("facture") Facture facture,
                              @RequestParam("ficheTraitementIds") List<Long> ficheTraitementIds) {
        for (Long ficheId : ficheTraitementIds) {
            FicheTraitement ficheTraitement = ficheTraitementService.getFicheTraitementById(ficheId);
            facture.getFichesTraitement().add(ficheTraitement);
        }

        factureService.saveFacture(facture);
        return "redirect:/factures";
    }

    @GetMapping("/edit/{id}")
    public String editFactureForm(@PathVariable Long id, Model model) {
        model.addAttribute("currentPage", "factures");

        model.addAttribute("facture", factureService.getFactureById(id));
        model.addAttribute("fichesTraitement", ficheTraitementService.getAllFichesTraitement());
        return "factures/form";
    }
}
