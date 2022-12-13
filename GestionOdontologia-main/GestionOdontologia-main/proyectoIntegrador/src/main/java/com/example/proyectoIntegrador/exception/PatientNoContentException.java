package com.example.proyectoIntegrador.exception;

public class PatientNoContentException extends Exception{
    public PatientNoContentException() {
        super("No existen pacientes en la base de datos");
    }
}
