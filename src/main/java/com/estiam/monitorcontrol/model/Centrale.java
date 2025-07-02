package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.Etat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "centrales")
public class Centrale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String topique;

    @Enumerated(EnumType.STRING)
    private Etat etat;

}


