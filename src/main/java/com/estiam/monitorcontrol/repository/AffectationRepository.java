package com.estiam.monitorcontrol.repository;

import com.estiam.monitorcontrol.model.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Integer> {
}
