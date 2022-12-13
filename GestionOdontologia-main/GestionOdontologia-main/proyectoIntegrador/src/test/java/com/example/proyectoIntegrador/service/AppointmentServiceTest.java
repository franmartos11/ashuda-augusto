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
        appointmentDto = new AppointmentDTO(1L, LocalDateTime.now(),new Dentist(),new Patient());
        appointment = new Appointment();
    }

    @Test
    @DisplayName("WHEN we list all the appointments THEN don´t throws any exception")
    public void getAllAppointments(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(appointment));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }
    @Test
    @DisplayName("WHEN we list all appointments but none exists anyone THEN it throws AppointmentNoContentException")
    public void getAllAppointmentsException(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertThrows(AppointmentNotContentException.class,()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a appointment by id THEN don´t throws any exception")
    public void getByIdAppointment(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a appointment by id THEN it throws AppointmentNotFoundException")
    public void getByIdDentistException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(AppointmentNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we update a appointment then don´t throws any exception")
    public void updateAppointment(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(appointmentDto));
    }
    @Test
    @DisplayName("WHEN we update a appointment that not exists then it throws AppointmentNotFoundException")
    public void updateAppointmentException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(AppointmentNotFoundException.class,()->service.update(appointmentDto));
    }

    @Test
    @DisplayName("WHEN we delete appointment THEN don´t throws any exception")
    public void deleteByIdDentist(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(appointment));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete appointment that is not present in the db THEN it throws AppointmentNotFoundException")
    public void deleteByIdDentistException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(AppointmentNotFoundException.class,()-> service.deleteById(5L));
    }
}