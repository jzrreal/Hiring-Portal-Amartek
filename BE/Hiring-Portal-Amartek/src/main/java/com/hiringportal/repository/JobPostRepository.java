package com.hiringportal.repository;

import com.hiringportal.entities.JobPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    @Query(value = """
            select count(jp.id) from JobPost jp
            """)
    Integer getTotalJobPost();

    @Query(value = """
            select jp from JobPost jp
            """)
    List<JobPost> findAllFiveNewestJobPost(PageRequest pageRequest);

}