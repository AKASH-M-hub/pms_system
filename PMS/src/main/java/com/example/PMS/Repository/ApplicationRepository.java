package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PMS.Entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // ✅ THIS PREVENTS DUPLICATES
    boolean existsByStudentStudentIdAndJobJobId(Long studentId, Long jobId);
}
