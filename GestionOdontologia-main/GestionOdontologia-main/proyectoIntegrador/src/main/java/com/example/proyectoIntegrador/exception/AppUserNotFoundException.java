package com.example.proyectoIntegrador.exception;

public class AppUserNotFoundException extends Exception{
    public AppUserNotFoundException() {
        super("El usuario no fue encontrado");
    }
}
