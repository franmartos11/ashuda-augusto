package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.exception.*;
import com.example.proyectoIntegrador.repository.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.proyectoIntegrador.model.UserRoles.ADMIN;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo repository;
    @InjectMocks

    private UserService service;
    private User user;

    @BeforeEach
    void setUp(){
        user = new User(2F,"Francisco","franmartos11@gmail.com","FranMartos",ADMIN_ROL);

    }

    @Test
    @DisplayName("WHEN list users THEN doesnt throw UserNoContentException")
    public void getAllUser(){
        given(repository.findAll()).willReturn(List.of(user));
        assertDoesNotThrow(()->service.getAll());

    }

    @Test
    @DisplayName("WHEN  list users BUT doent exist THEN throws UserNoContentException")
    public void getAllUsersException(){
        given(repository.findAll()).willReturn(Collections.emptyList());
        assertThrows(UserNoContException.class,()->service.getAll());

    }

    @Test
    @DisplayName("WHEN bring user byId THEN doesnt throw UserNotFoundException")
    public void getByIdUser(){
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        assertDoesNotThrow(()->service.getById(2F));

    }

    @Test
    @DisplayName("WHEN bring user byId THEN throws UserNotFoundException")
    public void getByIdUserException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->service.getById(2L));

    }

    @Test
    @DisplayName("WHEN user is created THEN doent throw exception")
    public void createUser(){
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        assertDoesNotThrow(()->service.create(user));

    }

    @Test
    @DisplayName("WHEN user is created with repeated dni THEN throws BadRequestException")
    public void createUserException(){
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        assertThrows(BadRequestException.class,()->service.create(user));

    }

    @Test
    @DisplayName("WHEN user is updated THEN doesnt throws exception")
    public void updateUser(){
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        assertDoesNotThrow(()->service.update(user));

    }

    @Test
    @DisplayName("WHEN non created user tries to update THEN throws UserNotFoundException")
    public void updateUserException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->service.update(user));

    }

    @Test
    @DisplayName("WHEN user is deleted THEN doesnt throw exception")
    public void deleteByIdUser(){
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        assertDoesNotThrow(()->service.deleteById(2F));

    }

    @Test
    @DisplayName("WHEN user thats not in db is deleted THEN throw UserNotFoundException")
    public void deleteByIdUserException(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()-> service.deleteById(2E));

    }

    @Test
    @DisplayName("WHEN user findbyEmail THEN doesnt throw exception")
    public void findByEmail(){
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        assertDoesNotThrow(()->service.loadUserByUsername("franmartos11@gmail.com"));

    }

    @Test
    @DisplayName("WHEN user findbyEmail with no name THEN throws UsernameNotFoundException")
    public void findByEmailException(){
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,()->service.loadUserByUsername("franmartos11@gmail.com"));

    }

}
