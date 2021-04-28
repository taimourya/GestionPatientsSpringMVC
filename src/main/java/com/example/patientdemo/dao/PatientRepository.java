package com.example.patientdemo.dao;

import com.example.patientdemo.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Page<Patient> findAllByNomContains(String mc, Pageable pageable);
}
