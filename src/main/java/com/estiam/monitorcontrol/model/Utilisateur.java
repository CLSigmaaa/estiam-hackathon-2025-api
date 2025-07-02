package com.estiam.monitorcontrol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ananas")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotNull(message = "Le mail est obligatoire")
    @Email(message = "Le mail doit être valide")
    private String mail;

    @Column(nullable = false)
    @NotNull(message = "Le mot de passe est obligatoire")
    private String motDePasse;

    @Column(nullable = false)
    @NotNull(message = "Le rôle est obligatoire")
    private String role;

}

