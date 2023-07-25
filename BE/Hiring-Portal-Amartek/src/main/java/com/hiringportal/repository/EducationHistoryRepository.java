package com.hiringportal.repository;

import com.hiringportal.dto.EducationHistoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hiringportal.entities.EducationHistory;

import java.util.List;

@Repository
public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Integer> {
    @Query(value = """
                select new com.hiringportal.dto.EducationHistoryQuery(er.level, er.name, er.major, er.candidateProfile.id)
                from EducationHistory er
                where er.candidateProfile.id in :idCandidateProfiles and er.level = 'S1'
            """)
    List<EducationHistoryQuery> findAllEducationHistoryInListCandidateIdWhereS1(List<Integer> idCandidateProfiles);

}
