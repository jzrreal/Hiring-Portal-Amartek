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
                join ApplicationStatus jas on ja.applicationStatus.id = jas.id
                where cp.id = :candidateProfileId
            """)
    List<JobApplicationResponse> findFirstFiveJobApplicationByCandidateProfileId(Integer candidateProfileId, PageRequest pageRequest);

    @Query(value = """
            select ja from JobApplication ja
            join ApplicationStatus aps on ja.applicationStatus.id = aps.id
            join CandidateProfile cp on ja.candidateProfile.id = cp.id
            where ja.jobPost.id = :jobPostId
            """)
    List<JobApplication> findAllByJobPostId(Integer jobPostId);
    @Query(value = """
            select ja from JobApplication ja
            join ApplicationStatus aps on ja.applicationStatus.id = aps.id
            join CandidateProfile cp on ja.candidateProfile.id = cp.id
            where ja.candidateProfile.id = :candidateProfileId
            """)
    List<JobApplication> findAllByCandidateProfileId(Integer candidateProfileId);
    @Query(value = """
            select count(ja.id) from JobApplication ja
            """)
    Integer getTotalApply();
    @Query(value = """
            select ja
            from JobApplication ja
            join JobPost jp on ja.jobPost.id = jp.id
            join CandidateProfile cp on ja.candidateProfile.id = cp.id
            join ApplicationStatus jas on ja.applicationStatus.id = jas.id
                """)
    List<JobApplication> findFirstFiveJobApplication(PageRequest pageRequest);


    Boolean existsByJobPost_IdAndCandidateProfile_Id(Integer jobPostId, Integer candidateProfileId);
}