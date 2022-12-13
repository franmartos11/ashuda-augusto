package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="firstName")
    private  String firstName;

    @NotNull
    @Column(name="lastName")
    private  String lastName;

    @NotNull
    @Column(name="dni",unique = true)
    private  String dni;

    @NotNull
    @Column(name="dischargeDate")
    private  LocalDate dischargeDate;

    @NotNull
    @Column(name="address")
    private String address;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnore

    private Set<Appointment> appointments = new HashSet<>();

    public void addAppointment(Appointment a) {
        appointments.add(a);
        a.setPatient(this);
    }

    public void removeAppointment(Appointment a) {
        appointments.add(a);
        a.setPatient(this);
    }
}
