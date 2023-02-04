package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.UserService;
import com.aoua.medoc.models.User;
import org.aspectj.bridge.Message;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:8100/"}, maxAge = 3600, allowCredentials="true")
//@CrossOrigin(value = "http://localhost:8100",maxAge = 3600,allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Ajout d'un user
   @PostMapping(value = "/ajouter")
//   @PreAuthorize("hasAuthority('USER')")
    public Object ajouter(User user){
        try{
            userService.ajouter(user);
            return "Ajouté avec succès";

        }catch (Exception e ){
            return "Cet user existe deja";
        }

    }
// Afficher la liste des users
    @GetMapping(value = "/liste")
//  @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> afficher(){
        return userService.afficher();
    }
}