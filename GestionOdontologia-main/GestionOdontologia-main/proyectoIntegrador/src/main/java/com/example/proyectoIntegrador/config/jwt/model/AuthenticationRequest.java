package com.example.proyectoIntegrador.config.jwt.model;

public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String e, String p) {
        this.email = e;
        this.password = p;

    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String p) {
        this.password = p;

    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String e) {
        this.email = e;

    }


}
