package com.example.proyectoIntegrador.exception;

public class DentistNoContException extends Exception{
    public DentistNoContException() {
        super("There arent Dentist on data base");
    }
}
