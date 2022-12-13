package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepo extends JpaRepository<Dentist,Long> {

    @Query("select d from Dentist d where d.registration = ?1")
    Optional<Dentist> findByMedicalLicense(String medicalLicense);

}
