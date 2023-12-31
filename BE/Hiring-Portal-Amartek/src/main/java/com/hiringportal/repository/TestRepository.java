package com.hiringportal.repository;

import com.hiringportal.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {

    Test findFirstByJobApplication_Id(Integer jobApplicationId);

    @Modifying
    @Query(value = """
            delete from Test t where t.testId = :testId
                """)
    void deleteTestById(Integer testId);

    Optional<Test> findTestByTestToken(String token);

    @Query(value = """
            select t from Test t
            where t.result is null and (t.endTest < :date or t.endAt < :date)
            """)
    List<Test> findAllExpiredTestAndResultIsNull(Date date);
}
