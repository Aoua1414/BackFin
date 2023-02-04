package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.RdvService;
import com.aoua.medoc.models.Messages;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:8100/"}, maxAge = 3600, allowCredentials="true")
@RequestMapping("/rdv")
public class RdvController {
    public final RdvService rdvService;
    @Autowired
     RdvRepository rdvRepository;
    public RdvController(RdvService rdvService) {
        this.rdvService = rdvService;
    }
    //Afficher liste des rdv

    @GetMapping(value = "/liste")
//    @PreAuthorize("hasAuthority(USER)")
    public List<Rdv> afficher(){
        return rdvRepository.findAll();
    }

    //afficher par id
    @GetMapping("/parid/{id}")
    public Optional<Rdv> afficherrdv(@PathVariable Long id){
      return rdvRepository.findById(id);
    }

    //Ajouter rdv

    @PostMapping(value = "/ajouter/{id}")
     public Messages ajouter(@RequestBody Rdv rdv, @PathVariable("id") User user){
//        System.out.println("heure= "+ rdv.getHeure());
//        System.out.println("date= "+ rdv.getDate());
//        System.out.println("motif= "+ rdv.getMotif());
//        System.out.println("service_medical= "+ rdv.getService_medical());
        try {
            rdvService.ajouter(rdv,user);
            Messages messages = new Messages("Ajouté avec succès",true);
            return messages;
        }catch (Exception e){
            Messages messages = new Messages("Erreur d'ajout ",false);
            return messages;
//                   return e.getMessage();
        }
    }

    //Modification rdv
    @PutMapping(value = "/modifier/{id}")
//    @PreAuthorize("hasAuthority(USER)")
    public Rdv modifier(@PathVariable Long id, @RequestBody Rdv rdv){
//        rdvService.modifier(id, rdv);
        return rdvService.modifier(id, rdv );
    }

    //Suppression RDV
    @DeleteMapping(value = "/suprimer/{id}")
//    @PreAuthorize("hasAuthority(USER)")
    public String supprimer(@PathVariable long id){
        return rdvService.supprimer(id);
    }

    //Afficher liste des rdv du jour
    @GetMapping(value = "/jour/{id}")

    public List<Rdv> afficher(@PathVariable("id") User user) {


        LocalDate lt
                = LocalDate.now();
        List<Rdv> rdvList = rdvRepository.findByDateAndUser(lt,user);

        return rdvList;
    }
}
