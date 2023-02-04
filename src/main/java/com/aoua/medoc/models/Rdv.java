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
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rdv;
    private String service_medical;
    private String motif;
    private LocalDate date;
    private LocalTime heure;

    @ManyToOne
    public User user;
    public void modifier(String service_medical, String motif, LocalDate date_rdv, LocalTime heure_rdv){
        this.service_medical=service_medical;
        this.motif=motif;
        this.date=date_rdv;
        this.heure=heure_rdv;
    }

}
