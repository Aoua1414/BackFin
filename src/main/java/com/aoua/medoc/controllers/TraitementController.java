package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.ServiceImplement.NotificationImplement;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8200", "http://localhost:8100"}, maxAge = 3600, allowCredentials = "true")
@RequestMapping("/api/traitement")
public class TraitementController {
    @Autowired
    public final TraitementService traitementService;

    @Autowired
    private final TraitementRepository traitementRepository;


    public TraitementController(TraitementService traitementService,
                                TraitementRepository traitementRepository) {
        this.traitementService = traitementService;
        this.traitementRepository = traitementRepository;
    }

    //Afficher liste des traitements
    @GetMapping(value = "/jour/{id}")

    public List<Traitement> afficher(@PathVariable("id") User user) {


        //Traitement traitementList =  traitementRepository.findById(id).get();
        LocalDate lt
                = LocalDate.now();
        List<Traitement> traitementList = traitementRepository.findByUser(user);

        List<Traitement> todayTraitement=new ArrayList<>();
        for (Traitement traitement:traitementList
             ) {
            if(traitement.getDate_debut().equals(lt) || (traitement.getDate_debut().isBefore(lt) && traitement.getDate_fin().isAfter(lt)) || traitement.getDate_fin().equals(lt) ){
                todayTraitement.add(traitement);
            }
        }

        return todayTraitement;
    }


    @GetMapping(value = "/liste")

    public List<Traitement> afficherTraitementJour() {
        return traitementService.afficher();
    }


     //Ajouter traitement
    @PostMapping(value = "/ajouter/{id}")
    public Object ajouter(@RequestBody Traitement traitement, @PathVariable("id") User user) {
//        try {
       System.out.println("----- "+traitement.getIntervalle());
        NotificationImplement.sendNotification();
        traitement.setUser(user);
            return  traitementService.ajouter(traitement,user.getId());

//        } catch (Exception e) {
//
//            return e.getMessage();
//        }
    }

    // Modification d'un traitement
    @PutMapping(value = "/modifier/{nom_medoc}")
    @PreAuthorize("hasAuthority('USER')")

    public String modifier(@PathVariable Long id , @RequestBody Traitement traitement) {
          traitementService.modifier(id, traitement);
          return "modifié avec succès ";
    }

    /*
    @GetMapping(value = "/filtrertraitementParDate")
    public List<Traitement> filtrerParDate(@RequestParam  LocalDate date1) {


        return traitementService(date1);*/
       /* Date today = new Date();

        System.out.println("TYUI"+date1);
       Traitement trrt = (Traitement) traitementService.afficher();
       if(trrt.getDate_debut().isBefore(date1) ){
           return trrt.getNom_medoc();
       }
       else{
       }*/

   // }

    //suppression d'un traitement
    @DeleteMapping(value = "/supprimer/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public String supprimer(@PathVariable long id){
        return traitementService.suupprimer(id);
    }


    @GetMapping(value = "/touttraitememt")
    public List<List<Traitement>> toutdujour(){
        List<Traitement> tr = traitementRepository.findAll();
        System.out.println("tout trtm "+tr.size());
        System.out.println("tout trtm "+tr);

        LocalDate lt = LocalDate.now();


        List<List<Traitement>> traitedujour = new ArrayList<>();
   /*  if(tr.get(6).getDate_debut().equals(lt)){
         System.out.println("tout dans condition ");
     } && tr.get(i).getDate_fin().isAfter(lt) || tr.get(i).getDate_debut().equals(lt) && tr.get(i).getDate_fin().isAfter(lt)
     else {
         System.out.println("hors ");
     }*/

        //pour avoir les traitements du jour

        for(int i = 0; i<tr.size();i++){
            Period intervalle = Period.between(tr.get(i).getDate_fin(),tr.get(i).getDate_debut());
            System.out.println("debut"+tr.get(i).getDate_debut());
            System.out.println("tout trtm "+tr);
          if(!intervalle.isNegative()){
              traitedujour.add(tr);
          }
        }

        return traitedujour;
    }
}