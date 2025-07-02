package com.estiam.monitorcontrol.controller;

import com.estiam.monitorcontrol.model.Information;
import com.estiam.monitorcontrol.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/informations")
public class InformationController {

    private final InformationRepository informationRepository;

    public InformationController(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @GetMapping
    public List<Information> getAllInformations() {
        return informationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Information> getInformationById(@PathVariable Integer id) {
        Optional<Information> info = informationRepository.findById(id);
        return info.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Information createInformation(@RequestBody Information information) {
        return informationRepository.save(information);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<Information> updateInformation(@PathVariable Integer id, @RequestBody Information updatedInfo) {
        return informationRepository.findById(id)
                .map(existingInfo -> {
                    existingInfo.setType(updatedInfo.getType());
                    existingInfo.setMessage(updatedInfo.getMessage());
                    existingInfo.setStatut(updatedInfo.getStatut());
                    informationRepository.save(existingInfo);
                    return ResponseEntity.ok(existingInfo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformation(@PathVariable Integer id) {
        if (informationRepository.existsById(id)) {
            informationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
