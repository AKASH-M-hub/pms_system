package com.example.PMS.Controller;

import com.example.PMS.Entity.Application;
import com.example.PMS.Entity.Student;
import com.example.PMS.Service.ApplicationService;
import com.example.PMS.Service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {

    private final ApplicationService applicationService;
    private final StudentService studentService;

    public StudentController(ApplicationService applicationService, StudentService studentService) {
        this.applicationService = applicationService;
        this.studentService = studentService;
    }

    @PostMapping("/apply/{jobId}")
    public Application applyToJob(
            @PathVariable Long jobId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        Student student = studentService.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return applicationService.apply(student.getStudentId(), jobId);
    }
}
