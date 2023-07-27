package com.hiringportal.repository;

import com.hiringportal.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository <Questions, Integer> {
}
