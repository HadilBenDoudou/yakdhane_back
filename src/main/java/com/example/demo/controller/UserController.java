package com.example.demo.controller;

import com.example.demo.Modele.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Vérifiez si l'utilisateur existe déjà
        if (userRepository.existsByCin(user.getCin())) {
            return ResponseEntity.badRequest().body("Un utilisateur avec ce CIN existe déjà. Veuillez vous connecter.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Un utilisateur avec cet email existe déjà. Veuillez vous connecter.");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès.");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String cin) {
        User user = userRepository.findByEmailAndCin(email, cin);
        if (user != null) {
            return ResponseEntity.ok("Connexion réussie.");
        } else {
            return ResponseEntity.status(401).body("Échec de la connexion. Email ou CIN incorrect.");
        }
    }
}


