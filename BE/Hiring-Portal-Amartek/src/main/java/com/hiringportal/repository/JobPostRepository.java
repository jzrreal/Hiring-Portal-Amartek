package com.hiringportal.repository;

import com.hiringportal.dto.JobPostChartResponse;
import com.hiringportal.entities.JobPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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
    @Query(value = """
            select jp from JobPost jp
            where jp.open_until < :date and (jp.closed is null or jp.closed is false )
            """)
    List<JobPost> findAllExpiredJobPost(Date date);
    @Query(value = """
                select new com.hiringportal.dto.JobPostChartResponse(jf.name, count(jap.id)) from JobPost jp
                join JobApplication jap on jp.id = jap.jobPost.id
                right join JobFunction jf on jp.jobFunction.id = jf.id
                group by jf.name
            """)
    List<JobPostChartResponse> getChartByJobFunction();

}