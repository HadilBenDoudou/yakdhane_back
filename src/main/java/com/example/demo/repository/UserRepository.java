package com.example.demo.repository;

import com.example.demo.Modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByCin(String cin);
    boolean existsByEmail(String email);
    User findByEmailAndCin(String email, String cin);

}
