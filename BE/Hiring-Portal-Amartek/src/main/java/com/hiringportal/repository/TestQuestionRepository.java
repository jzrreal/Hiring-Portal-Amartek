package com.hiringportal.repository;

import com.hiringportal.dto.TestQuestionQuery;
import com.hiringportal.entities.TestQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer> {
    @Modifying
    @Query(value = """
            delete from TestQuestion tq where tq.test.testId = :testId
                """)
    void deleteAllTesQuestionByTestId(Integer testId);

    @Query(value = """
            select new com.hiringportal.dto.TestQuestionQuery(tq.testQuestionId, q.questionId, tq.answer)
            from TestQuestion tq
            join Questions q on tq.questions.questionId = q.questionId
            where tq.test.testId = :testId
                        """)
    List<TestQuestionQuery> getAllTestQuestionByTestId(Integer testId);
    @Query(value = """
            select new com.hiringportal.dto.TestQuestionQuery(tq.testQuestionId, q.questionId, tq.answer)
            from TestQuestion tq
            join Questions q on tq.questions.questionId = q.questionId
            where tq.test.testId = :testId
                        """)
    Page<TestQuestionQuery> getPageAllTestQuestionByTestId(Integer testId, Pageable pageable);

    @Query(value = """
            select tq from TestQuestion tq
            join Questions q on tq.questions.questionId = q.questionId
            where tq.testQuestionId = :testQuestionId and q.questionId = :questionId
                        """)
    Optional<TestQuestion> existsByTestQuestionIdAndQuestionId(Integer testQuestionId, Integer questionId);
    @Modifying
    @Query(value = """
            delete from TestQuestion tq where tq.questions.questionId = :questionId
            """)
    void deleteAllTesQuestionByQuestionId(Integer questionId);
}
