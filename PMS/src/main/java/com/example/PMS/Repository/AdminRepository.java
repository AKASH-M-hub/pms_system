package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PMS.Entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
