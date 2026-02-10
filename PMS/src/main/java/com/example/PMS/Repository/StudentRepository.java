package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PMS.Entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
