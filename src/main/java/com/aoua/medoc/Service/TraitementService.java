package com.aoua.medoc.Service;


import com.aoua.medoc.models.Traitement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface TraitementService{

    String ajouter(Traitement traitement, long id);

    Traitement modifier(Long id, Traitement traitement);

    String suupprimer(Long id);

    List<Traitement> afficher();



   // List<Traitement> flitreDate(LocalDate datedebut);




}
