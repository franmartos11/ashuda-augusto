package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.BadRequestException;
import com.example.proyectoIntegrador.exception.DentistNoContentException;
import com.example.proyectoIntegrador.exception.DentistNotFoundException;

import com.example.proyectoIntegrador.model.Dentist;
import com.example.proyectoIntegrador.repository.DentistRepo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DentistService {

    private final DentistRepo repository;

    public List<Dentist> getAll() throws DentistNoContentException {

        if(repository.findAll().isEmpty())
            throw new DentistNoContentException();
        return repository.findAll();

    }

    public Dentist getById(Long id) throws DentistNotFoundException {
        return repository.findById(id).orElseThrow(DentistNotFoundException::new);

    }

    public Dentist getByMedicalLicense(String ml) throws DentistNotFoundException {
        return repository.findByMedicalLicense(ml).orElseThrow(DentistNotFoundException::new);

    }

    public void create(Dentist d) throws BadRequestException {

        if(repository.findByMedicalLicense(d.getMedicalLicense()).isPresent())
            throw new BadRequestException("Already Exist a Dentist With the Medical License : " + d.getMedicalLicense() + " on the data base");
        repository.save(d);

    }

    public void update(Dentist d) throws DentistNotFoundException {

        if (repository.findById(d.getId()).isEmpty())
            throw new DentistNotFoundException();
        repository.save(d);

    }

    public void deleteById(Long id) throws BadRequestException {

        if (repository.findById(id).isEmpty())
            throw new BadRequestException(" Doesnt seem to exist a dentist with the medical License" + id + " in the data base");
        repository.deleteById(id);

    }

}
