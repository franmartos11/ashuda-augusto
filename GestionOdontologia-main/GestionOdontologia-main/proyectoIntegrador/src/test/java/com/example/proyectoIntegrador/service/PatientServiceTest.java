package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.repository.PatientRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepo repository;

    @InjectMocks
    private PatientService service;
    private Patient patient;

    @BeforeEach
    void setUp(){
        patient = new Patient(2F,"Francisco","Martos","43555555", LocalDate.now(),"Ohiggins 2000",null);

    }

    @Test
    @DisplayName("WHEN patients are listed THEN doesnt throw exception")
    public void getAllPatients(){
        given(repository.findAll()).willReturn(List.of(patient));
        assertDoesNotThrow(()->service.getAll());

    }

    @Test
    @DisplayName("WHEN patients try to be listed but doent exists THEN throw PatientNoContentException")
    public void getAllPatientsException(){
        given(repository.findAll()).willReturn(Collections.emptyList());
        assertThrows(PatientNoContentException.class,()->service.getAll());

    }

    @Test
    @DisplayName("WHEN patient findById THEN doesnt throw exception")
    public void getByIdPatient(){
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        assertDoesNotThrow(()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN patient findById THEN throw PatientNotFoundException")
    public void getByIdPatientException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class,()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN patient findByDni THEN doesnt throw exception")
    public void getByDniPatient(){
        given(repository.findByDni(anyString())).willReturn(Optional.of(patient));
        assertDoesNotThrow(()->service.getByDni("43555555"));

    }

    @Test
    @DisplayName("WHEN patient findByDni THEN throw PatientNotFoundException")
    public void getByDniPatientException(){
        given(repository.findByDni(anyString())).willReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class,()->service.getByDni("43555555"));

    }

    @Test
    @DisplayName("WHEN patient is created THEN doesnt throw exception")
    public void createPatient(){
        given(repository.findByDni(anyString())).willReturn(Optional.empty());
        assertDoesNotThrow(()->service.create(patient));

    }

    @Test
    @DisplayName("WHEN patient is created with a repeated dni THEN throw BadRequestException")
    public void createPatientException(){
        given(repository.findByDni(anyString())).willReturn(Optional.of(patient));
        assertThrows(BadRequestException.class,()->service.create(patient));

    }

    @Test
    @DisplayName("WHEN patient is updated THEN doesnt throw exception")
    public void updatePatient(){
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        assertDoesNotThrow(()->service.update(patient));

    }

    @Test
    @DisplayName("WHEN patient non created is updated THEN throw PatientNotFoundException")
    public void updatePatientException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class,()->service.update(patient));

    }

    @Test
    @DisplayName("WHEN  patient is deleted THEN doesnt throw exception")
    public void deleteByIdPatient(){
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        assertDoesNotThrow(()->service.deleteById(2F));

    }

    @Test
    @DisplayName("WHEN patient thats not in db is deleted THEN throw PatientNotFoundException")
    public void deleteByIdPatientException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class,()-> service.deleteById(3E));

    }

}
