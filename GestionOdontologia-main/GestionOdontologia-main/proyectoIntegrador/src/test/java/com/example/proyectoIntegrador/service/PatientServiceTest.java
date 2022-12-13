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
        patient = new Patient(1L,"Facundo","Burgos","123456789", LocalDate.now(),"San juan 120",null);
    }

    @Test
    @DisplayName("WHEN we list all the patients THEN don´t throws any exception")
    public void getAllPatients(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(patient));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }
    @Test
    @DisplayName("WHEN we list all patients but none exists anyone THEN it throws PatientNoContentException")
    public void getAllPatientsException(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertThrows(PatientNoContentException.class,()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a patient by id THEN don´t throws any exception")
    public void getByIdPatient(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a patient by id THEN it throws PatientNotFoundException")
    public void getByIdPatientException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(PatientNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we bring a patient by dni THEN don´t throws any exception")
    public void getByDniPatient(){
        //GIVEN
        given(repository.findByDni(anyString())).willReturn(Optional.of(patient));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getByDni("123456789"));
    }
    @Test
    @DisplayName("WHEN we bring a patient by dni THEN it throws PatientNotFoundException")
    public void getByDniPatientException(){
        //GIVEN
        given(repository.findByDni(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(PatientNotFoundException.class,()->service.getByDni("123456789"));
    }

    @Test
    @DisplayName("WHEN we create a patient then don´t throws any exception")
    public void createPatient(){
        //GIVEN
        given(repository.findByDni(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(patient));
    }
    @Test
    @DisplayName("WHEN we create a patient with the repeated dni then it throws BadRequestException")
    public void createPatientException(){
        //GIVEN
        given(repository.findByDni(anyString())).willReturn(Optional.of(patient));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(patient));
    }

    @Test
    @DisplayName("WHEN we update a patient then don´t throws any exception")
    public void updatePatient(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(patient));
    }
    @Test
    @DisplayName("WHEN we update a patient that not exists then it throws PatientNotFoundException")
    public void updatePatientException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(PatientNotFoundException.class,()->service.update(patient));
    }

    @Test
    @DisplayName("WHEN we delete patient THEN don´t throws any exception")
    public void deleteByIdPatient(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(patient));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete patient that is not present in the db THEN it throws PatientNotFoundException")
    public void deleteByIdPatientException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(PatientNotFoundException.class,()-> service.deleteById(5L));
    }
}
