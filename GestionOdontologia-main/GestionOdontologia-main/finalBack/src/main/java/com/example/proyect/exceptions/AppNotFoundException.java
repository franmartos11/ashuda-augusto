package com.example.proyectoIntegrador.exception;

public class AppNotFoundException extends Exception{
    public AppNotFoundException() {
        super("Couldn't found the appointment");
    }
}
