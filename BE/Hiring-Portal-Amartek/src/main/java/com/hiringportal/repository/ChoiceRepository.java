package com.hiringportal.repository;

import com.hiringportal.entities.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
    @Query(value = """
                select c from Choice c where c.question.questionId = :questionId
            """)
    List<Choice> findAllByQuestionId(Integer questionId);

    @Modifying
    @Query(value = """
                delete from Choice c where c.question.questionId = :questionId
            """)
    void deleteAllChoiceByQuestionId(Integer questionId);

    @Query(value = """
            select c from Choice c where c.question.questionId in :idQuestions
            """)
    List<Choice> findAllChoiceInListQuestionId(List<Integer> idQuestions);
}
