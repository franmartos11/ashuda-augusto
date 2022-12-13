package com.example.proyectoIntegrador.exception;

public class DentistNoContentException extends Exception{
    public DentistNoContentException() {
        super("No existen odontologos en la base de datos");
    }
}
