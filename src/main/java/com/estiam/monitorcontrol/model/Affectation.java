package com.estiam.monitorcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "affectations")
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime heureDebut;

    private LocalDateTime heureFin;

    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;

    private String nomProfesseur;

    @ManyToMany
    @JoinTable(
            name = "affectations_salles",
            joinColumns = @JoinColumn(name = "affectation_id"),
            inverseJoinColumns = @JoinColumn(name = "salle_id")
    )
    private List<Salle> salles;

    @ManyToMany
    @JoinTable(
            name = "affectations_classes",
            joinColumns = @JoinColumn(name = "affectation_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private List<Classe> classes;
}
