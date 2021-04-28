package com.example.patientdemo.controller;

import com.example.patientdemo.dao.PatientRepository;
import com.example.patientdemo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    private void preparePatientsModel(Model model, int page, int size, String mc) {
        Page<Patient> patientPage = patientRepository.findAllByNomContains(mc, PageRequest.of(page, size));
        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("mc", mc);
    }

    @GetMapping("/patients")
    public String getPatients(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "") String mc)
    {

        preparePatientsModel(model, page, size, mc);
        model.addAttribute("patient", new Patient());

        return "patients";
    }

    @GetMapping("/removePatient")
    public String removePatient(@RequestParam(required = true) Integer id,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "") String mc)
    {
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&mc="+mc;
    }


    @PostMapping("/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {

            preparePatientsModel(model, 0, 5, "");
            model.addAttribute("errorSavePatient", true);///sert pour afficher le modal bootstrap pour afficher les erreurs
            return "patients";
        }
        patientRepository.save(patient);
        return "redirect:/patients";
    }

}
