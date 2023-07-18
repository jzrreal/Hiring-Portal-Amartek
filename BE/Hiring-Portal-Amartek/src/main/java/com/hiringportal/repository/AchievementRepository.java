package com.hiringportal.repository;

import com.hiringportal.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
}
