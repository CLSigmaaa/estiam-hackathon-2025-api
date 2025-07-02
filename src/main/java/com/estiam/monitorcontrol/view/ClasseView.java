package com.estiam.monitorcontrol.view;

import com.estiam.monitorcontrol.model.enums.TypeClasse;
import lombok.Data;

@Data
public class ClasseView {
    private Integer id;
    private String nom;
    private Integer effectif;
    private TypeClasse type;
}