package com.example.PMS.Service;

import com.example.PMS.Entity.Student;
import com.example.PMS.Repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;
    private final PasswordEncoder encoder;

    public StudentService(StudentRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Student registerStudent(Student student) {
        // Check if email already exists
        if (repo.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRole("ROLE_STUDENT");
        return repo.save(student);
    }

    public Student addStudent(Student student) {
        return registerStudent(student);
    }

    public Optional<Student> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
