package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.model.Classe;
import com.estiam.monitorcontrol.repository.ClasseRepository;
import com.estiam.monitorcontrol.view.AffectationView;
import com.estiam.monitorcontrol.view.ClasseView;
import com.estiam.monitorcontrol.view.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping
    @JsonView(JsonViews.ClasseBasic.class)
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.ClasseBasic.class)
    public Classe getClasseById(@PathVariable Integer id) {
        return classeRepository.findById(id).orElse(null);
    }

    @PostMapping
    @JsonView(JsonViews.ClasseBasic.class)
    public Classe createClasse(@RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.ClasseBasic.class)
    public Classe updateClasse(@PathVariable Integer id, @RequestBody Classe classeDetails) {
        Classe classe = classeRepository.findById(id).orElse(null);
        if (classe != null) {
            classe.setNom(classeDetails.getNom());
            classe.setEffectif(classeDetails.getEffectif());
            classe.setType(classeDetails.getType());
            return classeRepository.save(classe);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteClasse(@PathVariable Integer id) {
        classeRepository.deleteById(id);
    }
}
