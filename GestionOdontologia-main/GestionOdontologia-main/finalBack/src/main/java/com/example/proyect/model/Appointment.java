package com.example.proyectoIntegrador.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="dayTime")
    private LocalDateTime dayTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

