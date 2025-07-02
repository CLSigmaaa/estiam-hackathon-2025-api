package com.estiam.monitorcontrol.repository;

import com.estiam.monitorcontrol.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByMail(String mail);
}
