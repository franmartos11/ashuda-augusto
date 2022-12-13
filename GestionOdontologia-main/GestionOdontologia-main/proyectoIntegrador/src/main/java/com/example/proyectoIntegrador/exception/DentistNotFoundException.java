package com.example.proyectoIntegrador.exception;

public class DentistNotFoundException extends Exception{
    public DentistNotFoundException() {
        super("El odontologo no fue encontrado");
    }
}
