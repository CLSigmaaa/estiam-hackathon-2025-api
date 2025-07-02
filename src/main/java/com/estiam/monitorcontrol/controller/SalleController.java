package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.model.Salle;
import com.estiam.monitorcontrol.repository.SalleRepository;
import com.estiam.monitorcontrol.view.AffectationView;
import com.estiam.monitorcontrol.view.JsonViews;
import com.estiam.monitorcontrol.view.SalleView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    @Autowired
    private SalleRepository salleRepository;

    @GetMapping
    @JsonView(JsonViews.SalleDetail.class)
    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.SalleDetail.class)
    public Salle getSalleById(@PathVariable Integer id) {
        return salleRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleRepository.save(salle);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.SalleDetail.class)
    public Salle updateSalle(@PathVariable Integer id, @RequestBody Salle salleDetails) {
        Salle salle = salleRepository.findById(id).orElse(null);
        if (salle != null) {
            salle.setNom(salleDetails.getNom());
            salle.setCapacite(salleDetails.getCapacite());
            salle.setStatut(salleDetails.getStatut());
            return salleRepository.save(salle);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSalle(@PathVariable Integer id) {
        salleRepository.deleteById(id);
    }
}
