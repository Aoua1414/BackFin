package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.PharmacienService;
import com.aoua.medoc.Service.UserService;
import com.aoua.medoc.models.ERole;
import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.models.Role;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.RoleRepository;
import com.aoua.medoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RequestMapping("/pharmacien")

@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:8100/"}, maxAge = 3600, allowCredentials="true")
@RestController
public class PharmacienController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;


    public final PharmacienService pharmacienService;
    @Autowired
    private UserRepository userRepository;

    public PharmacienController(PharmacienService pharmacienService) {
        this.pharmacienService = pharmacienService;
    }
    //Afficher pharmaciens
    @GetMapping(value = "/liste")
//    @PreAuthorize("hasAuthority('USER,ADMIN')")

    public List<Pharmacien> afficher(){
        return pharmacienService.afficher();
    }
    //Ajouter pharmacien
    @PostMapping("/ajouter")
   // @PreAuthorize("hasRole('ADMIN')")
    public Object ajouter(@RequestBody Pharmacien pharmacien ){

        try {

            pharmacienService.ajouter(pharmacien);
            System.out.println("nom "+pharmacien.getNom_prenom());
            return "Ajouté avec succès";
        } catch (HttpStatusCodeException httpStatusCodeException) {
            return "Non authorise";
        }


//        User rol = new User();
        //rol.add(user);

//        Role role= userRepository.RecupererRoleUser(rol);
//        if (user.getRoles().equals(ERole.ROLE_ADMIN))
//        if (user.getRoles().equals("ROLE_ADMIN")){
//            pharmacienService.ajouter(pharmacien);
//            return "Ajouté avec succès";
//        }else {
//            return "Vous n'êtes pas autorisé a faire cette action";
//        }



        //roles.add(role);
//        if (user.getRoles().equals("ROLE_ADMIN")){
//            try{
//                pharmacienService.ajouter(pharmacien);
//                return "Ajouté avec succès";
//            }catch (Exception e){
//                return "Existe déjà";
//            }
//        }else {
//            return "Vous n'êtes pas autorisé a faire cette action";
//        }

    }
    // Modifier pharmacien
    @PutMapping(value = "/modifier/{id}")
//    @PreAuthorize("hasAutority(ADMIN)")
    public String modifier(@PathVariable Long id, @RequestBody Pharmacien pharmacien){
        pharmacienService.modifier(id, pharmacien);
        return "modifié avec succès";
    }
    //supprimer pharmacien
    @DeleteMapping(value = "/supprimer/{id}")
//    @PreAuthorize("hasAuthority(ADMIN)")
    public String supprimer(@PathVariable long id){
        return pharmacienService.supprimer(id);
    }

}
