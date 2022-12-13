package com.example.proyectoIntegrador.exception;

public class AppointmentNotContentException extends Exception{
    public AppointmentNotContentException() {
        super("No existen turnos en la base de datos");
    }
}
