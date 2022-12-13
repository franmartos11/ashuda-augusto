package com.example.proyectoIntegrador.exception;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException() {
        super("El paciente no fue encontrado");
    }
}
