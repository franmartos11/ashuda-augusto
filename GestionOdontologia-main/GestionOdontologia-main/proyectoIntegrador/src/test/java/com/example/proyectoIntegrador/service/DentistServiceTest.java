package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.BadRequestException;
import com.example.proyectoIntegrador.exception.DentistNoContException;
import com.example.proyectoIntegrador.exception.DentistNotFoundException;
import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.repository.DentistRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class DentistServiceTest {

    @Mock
    private DentistRepo repository;

    @InjectMocks
    private DentistService service;
    private Dentist dentist;

    @BeforeEach
    void setUp(){
        dentist = new Dentist(2F,"Francisco","Martos","DN8735",null);
    }

    @Test
    @DisplayName("WHEN dentist are listed THEN doesnt throw exception")
    public void getAllDentist(){
        given(repository.findAll()).willReturn(List.of(dentist));
        assertDoesNotThrow(()->service.getAll());

    }

    @Test
    @DisplayName("WHEN dentist try to be listed but doent exists THEN throw DentistNoContentException")
    public void getAllDentistException(){
        given(repository.findAll()).willReturn(Collections.emptyList());
        assertThrows(DentistNoContException.class,()->service.getAll());

    }

    @Test
    @DisplayName("WHEN dentist findById THEN doesnt throw exception")
    public void getByIdDentist(){
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        assertDoesNotThrow(()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN dentist findById THEN throw DentistNotFoundException")
    public void getByIdDentistException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(DentistNotFoundException.class,()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN dentist findByMedicalLicense THEN doesnt throw exception")
    public void getByRegistrationDentist(){
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.of(dentist));
        assertDoesNotThrow(()->service.getByMedicalLicense("DN8735"));

    }

    @Test
    @DisplayName("WHEN dentist findByMedicalLicense THEN throw DentistNotFoundException")
    public void getByRegistrationDentistException(){
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.empty());
        assertThrows(DentistNotFoundException.class,()->service.getByMedicalLicense("DN8735"));

    }

    @Test
    @DisplayName("WHEN dentist is created THEN doesnt throw exception")
    public void createDentist(){
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.empty());
        assertDoesNotThrow(()->service.create(dentist));

    }

    @Test
    @DisplayName("WHEN dentist is created with repeated Medical License THEN throw BadRequestException")
    public void createDentistException(){
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.of(dentist));
        assertThrows(BadRequestException.class,()->service.create(dentist));

    }

    @Test
    @DisplayName("WHEN dentist is updated THEN doesnt throw exception")
    public void updateDentist(){
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        assertDoesNotThrow(()->service.update(dentist));

    }

    @Test
    @DisplayName("WHEN non existant dentist is updated THEN throw DentistNotFoundException")
    public void updateDentistException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(DentistNotFoundException.class,()->service.update(dentist));

    }

    @Test
    @DisplayName("WHEN dentist is deleted THEN doesnt throw exception")
    public void deleteByIdDentist(){
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        assertDoesNotThrow(()->service.deleteById(2F));

    }

    @Test
    @DisplayName("WHEN dentist thats not in db is deleted THEN throw DentistNotFoundException")
    public void deleteByIdDentistException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(BadRequestException.class,()-> service.deleteById(3F));

    }
}