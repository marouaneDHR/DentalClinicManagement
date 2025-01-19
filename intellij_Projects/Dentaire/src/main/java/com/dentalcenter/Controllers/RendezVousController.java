package com.dentalcenter.Controllers;

import com.dentalcenter.model.RendezVous;
import com.dentalcenter.model.Patient;
import com.dentalcenter.service.RendezVousService;
import com.dentalcenter.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rendezvous")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listRendezVous(Model model) {
        model.addAttribute("currentPage", "rendezvous");

        model.addAttribute("rendezVous", rendezVousService.getAllRendezVous());
        return "rendezvous/list";
    }

    @GetMapping("/new")
    public String createRendezVousForm(Model model) {
        model.addAttribute("currentPage", "rendezvous");

        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("rendezVous", new RendezVous());
        model.addAttribute("patients", patients);
        return "rendezvous/form";
    }

    @PostMapping
    public String saveRendezVous(@ModelAttribute("rendezVous") RendezVous rendezVous) {
        rendezVousService.saveRendezVous(rendezVous);
        return "redirect:/rendezvous";
    }

    @GetMapping("/edit/{id}")
    public String editRendezVousForm(@PathVariable Long id, Model model) {
        model.addAttribute("currentPage", "rendezvous");

        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("rendezVous", rendezVousService.getRendezVousById(id));
        model.addAttribute("patients", patients);
        return "rendezvous/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return "redirect:/rendezvous";
    }
}
