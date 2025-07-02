package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.view.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(JsonViews.AffectationBasic.class)
    private Integer id;

    @JsonView(JsonViews.AffectationBasic.class)
    private LocalDateTime heureDebut;

    @JsonView(JsonViews.AffectationBasic.class)
    private LocalDateTime heureFin;

    @JsonView(JsonViews.AffectationBasic.class)
    private LocalDateTime dateCreation;

    @JsonView(JsonViews.AffectationBasic.class)
    private LocalDateTime dateModification;

    @JsonView(JsonViews.AffectationBasic.class)
    private String nomProfesseur;

    @ManyToMany
    @JoinTable(
            name = "affectations_salles",
            joinColumns = @JoinColumn(name = "affectation_id"),
            inverseJoinColumns = @JoinColumn(name = "salle_id")
    )
    @JsonView(JsonViews.AffectationBasic.class)
    private List<Salle> salles;

    @ManyToMany
    @JoinTable(
            name = "affectations_classes",
            joinColumns = @JoinColumn(name = "affectation_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    @JsonView(JsonViews.AffectationBasic.class)
    private List<Classe> classes;
}
