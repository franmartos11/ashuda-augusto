package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.model.User;
import com.example.proyectoIntegrador.repository.UserRepo;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepo repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getAll() throws AppUserNoContentException {

        if(repository.findAll().isEmpty())
            throw new AppUserNoContentException();
        return repository.findAll();

    }

    public User getById(Long id) throws AppUserNotFoundException {

        return repository.findById(id).orElseThrow(AppUserNotFoundException::new);

    }

    public void create(User u) throws BadRequestException {

        if(repository.findByEmail(u.getEmail()).isPresent())
            throw new BadRequestException("A user with the email: " + u.getEmail() + " already exists in the data base");

        if (passwordEncoder != null) {
            var appUser = new User(null, u.getName(), u.getEmail(), passwordEncoder.encode(u.getPassword()), u.getRol());
            repository.save(appUser);
        }

    }

    public void update(User u) throws AppUserNotFoundException {

        if(repository.findById(u.getId()).isEmpty()) throw new AppUserNotFoundException();
        repository.save(u);

    }

    public void deleteById(Long id) throws AppUserNotFoundException {

        if(repository.findById(id).isEmpty()) throw new AppUserNotFoundException();
        repository.deleteById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return repository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email + " was not found in the data base"));

    }

}
