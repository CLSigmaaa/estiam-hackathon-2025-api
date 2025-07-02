package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.model.Centrale;
import com.estiam.monitorcontrol.repository.CentraleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/centrales")
public class CentraleController {
    @Autowired
    private CentraleRepository centraleRepository;

    @GetMapping
    public List<Centrale> getAllCentrales() {
        return centraleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Centrale> getCentraleById(@PathVariable Integer id) {
        Optional<Centrale> centrale = centraleRepository.findById(id);
        return centrale.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Centrale createCentrale(@RequestBody Centrale centrale) {
        return centraleRepository.save(centrale);
    }

    // PUT update centrale
    @PutMapping("/{id}")
    public ResponseEntity<Centrale> updateCentrale(@PathVariable Integer id, @RequestBody Centrale updatedCentrale) {
        return centraleRepository.findById(id)
                .map(existingCentrale -> {
                    existingCentrale.setNom(updatedCentrale.getNom());
                    existingCentrale.setTopique(updatedCentrale.getTopique());
                    existingCentrale.setEtat(updatedCentrale.getEtat());
                    centraleRepository.save(existingCentrale);
                    return ResponseEntity.ok(existingCentrale);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE centrale
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentrale(@PathVariable Integer id) {
        if (centraleRepository.existsById(id)) {
            centraleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
