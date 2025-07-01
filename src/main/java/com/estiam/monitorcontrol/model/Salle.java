package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.Statut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private Integer capacite;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToMany(mappedBy = "salles")
    private List<Affectation> affectations;

}
