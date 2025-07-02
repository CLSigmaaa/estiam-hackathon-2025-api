package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.dto.LoginRequest;
import com.estiam.monitorcontrol.model.Utilisateur;
import com.estiam.monitorcontrol.repository.UtilisateurRepository;
import com.estiam.monitorcontrol.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByMail(loginRequest.getMail());
        if (utilisateur == null || !passwordEncoder.matches(loginRequest.getMotDePasse(), utilisateur.getMotDePasse())) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
        String token = jwtUtil.generateToken(utilisateur.getMail(), utilisateur.getRole());
        return ResponseEntity.ok(token);
    }

    // Exemple d'endpoint protégé
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Token manquant");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            return ResponseEntity.status(401).body("Token invalide");
        }
        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);
        return ResponseEntity.ok("Profil de " + username + " avec rôle " + role);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Integer id) {
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Utilisateur createUser(@Valid @RequestBody Utilisateur user) {
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return utilisateurRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Integer id, @Valid @RequestBody Utilisateur updatedUser) {
        return utilisateurRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setMail(updatedUser.getMail());
                    existingUser.setMotDePasse(passwordEncoder.encode(updatedUser.getMotDePasse()));
                    existingUser.setRole(updatedUser.getRole());
                    utilisateurRepository.save(existingUser);
                    return ResponseEntity.ok(existingUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
