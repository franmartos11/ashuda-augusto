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
        dentist = new Dentist(1L,"Facundo","Burgos","ABC",null);
    }

    @Test
    @DisplayName("WHEN we list all the dentists THEN don´t throws any exception")
    public void getAllDentist(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(dentist));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }
    @Test
    @DisplayName("WHEN we list all dentists but none exists anyone THEN it throws DentistNoContentException")
    public void getAllDentistException(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertThrows(DentistNoContException.class,()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a dentist by id THEN don´t throws any exception")
    public void getByIdDentist(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a dentist by id THEN it throws DentistNotFoundException")
    public void getByIdDentistException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(DentistNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we bring a dentist by registration THEN don´t throws any exception")
    public void getByRegistrationDentist(){
        //GIVEN
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.of(dentist));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getByMedicalLicense("ABC"));
    }
    @Test
    @DisplayName("WHEN we bring a dentist by registration THEN it throws DentistNotFoundException")
    public void getByRegistrationDentistException(){
        //GIVEN
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(DentistNotFoundException.class,()->service.getByMedicalLicense("ABC"));
    }

    @Test
    @DisplayName("WHEN we create a dentist then don´t throws any exception")
    public void createDentist(){
        //GIVEN
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(dentist));
    }
    @Test
    @DisplayName("WHEN we create a dentist with the repeated registration then it throws BadRequestException")
    public void createDentistException(){
        //GIVEN
        given(repository.findByMedicalLicense(anyString())).willReturn(Optional.of(dentist));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(dentist));
    }

    @Test
    @DisplayName("WHEN we update a dentist then don´t throws any exception")
    public void updateDentist(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(dentist));
    }
    @Test
    @DisplayName("WHEN we update a dentist that not exists then it throws DentistNotFoundException")
    public void updateDentistException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(DentistNotFoundException.class,()->service.update(dentist));
    }

    @Test
    @DisplayName("WHEN we delete dentist THEN don´t throws any exception")
    public void deleteByIdDentist(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(dentist));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete dentist that is not present in the db THEN it throws DentistNotFoundException")
    public void deleteByIdDentistException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()-> service.deleteById(5L));
    }
}