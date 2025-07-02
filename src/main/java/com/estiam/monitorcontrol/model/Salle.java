package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.Statut;
import com.estiam.monitorcontrol.view.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(JsonViews.SalleBasic.class)
    private Integer id;

    @JsonView(JsonViews.SalleBasic.class)
    private String nom;

    @JsonView(JsonViews.SalleBasic.class)
    private Integer capacite;

    @Enumerated(EnumType.STRING)
    @JsonView(JsonViews.SalleBasic.class)
    private Statut statut;

    @ManyToMany(mappedBy = "salles")
    @JsonView(JsonViews.SalleDetail.class)
    private List<Affectation> affectations;
}

