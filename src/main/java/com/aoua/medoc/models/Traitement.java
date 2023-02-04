package com.aoua.medoc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_traitement;
    private String nom_medoc;
    private Long duree_traitement;
    private String nbrePillule;
    private Long fois_parjour;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private LocalTime premiere_prise;
    private LocalTime intervalle;

    @ManyToOne
    private User user;
}
