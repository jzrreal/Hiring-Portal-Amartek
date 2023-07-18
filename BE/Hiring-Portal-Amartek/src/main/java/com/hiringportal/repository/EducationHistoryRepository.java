package com.hiringportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiringportal.entities.EducationHistory;

public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Integer> {
    
}
