package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where p.dni = ?1")
    Optional<Patient> findByDni(String dni);

}
