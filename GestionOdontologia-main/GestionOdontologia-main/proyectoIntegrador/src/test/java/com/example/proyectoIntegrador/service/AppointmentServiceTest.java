package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.Appointment;
import com.example.proyectoIntegrador.model.AppointmentDTO;
import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.repository.AppointmentRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepo repository;

    @InjectMocks
    private AppointmentService service;
    private AppointmentDTO appointmentDto;
    private Appointment appointment;

    @BeforeEach
    void setUp(){
        appointmentDto = new AppointmentDTO(2F, LocalDateTime.now(),new Dentist(),new Patient());
        appointment = new Appointment();

    }

    @Test
    @DisplayName("WHEN appointments are listed THEN doesnt throw exception")
    public void getAllAppointments(){
        given(repository.findAll()).willReturn(List.of(appointment));
        assertDoesNotThrow(()->service.getAll());

    }

    @Test
    @DisplayName("WHEN non existing appointments are listed THEN throw AppNoContentException")
    public void getAllAppointmentsException(){
        given(repository.findAll()).willReturn(Collections.emptyList());
        assertThrows(AppNoContException.class,()->service.getAll());

    }

    @Test
    @DisplayName("WHEN appointment findById THEN doesnt throw exception")
    public void getByIdAppointment(){
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        assertDoesNotThrow(()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN appointment findById THEN throw AppNotFoundException")
    public void getByIdDentistException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(AppNotFoundException.class,()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN appointment is updated THEN doesnt throw exception")
    public void updateAppointment(){
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        assertDoesNotThrow(()->service.update(appointmentDto));

    }

    @Test
    @DisplayName("WHEN non existing appointment is updated THEN throw AppNotFoundException")
    public void updateAppointmentException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(AppNotFoundException.class,()->service.update(appointmentDto));

    }

    @Test
    @DisplayName("WHEN appointment id deleted THEN doesnt throw exception")
    public void deleteByIdDentist(){
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        assertDoesNotThrow(()->service.deleteById(2F));

    }

    @Test
    @DisplayName("WHEN appointment thats not in db is deleted THEN throw AppNotFoundException")
    public void deleteByIdDentistException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(AppNotFoundException.class,()-> service.deleteById(3D));

    }

}