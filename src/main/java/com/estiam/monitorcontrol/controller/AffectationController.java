package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.model.Affectation;
import com.estiam.monitorcontrol.repository.AffectationRepository;
import com.estiam.monitorcontrol.view.AffectationView;
import com.estiam.monitorcontrol.view.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affectations")
public class AffectationController {

    @Autowired
    private AffectationRepository affectationRepository;

    @GetMapping
    @JsonView(JsonViews.AffectationBasic.class)
    public List<Affectation> getAllAffectations() {
        return affectationRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.AffectationDetail.class)
    public Affectation getAffectationById(@PathVariable Integer id) {
        return affectationRepository.findById(id).orElse(null);
    }


    @PostMapping
    public Affectation createAffectation(@RequestBody Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.AffectationDetail.class)
    public Affectation updateAffectation(@PathVariable Integer id, @RequestBody Affectation affectationDetails) {
        Affectation affectation = affectationRepository.findById(id).orElse(null);
        if (affectation != null) {
            affectation.setHeureDebut(affectationDetails.getHeureDebut());
            affectation.setHeureFin(affectationDetails.getHeureFin());
            affectation.setNomProfesseur(affectationDetails.getNomProfesseur());
            affectation.setSalles(affectationDetails.getSalles());
            affectation.setClasses(affectationDetails.getClasses());
            affectation.setDateModification(affectationDetails.getDateModification());
            return affectationRepository.save(affectation);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAffectation(@PathVariable Integer id) {
        affectationRepository.deleteById(id);
    }
}
