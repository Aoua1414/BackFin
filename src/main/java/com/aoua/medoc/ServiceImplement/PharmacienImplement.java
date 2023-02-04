package com.aoua.medoc.ServiceImplement;

import com.aoua.medoc.Service.PharmacienService;
import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.repository.PharmacienRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Service


public class PharmacienImplement implements PharmacienService {

    private final PharmacienRepository pharmacienRepository;
    @Override
    public Pharmacien ajouter(Pharmacien pharmacien) {
        return pharmacienRepository.save(pharmacien);
    }

    @Override
    public Pharmacien modifier(Long id,  Pharmacien pharmacien) {
        return pharmacienRepository.findById(id)
                .map(pharmacien1 ->{
                    pharmacien1.setNom_prenom(pharmacien.getNom_prenom());
                    pharmacien1.setAdresse(pharmacien.getAdresse());
                    pharmacien1.setNumero(pharmacien.getNumero());
                    return pharmacienRepository.save(pharmacien1);
                }).orElseThrow(()-> new RuntimeException("Pharmacien introuvable"));

    }

    @Override
    public String supprimer(Long id) {
        pharmacienRepository.deleteById(id);
        return ("Supprime avec succes");
    }

    @Override
    public List<Pharmacien> afficher() {
       return pharmacienRepository.findAll();
    }

}
