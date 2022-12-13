package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.exception.PatientNoContentException;
import com.example.proyectoIntegrador.exception.PatientNotFoundException;

import com.example.proyectoIntegrador.model.Patient;
import com.example.proyectoIntegrador.service.PatientService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService service;

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAll() throws PatientNoContentException {
        return ResponseEntity.ok(service.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) throws PatientNotFoundException {
        return ResponseEntity.ok(service.getById(id));

    }

    @GetMapping("/dni")
    public ResponseEntity<Patient> getByDni(@RequestBody String dni) throws PatientNotFoundException {
        return ResponseEntity.ok(service.getByDni(dni));

    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Patient p){
        try{
            service.create(p);
            return new ResponseEntity<>("Patient Registered Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }

    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody Patient p){
        try{
            service.update(p);
            return new ResponseEntity<>("Patient Modified Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Patient Deleted Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

}
