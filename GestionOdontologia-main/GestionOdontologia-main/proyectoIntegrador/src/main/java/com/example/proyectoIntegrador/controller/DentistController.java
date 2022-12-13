package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.exception.DentistNoContException;
import com.example.proyectoIntegrador.exception.DentistNotFoundException;
import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.service.DentistService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/dentists")
public class DentistController {

    private final DentistService service;

    @GetMapping("/all")
    public ResponseEntity<List<Dentist>> getAll() throws DentistNoContException {
        return ResponseEntity.ok(service.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getById(@PathVariable Long id) throws DentistNotFoundException {
        return ResponseEntity.ok(service.getById(id));

    }

    @GetMapping("/registration")
    public ResponseEntity<Dentist> getByRegistration(@RequestBody String registration) throws DentistNotFoundException {
        return ResponseEntity.ok(service.getByMedicalLicense(registration));

    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Dentist d){
        try{
            service.create(d);
            return new ResponseEntity<>("Dentist Registered Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Dentist d){

        try{
            service.update(d);
            return new ResponseEntity<>("Dentist Modified Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Dentist Deleted Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }
}

