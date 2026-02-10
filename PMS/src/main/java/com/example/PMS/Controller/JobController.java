package com.example.PMS.Controller;

import com.example.PMS.Entity.Student;
import com.example.PMS.Repository.ApplicationRepository;
import com.example.PMS.Repository.JobRepository;
import com.example.PMS.Repository.StudentRepository;
import com.example.PMS.DTO.JobDTO;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JobController {

    private final JobRepository jobRepository;
    private final StudentRepository studentRepository;
    private final ApplicationRepository applicationRepository;

    public JobController(JobRepository jobRepository,
                         StudentRepository studentRepository,
                         ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.studentRepository = studentRepository;
        this.applicationRepository = applicationRepository;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/jobs")
    public List<JobDTO> getJobs(Authentication authentication) {
        String email = authentication.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return jobRepository.findAll().stream().map(job -> {
            boolean applied = applicationRepository
                    .existsByStudentStudentIdAndJobJobId(
                            student.getStudentId(),
                            job.getJobId()
                    );
            JobDTO dto = new JobDTO();
            dto.setJobId(job.getJobId());
            dto.setTitle(job.getTitle());
            dto.setSalary(job.getSalary());
            dto.setEligibilityCgpa(job.getEligibilityCgpa());
            dto.setDeadlineDate(job.getDeadlineDate());
            dto.setRegistrationLink(job.getRegistrationLink());
            dto.setApplied(applied);
            return dto;
        }).collect(Collectors.toList());
    }
}
