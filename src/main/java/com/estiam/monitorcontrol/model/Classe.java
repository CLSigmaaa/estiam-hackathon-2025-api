package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.TypeClasse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "classes")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private Integer effectif;

    @Enumerated(EnumType.STRING)
    private TypeClasse type;

    @ManyToMany(mappedBy = "classes")
    private List<Affectation> affectations;

}

