package com.hiringportal.repository;

import com.hiringportal.entities.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer> {
}
