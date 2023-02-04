package com.aoua.medoc.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.aoua.medoc.Service.UserService;
import com.aoua.medoc.payload.request.LoginRequest;
import com.aoua.medoc.payload.request.SignupRequest;
import com.aoua.medoc.payload.response.JwtResponse;
import com.aoua.medoc.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoua.medoc.models.ERole;
import com.aoua.medoc.models.Role;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.RoleRepository;
import com.aoua.medoc.repository.UserRepository;
import com.aoua.medoc.security.jwt.JwtUtils;
import com.aoua.medoc.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;
  @Autowired
  UserService userService;
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/log")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
//    userService.aouamethode(userDetails.getId());
    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(),
                       // userDetails.getNumero(),
                         userDetails.getEmail(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Nom deja existant"));
      //"Error: Username is already taken!"
    }

   /* if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
*/
    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getNumero(),

               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER);
      if (userRole==null){
        return ResponseEntity.badRequest().body(new MessageResponse("dfghj"));
      }else {
        roles.add(userRole);
      }


    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);

          roles.add(adminRole);

          break;

        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER);

          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User bien enregistré!"));
  }
}
