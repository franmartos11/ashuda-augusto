package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.exception.AppointmentNotContentException;
import com.example.proyectoIntegrador.exception.AppointmentNotFoundException;
import com.example.proyectoIntegrador.model.AppointmentDTO;
import com.example.proyectoIntegrador.service.AppointmentService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor

@RestController
@CrossOrigin
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/all")
    public ResponseEntity<Set<AppointmentDTO>> getAll() throws AppointmentNotContentException {
        return ResponseEntity.ok(service.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id) throws AppointmentNotFoundException {
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody AppointmentDTO dto){
        try{
            service.create(dto);
            return new ResponseEntity<>("Appointment Registered Successfully",HttpStatus.CREATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> update(@RequestBody AppointmentDTO dto){
        try{
            service.update(dto);
            return new ResponseEntity<>("Appointment Modified Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            service.deleteById(id);
            return new ResponseEntity<>("Appointment Deleted Successfully",HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }
}

