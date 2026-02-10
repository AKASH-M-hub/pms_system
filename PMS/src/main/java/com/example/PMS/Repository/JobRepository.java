package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PMS.Entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
