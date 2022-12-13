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
        user = new User(1L,"Facundo","facundo@gmail.com","1111",ADMIN);
    }

    @Test
    @DisplayName("WHEN we list all the user THEN don´t throws any exception")
    public void getAllUser(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }
    @Test
    @DisplayName("WHEN we list all user but none exists anyone THEN it throws AppUserNoContentException")
    public void getAllUsersException(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertThrows(UserNoContException.class,()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a user by id THEN don´t throws any exception")
    public void getByIdUser(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a user by id THEN it throws AppUserNotFoundException")
    public void getByIdUserException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(UserNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we create a user then don´t throws any exception")
    public void createUser(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(user));
    }
    @Test
    @DisplayName("WHEN we create a user with the repeated dni then it throws BadRequestException")
    public void createUserException(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(user));
    }

    @Test
    @DisplayName("WHEN we update a user then don´t throws any exception")
    public void updateUser(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(user));
    }
    @Test
    @DisplayName("WHEN we update a user that not exists then it throws AppUserNotFoundException")
    public void updateUserException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(UserNotFoundException.class,()->service.update(user));
    }
    @Test
    @DisplayName("WHEN we delete user THEN don´t throws any exception")
    public void deleteByIdUser(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete user that is not present in the db THEN it throws AppUserNotFoundException")
    public void deleteByIdUserException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(UserNotFoundException.class,()-> service.deleteById(5L));
    }

    @Test
    @DisplayName("WHEN we find user by email THEN don´t throws any exception")
    public void findByEmail(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.of(user));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.loadUserByUsername("facundo@gmail.com"));
    }

    @Test
    @DisplayName("WHEN we find user by email THEN throws UsernameNotFoundException")
    public void findByEmailException(){
        //GIVEN
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(UsernameNotFoundException.class,()->service.loadUserByUsername("facundo@gmail.com"));
    }
}
