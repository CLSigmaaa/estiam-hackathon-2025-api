package com.estiam.monitorcontrol.view;

import com.estiam.monitorcontrol.model.enums.Statut;
import lombok.Data;

@Data
public class SalleView {
    private Integer id;
    private String nom;
    private Integer capacite;
    private Statut statut;
}
