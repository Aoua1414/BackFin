package com.aoua.medoc.ServiceImplement;

import com.aoua.medoc.Service.RdvService;
import com.aoua.medoc.controllers.NotificationController;
import com.aoua.medoc.models.Messages;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.NotificationRepository;
import com.aoua.medoc.repository.RdvRepository;
import com.aoua.medoc.repository.TraitementRepository;
import com.aoua.medoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class RdvImplement implements RdvService {

    private UserRepository userRepository;
    @Autowired
    private TraitementRepository traitementRepository;
    private NotificationRepository notificationRepository;
    @Autowired
    private final RdvRepository rdvRepository;

    @Override
    public String ajouter(Rdv rdv, User iduser) {
     rdv.setUser(iduser);
        rdvRepository.save(rdv);

//        NotificationController notificationController = new NotificationController(userRepository,traitementRepository, notificationRepository, rdvRepository);
//        notificationController.envoyermessage(iduser, rdv.getId_rdv(), true);

        return "Rendez-vous enregistré avec succès.";

    }

    @Override
    public Rdv modifier(Long id, Rdv rdv) {
        return rdvRepository.findById(id)
                .map(rdv1 -> {
//                    rdv1.setHeure_rdv(rdv.getHeure_rdv());
                    rdv1.setHeure(rdv.getHeure());
                    rdv1.setDate(rdv.getDate());
                    rdv1.setMotif(rdv.getMotif());
                    rdv1.setService_medical(rdv.getService_medical());
                    System.out.println(rdv1);
                    return rdvRepository.save(rdv1);
                }).orElseThrow(() -> new RuntimeException(String.valueOf(Messages.set("Rendez-vous introuvable",true))));
    }

    @Override
    public String supprimer(Long id) {
        rdvRepository.deleteById(id);
        return ("Supprime avec succes");
    }

    @Override
    public List<Rdv> afficher() {
        return rdvRepository.findAll();
    }


}

