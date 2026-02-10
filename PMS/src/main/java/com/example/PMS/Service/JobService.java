package com.example.PMS.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PMS.DTO.JobDTO;
import com.example.PMS.Entity.Job;
import com.example.PMS.Repository.ApplicationRepository;
import com.example.PMS.Repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public JobService(JobRepository jobRepository,
                      ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<JobDTO> getAllJobsForStudent(Long studentId) {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> response = new ArrayList<>();

        for (Job job : jobs) {

            JobDTO dto = new JobDTO();
            dto.setJobId(job.getJobId());
            dto.setTitle(job.getTitle());
            dto.setSalary(job.getSalary());
            dto.setEligibilityCgpa(job.getEligibilityCgpa());
            dto.setDeadlineDate(job.getDeadlineDate());

            boolean applied = applicationRepository
                    .existsByStudentStudentIdAndJobJobId(
                            studentId,
                            job.getJobId()
                    );

            dto.setApplied(applied);

            response.add(dto);
        }

        return response;
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }
}
