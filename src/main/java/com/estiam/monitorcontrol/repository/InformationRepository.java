package com.estiam.monitorcontrol.repository;

import com.estiam.monitorcontrol.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {
}
