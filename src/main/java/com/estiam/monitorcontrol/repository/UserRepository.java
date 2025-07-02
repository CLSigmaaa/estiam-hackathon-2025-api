package com.estiam.monitorcontrol.repository;

import com.estiam.monitorcontrol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String mail);
}
