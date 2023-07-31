package com.hiringportal.repository;

import com.hiringportal.dto.QuestionAnswerQuery;
import com.hiringportal.entities.Questions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {

    @Query(value = """
            select q from Questions q where q.segment = 'DATABASE' and q.questionLevel.questionLevelId = 3 order by rand()
                """)
    Questions findRandomOneHardQuestionDatabase(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'BASIC_PROGRAMMING' and q.questionLevel.questionLevelId = 3 order by rand()
                """)
    Questions findRandomOneHardQuestionBasicProgramming(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'LOGIKA_MATEMATIKA' and q.questionLevel.questionLevelId = 3 order by rand()
                """)
    Questions findRandomOneHardQuestionLogikaMatematika(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'DATABASE' and q.questionLevel.questionLevelId = 2 order by rand()
                """)
    Questions findRandomOneMediumQuestionDatabase(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'BASIC_PROGRAMMING' and q.questionLevel.questionLevelId = 2 order by rand()
                """)
    Questions findRandomOneMediumQuestionBasicProgramming(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'LOGIKA_MATEMATIKA' and q.questionLevel.questionLevelId = 2 order by rand()
                """)
    Questions findRandomOneMediumQuestionLogikaMatematika(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'DATABASE' and q.questionLevel.questionLevelId = 1 order by rand()
                """)
    List<Questions> findRandomThreeEasyQuestionDatabase(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'BASIC_PROGRAMMING' and q.questionLevel.questionLevelId = 1 order by rand()
                """)
    List<Questions> findRandomThreeEasyQuestionBasicProgramming(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.segment = 'LOGIKA_MATEMATIKA' and q.questionLevel.questionLevelId = 1 order by rand()
                """)
    List<Questions> findRandomThreeEasyQuestionLogikaMatematika(PageRequest pageRequest);
    @Query(value = """
            select q from Questions q where q.questionId in :idQuestions
            """)
    List<Questions> findAllQuestionInListQuestionId(List<Integer> idQuestions);
    @Query(value = """
            select new com.hiringportal.dto.QuestionAnswerQuery(q.questionId, ql.questionLevelId, c.choiceId) from Questions q
            join QuestionLevel ql on q.questionLevel.questionLevelId = ql.questionLevelId
            join Choice c on c.question.questionId = q.questionId
            where q.questionId in :idQuestions and c.correct is true
            """)
    List<QuestionAnswerQuery> findAllQuestionIdQuestionLevelIdCorrectChoiceIdInListQuestionId(List<Integer> idQuestions);
    @Query(value = """
            select count(q.questionId) from Questions q where q.questionLevel.questionLevelId = 1
            """)
    Integer getTotalEasyQuestion();
    @Query(value = """
            select count(q.questionId) from Questions q where q.questionLevel.questionLevelId = 2
            """)
    Integer getTotalMediumQuestion();
    @Query(value = """
            select count(q.questionId) from Questions q where q.questionLevel.questionLevelId = 3
            """)
    Integer getTotalHardQuestion();
    @Query(value = """
            select q from Questions q 
            """)
    List<Questions> findFiveLatestQuestion(PageRequest pageRequest);
}
