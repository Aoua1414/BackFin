package com.aoua.medoc.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String message;
    private LocalDate date;
    private LocalTime heure;

    @ManyToOne
    private User user;
    @ManyToOne
    private Traitement traitement;
    @ManyToOne
    private Rdv rdv;


}
