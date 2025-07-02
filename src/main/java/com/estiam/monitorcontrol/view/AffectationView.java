package com.estiam.monitorcontrol.view;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AffectationView {
    private Integer id;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private String nomProfesseur;

    private List<SalleView> salles;
    private List<ClasseView> classes;
}
