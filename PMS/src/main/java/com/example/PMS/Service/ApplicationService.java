package com.example.PMS.Service;

import com.example.PMS.Entity.*;
import com.example.PMS.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository appRepo;
    private final StudentRepository studentRepo;
    private final JobRepository jobRepo;

    public ApplicationService(ApplicationRepository appRepo,
                              StudentRepository studentRepo,
                              JobRepository jobRepo) {
        this.appRepo = appRepo;
        this.studentRepo = studentRepo;
        this.jobRepo = jobRepo;
    }

    public Application apply(Long studentId, Long jobId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus("APPLIED");
        application.setAppliedDate(LocalDate.now());

        return appRepo.save(application);
    }

    public List<Application> getAllApplications() {
        return appRepo.findAll();
    }
}
