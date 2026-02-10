package com.example.PMS.Controller;

import com.example.PMS.Entity.Application;
import com.example.PMS.Entity.Job;
import com.example.PMS.Service.ApplicationService;
import com.example.PMS.Service.JobService;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    public AdminController(JobService jobService,
                           ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @PostMapping("/job")
    public Job addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }
}
