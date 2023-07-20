package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.EducationHistory;

@Repository
public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Integer> {
    
}
