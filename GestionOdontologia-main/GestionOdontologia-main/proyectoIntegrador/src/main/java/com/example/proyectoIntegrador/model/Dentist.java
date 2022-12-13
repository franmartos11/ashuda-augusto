package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "dentist")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="firstName")
    private  String name;

    @NotNull
    @Column(name="lastName")
    private  String lastName;

    @NotNull
    @Column(name="registration",unique = true)
    private  String medicalLicense;

    @OneToMany(

            mappedBy = "dentist",
            cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.LAZY

    )

    @JsonIgnore
    private Set<Appointment> appointments= new HashSet<>();

    public void addAppointment(Appointment a) {
        appointments.add(a);
        a.setDentist(this);
    }

    public void removeAppointment(Appointment a) {
        appointments.add(a);
        a.setDentist(this);
    }
}
