package com.dentalcenter.Controllers;

import com.dentalcenter.model.Ordonnance;
import com.dentalcenter.service.OrdnanceService;
import com.dentalcenter.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ordonnances")
public class OrdnanceController {
    @Autowired
    private OrdnanceService ordnanceService;

    @Autowired
    private PatientService patientService;


    @GetMapping
    public String listOrdonnances(@RequestParam(value = "patientId", required = false) Long patientId, Model model) {
        model.addAttribute("currentPage", "ordonnances");

        if (patientId != null) {
            model.addAttribute("ordonnances", ordnanceService.getOrdonnancesByPatientId(patientId));
        } else {
            model.addAttribute("ordonnances", ordnanceService.getAllOrdonnances());
        }

        model.addAttribute("patients", patientService.getAllPatients()); // Fetch all patients for the dropdown
        model.addAttribute("selectedPatientId", patientId); // To keep the selected value in the dropdown
        return "ordonnances/list";
    }

    @GetMapping("/new")
    public String createOrdonnanceForm(Model model) {
        model.addAttribute("currentPage", "ordonnances");

        model.addAttribute("ordonnance", new Ordonnance());
        model.addAttribute("patients", patientService.getAllPatients());
        return "ordonnances/form";
    }

    @PostMapping
    public String saveOrdonnance(@ModelAttribute("ordonnance") Ordonnance ordonnance) {
        ordnanceService.saveOrdonnance(ordonnance);
        return "redirect:/ordonnances";
    }

    @GetMapping("/edit/{id}")
    public String editOrdonnanceForm(@PathVariable Long id, Model model) {
        model.addAttribute("currentPage", "ordonnances");

        model.addAttribute("ordonnance", ordnanceService.getOrdonnanceById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        return "ordonnances/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrdonnance(@PathVariable Long id) {
        ordnanceService.deleteOrdonnance(id);
        return "redirect:/ordonnances";
    }



}
