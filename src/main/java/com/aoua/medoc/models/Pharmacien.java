package com.aoua.medoc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Pharmacien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_prenom;
    private Integer numero;
    private String adresse;

    @ManyToOne
    User user;

}
