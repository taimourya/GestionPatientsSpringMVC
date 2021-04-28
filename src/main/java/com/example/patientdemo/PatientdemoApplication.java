package com.example.patientdemo;

import com.example.patientdemo.dao.PatientRepository;
import com.example.patientdemo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PatientdemoApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PatientdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        patientRepository.save(new Patient(null, "yahya", new Date(), false));
        patientRepository.save(new Patient(null, "amine", new Date(), true));
        patientRepository.save(new Patient(null, "hamza", new Date(), false));
        patientRepository.save(new Patient(null, "mohammed", new Date(), false));
        patientRepository.save(new Patient(null, "oussama", new Date(), true));

        for(int i = 1; i <= 45; i++) {
            patientRepository.save(new Patient(null, "yahya" + i, new Date(), false));
        }


        patientRepository.findAll().forEach(patient -> System.out.println(patient.getNom()));

    }
}
