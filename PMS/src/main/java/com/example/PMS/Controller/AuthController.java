package com.example.PMS.Controller;

import com.example.PMS.Entity.Student;
import com.example.PMS.Service.AuthenticationService;
import com.example.PMS.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final StudentService studentService;

    public AuthController(AuthenticationService authenticationService,
                          StudentService studentService) {
        this.authenticationService = authenticationService;
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String role = request.getRole().toLowerCase();

            if ("student".equals(role)) {
                // Authenticate as student
                Map<String, String> response = authenticationService.authenticateStudent(
                    request.getEmail(),
                    request.getPassword()
                );
                return ResponseEntity.ok(response);
            } else if ("admin".equals(role)) {
                // Authenticate as admin
                Map<String, String> response = authenticationService.authenticateAdmin(
                    request.getEmail(),
                    request.getPassword()
                );
                return ResponseEntity.ok(response);
            } else {
                // Fallback: try both
                Map<String, String> response = authenticationService.login(
                    request.getEmail(),
                    request.getPassword()
                );
                return ResponseEntity.ok(response);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during login"));
        }
    }

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        try {
            Student registeredStudent = studentService.registerStudent(student);
            return ResponseEntity.ok(Map.of(
                "message", "Student registration successful",
                "email", registeredStudent.getEmail()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during registration"));
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;
        private String role = "student";

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
