package com.estiam.monitorcontrol.model;

import com.estiam.monitorcontrol.model.enums.TypeInformation;
import jakarta.persistence.*;

@Entity
@Table(name = "informations")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TypeInformation type;

    private String message;

    private Boolean statut;

}
