package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.config.jwt.JwtUtil;
import com.example.proyectoIntegrador.config.jwt.model.AuthenticationRequest;
import com.example.proyectoIntegrador.config.jwt.model.AuthenticationResponse;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.User;
import com.example.proyectoIntegrador.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
public class UserController {

    private UserService service;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws AppUserNoContentException {
        return ResponseEntity.ok(service.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) throws AppUserNotFoundException {
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody User u){
        try{
            service.create(u);
            return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody User appUser){
        try{
            service.update(appUser);
            return new ResponseEntity<>("User Modified Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect Credentials", e);

        }

        final UserDetails userDetails = service.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse((jwt)));
    }
}
