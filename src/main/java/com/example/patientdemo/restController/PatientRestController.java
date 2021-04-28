package com.example.patientdemo.restController;

import com.example.patientdemo.dao.PatientRepository;
import com.example.patientdemo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PatientRestController {
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/patient/get/{id}")
    public Patient getPatient(@PathVariable Integer id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isPresent())
            return optionalPatient.get();
        return null;
    }

}
