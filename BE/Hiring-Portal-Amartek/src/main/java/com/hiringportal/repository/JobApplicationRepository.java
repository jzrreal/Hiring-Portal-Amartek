package com.hiringportal.repository;

import com.hiringportal.dto.JobApplicationResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.JobApplication;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    @Query(value = """
                select new com.hiringportal.dto.JobApplicationResponse(ja.id, ja.apply_date, jp.title, jp.jobFunction.name, jp.jobLevel.name, jas.name) from JobApplication ja
                join JobPost jp on ja.jobPost.id = jp.id
                join CandidateProfile cp on ja.candidateProfile.id = cp.id
                join JobApplicationStatus jas on ja.jobApplicationStatus.id = jas.id
                where cp.id = :candidateProfileId
            """)
    List<JobApplicationResponse> findFirstFiveJobApplicationByCandidateProfileId(Integer candidateProfileId, PageRequest pageRequest);

}