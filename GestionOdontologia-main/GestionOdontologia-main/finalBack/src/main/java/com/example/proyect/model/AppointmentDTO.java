package com.example.proyectoIntegrador.model;
import java.time.LocalDateTime;

public record AppointmentDTO (long id, LocalDateTime ldt, Dentist d,Patient p){
}
