package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.TypeClasse;
import com.estiam.monitorcontrol.view.ClasseView;
import com.estiam.monitorcontrol.view.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(JsonViews.ClasseBasic.class)
    private Integer id;

    @JsonView(JsonViews.ClasseBasic.class)
    private String nom;

    @JsonView(JsonViews.ClasseBasic.class)
    private Integer effectif;

    @Enumerated(EnumType.STRING)
    @JsonView(JsonViews.ClasseBasic.class)
    private TypeClasse type;

    @ManyToMany(mappedBy = "classes")
    @JsonView(JsonViews.ClasseDetail.class)
    private List<Affectation> affectations;

}

