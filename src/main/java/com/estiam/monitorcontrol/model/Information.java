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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeInformation getType() {
        return type;
    }

    public void setType(TypeInformation type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

}
