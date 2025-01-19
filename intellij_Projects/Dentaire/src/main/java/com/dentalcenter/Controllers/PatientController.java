package com.dentalcenter.Controllers;


import com.dentalcenter.model.Patient;
import com.dentalcenter.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("currentPage", "patients");

        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    @GetMapping("/new")
    public String showPatientForm(Model model) {
        model.addAttribute("currentPage", "patients");

        model.addAttribute("patient", new Patient());
        return "patients/form";
    }

    @PostMapping
    public String savePatient(@ModelAttribute("patient") Patient patient) {

        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("currentPage", "patients");

        model.addAttribute("patient", patientService.getPatientById(id));
        return "patients/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
