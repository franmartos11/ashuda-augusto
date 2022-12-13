package com.example.proyectoIntegrador.exception;

public class PatientNoContentException extends Exception{
    public PatientNoContentException() {
        super("There arent patients on the data base");
    }
}
