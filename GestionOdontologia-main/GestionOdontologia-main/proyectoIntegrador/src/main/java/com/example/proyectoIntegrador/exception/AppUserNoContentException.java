package com.example.proyectoIntegrador.exception;

public class AppUserNoContentException extends Exception{
    public AppUserNoContentException() {
        super("No existen usuarios en la base de datos");
    }
}
