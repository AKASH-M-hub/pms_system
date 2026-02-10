package com.example.PMS.DTO;

import java.time.LocalDate;

public class ApplicationDTO {
    private Long applicationId;
    private String status;
    private LocalDate appliedDate;
    private JobDTO job;
    private StudentDTO student;

    public ApplicationDTO(Long applicationId, String status,
                          LocalDate appliedDate, JobDTO job, StudentDTO student) {
        this.applicationId = applicationId;
        this.status = status;
        this.appliedDate = appliedDate;
        this.job = job;
        this.student = student;
    }

    public Long getApplicationId() { return applicationId; }
    public String getStatus() { return status; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public JobDTO getJob() { return job; }
    public StudentDTO getStudent() { return student; }
}
