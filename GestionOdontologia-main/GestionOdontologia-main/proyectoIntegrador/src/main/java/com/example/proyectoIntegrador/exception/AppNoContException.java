package com.example.proyectoIntegrador.exception;

public class AppNoContException extends Exception{
    public AppNoContException() {
        super("There arent turns on the data base");
    }
}
