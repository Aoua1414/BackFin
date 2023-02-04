package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.controllers.NotificationController;
import com.aoua.medoc.models.Notification;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.repository.NotificationRepository;
import com.aoua.medoc.repository.RdvRepository;
import com.aoua.medoc.repository.TraitementRepository;
import com.aoua.medoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor

public class TraitementImplement  implements TraitementService {
@Autowired
    private final TraitementRepository traitementRepository;
@Autowired
    private final NotificationRepository notificationRepository;
@Autowired
    private UserRepository userRepository;
    @Autowired
    private RdvRepository rdvRepository;

    @Override
    public String ajouter(Traitement traitement, long iduser) {
        //Recuperation de date debut et fin de traitement

        LocalDate debut=traitement.getDate_debut();
        LocalDate fin = traitement.getDate_fin();
        long duree_traitement = traitement.getDuree_traitement();
        long entre = ChronoUnit.DAYS.between(debut,fin);

        System.out.println("=============== intervalle== "+entre);
        System.out.println("=============== duree_traitement== "+duree_traitement);

        if( entre == duree_traitement){
            Traitement traitement1 = traitementRepository.save(traitement);

            //pour envoyer notif

//            Notification no = new Notification();
//            no.setNom_medoc("Dessdrdrtrt");
//            no.setUser(traitement.getUser());
//            no.setDuree_traitement(traitement.getDuree_traitement());
//            no.setDate_debut(traitement.getDate_debut());
//            no.setDate_fin(traitement.getDate_fin());
//            no.setIntervalle(traitement.getIntervalle());
//            no.setPremiere_prise(traitement.getPremiere_prise());
//            no.setNbrePillule(traitement.getNbrePillule());
//            no.setNom_medoc(traitement.getNom_medoc());

//            NotificationController notificationController = new NotificationController(userRepository,traitementRepository, notificationRepository, rdvRepository);
//            notificationController.envoyermessage(iduser, traitement1.getId_traitement(),true);


//            no.setMessage("prenez votre medicament");
//            notificationRepository.save(no);
            return "Traitement enregistré avec succès.";
        }else

            return "Verifiez vos dates";


            }



    @Override
    public Traitement modifier(Long id, Traitement traitement) {

        return traitementRepository.findById(id)
                .map(traitement1 -> {
                    traitement1.setDuree_traitement(traitement.getDuree_traitement());
                    traitement1.setDate_fin(traitement.getDate_fin());
                    traitement1.setIntervalle(traitement.getIntervalle());
                    traitement1.setDate_debut(traitement.getDate_debut());
                    traitement1.setNom_medoc(traitement.getNom_medoc());
                    traitement1.setNbrePillule(traitement.getNbrePillule());
                    traitement1.setPremiere_prise(traitement.getPremiere_prise());
                    traitement1.setFois_parjour(traitement.getFois_parjour());
                    return traitementRepository.save(traitement1);
                }).orElseThrow(()->new RuntimeException("Traitement introuvable"));
                }

    @Override
    public String suupprimer(Long id) {
        traitementRepository.deleteById(id);
        return ("supprime avec succes");
    }




    @Override
    public List<Traitement> afficher() {
        return traitementRepository.findAll();
    }




   /* @Override
    public List<Traitement> flitreDate(LocalDate datedebut) {
        return traitementRepository.findByDatedebut(datedebut);
    }*/
}



