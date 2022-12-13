package com.example.proyectoIntegrador.exception;

public class AppointmentNotFoundException extends Exception{
    public AppointmentNotFoundException() {
        super("El turno no fue encontrado");
    }
}
