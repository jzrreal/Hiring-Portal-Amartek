package com.hiringportal.service.onlineTest;

import com.hiringportal.dto.ChoiceTestResponse;
import com.hiringportal.dto.QuestionAnswerQuery;
import com.hiringportal.dto.QuestionTestResponse;
import com.hiringportal.dto.TestQuestionQuery;
import com.hiringportal.entities.*;
import com.hiringportal.repository.*;
import com.hiringportal.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineTestServiceImpl implements OnlineTestService {

    private final TestRepository testRepository;
    private final ChoiceRepository choiceRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuestionLevelRepository questionLevelRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final TestParameterRepository testParameterRepository;
    private final ApplicationStatusRepository applicationStatusRepository;
    private final EmailService emailService;

    @Override
    public void generateTestForCandidateProfile(Integer jobApplicationId) {

        Test testTemp = testRepository.findFirstByJobApplication_Id(jobApplicationId);
        if (testTemp != null) {
            //delete all test question where test id
            testQuestionRepository.deleteAllTesQuestionByTestId(testTemp.getTestId());

            //Delete test if already exist
            testRepository.deleteTestById(testTemp.getTestId());
        }


        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Application with Id : " + jobApplicationId + " not found"));

        CandidateProfile candidateProfile = candidateProfileRepository.findById(jobApplication.getCandidateProfile().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicant with Id " + jobApplication.getCandidateProfile().getId() + " not found"));

        TestParameter testParameter = testParameterRepository.findById(1).orElseThrow();

        //List storage of all question
        List<Questions> allQuestions = new ArrayList<>();

        //Query all random question
        Questions questionDatabaseHard = questionRepository.findRandomOneHardQuestionDatabase(PageRequest.of(0, 1));
        Questions questionBasicProgrammingHard = questionRepository.findRandomOneHardQuestionBasicProgramming(PageRequest.of(0, 1));
        Questions questionLogikaMatematikaHard = questionRepository.findRandomOneHardQuestionLogikaMatematika(PageRequest.of(0, 1));
        Questions questionDatabaseMedium = questionRepository.findRandomOneMediumQuestionDatabase(PageRequest.of(0, 1));
        Questions questionBasicProgrammingMedium = questionRepository.findRandomOneMediumQuestionBasicProgramming(PageRequest.of(0, 1));
        Questions questionLogikaMatematikaMedium = questionRepository.findRandomOneMediumQuestionLogikaMatematika(PageRequest.of(0, 1));
        List<Questions> questionDatabaseEasy = questionRepository.findRandomThreeEasyQuestionDatabase(PageRequest.of(0, 3));
        List<Questions> questionBasicProgrammingEasy = questionRepository.findRandomThreeEasyQuestionBasicProgramming(PageRequest.of(0, 3));
        List<Questions> questionLogikaMatematikaEasy = questionRepository.findRandomThreeEasyQuestionLogikaMatematika(PageRequest.of(0, 3));

        //store all question
        allQuestions.add(questionDatabaseHard);
        allQuestions.add(questionBasicProgrammingHard);
        allQuestions.add(questionLogikaMatematikaHard);
        allQuestions.add(questionDatabaseMedium);
        allQuestions.add(questionBasicProgrammingMedium);
        allQuestions.add(questionLogikaMatematikaMedium);

        //store all question
        allQuestions.addAll(questionDatabaseEasy);
        allQuestions.addAll(questionBasicProgrammingEasy);
        allQuestions.addAll(questionLogikaMatematikaEasy);

        //insert data to tb_tr_tests
        Test test = Test.builder()
                .startAt(new Date(System.currentTimeMillis())) //Test start from generate test
                .endAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(testParameter.getExpirationHour()))) //Test end from generate test + hour from test parameter
                .testToken(UUID.randomUUID().toString()) //Random token to send via email as test authentication
                .jobApplication(jobApplication) //Insert what job application to become test
                .build();
        testRepository.save(test);

        //Set job application status to test
        jobApplication.setApplicationStatus(
                applicationStatusRepository.findById(3).orElse(null));
        jobApplicationRepository.save(jobApplication);

        //Create list test question and save to tb_tr_test_questions
        List<TestQuestion> testQuestions =
                allQuestions.stream().map(
                        question -> TestQuestion.builder()
                                .test(test)
                                .questions(question)
                                .build()
                ).collect(Collectors.toList());
        testQuestionRepository.saveAll(testQuestions);

        //Send email to applicant
        emailService.sendEmailTest(
                candidateProfile.getUser().getFullName(),
                candidateProfile.getUser().getEmail(),
                test.getTestToken(),
                test.getEndAt()
        );

    }

    @Override
    public List<QuestionTestResponse> getAllQuestionTestByToken(String token) {
        TestParameter testParameter = testParameterRepository.findById(1).orElseThrow();
        Test test = testRepository.findTestByTestToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Test not found"));

        if (test.getResult() != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Test has been finished");
        }

        if (!new Date(System.currentTimeMillis()).before(test.getEndAt()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Test has been expired");

        if (test.getEndTest() != null && !new Date(System.currentTimeMillis()).before(test.getEndTest())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Test has been expired");
        }

        if (test.getEndTest() == null) {
            test.setEndTest(
                    new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(testParameter.getTestTimeMinute()))
            );
            testRepository.save(test);
        }

        //Nanti ganti jadi TestQuestionQuery yang isinya id test question sama id question aja
        List<TestQuestionQuery> testQuestions = testQuestionRepository.getAllTestQuestionByTestId(test.getTestId());

        //transform list<TestQuestionQuery> to list question_id
        List<Integer> idQuestions = testQuestions
                .stream().map(TestQuestionQuery::getQuestionId).toList();

        //Bikin map<integer, String> dimana integer itu adalah questionId dan string nya adalah question nya
        Map<Integer, String> questionMap =
                questionRepository.findAllQuestionInListQuestionId(idQuestions)
                        .stream().collect(Collectors.toMap(Questions::getQuestionId, Questions::getQuestion));

        //get all question and transform into  Map<Integer, Choice> where the key is question id
        Map<Integer, List<Choice>> choiceMap =
                choiceRepository.findAllChoiceInListQuestionId(idQuestions)
                        .stream().collect(Collectors.groupingBy(choice -> choice.getQuestion().getQuestionId()));

        return testQuestions.stream().map(
                testQuestion -> {
                    //Mapping choice to ChoiceTestResponse
                    List<ChoiceTestResponse> choiceTestResponses =
                            choiceMap.get(testQuestion.getQuestionId())
                                    .stream().map(
                                            choice -> ChoiceTestResponse.builder()
                                                    .choiceId(choice.getChoiceId())
                                                    .choice(choice.getChoice())
                                                    .build()
                                    ).toList();
                    //mapping testQuestion to QuestionTestResponse
                    return QuestionTestResponse.builder()
                            .choices(choiceTestResponses)
                            .testQuestionId(testQuestion.getTestQuestionId())
                            .questionId(testQuestion.getQuestionId())
                            .question(questionMap.get(testQuestion.getQuestionId()))
                            .answer(testQuestion.getAnswer())
                            .build();
                }
        ).toList();
    }

    @Override
    public LocalDateTime getEndTestByToken(String token) {
        Test test = testRepository.findTestByTestToken(token).orElseThrow();
        return test.getEndTest()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    @Async
    public void saveEachOnlineTestAnswer(Integer testQuestionId, Integer questionId, Integer choiceId) {
        TestQuestion testQuestion = testQuestionRepository
                .existsByTestQuestionIdAndQuestionId(testQuestionId, questionId)
                .orElse(null);

        if (testQuestion != null) {
            testQuestion.setAnswer(choiceId);
            testQuestionRepository.save(testQuestion);
        }
    }

    @Override
    public void saveAllAnswerAndGetResult(String token) {
        Test test = testRepository.findTestByTestToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Test not found"));

        if (test.getResult() != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Test has been finished");
        }

        Integer result = 0; //Initiate result total

        //Get all question level and mapping to map with key its id and value its point
        Map<Integer, Integer> questionLevelMap = questionLevelRepository.findAll()
                .stream().collect(Collectors.toMap(QuestionLevel::getQuestionLevelId, QuestionLevel::getPoint));

        //Get list that contains testQuestionId, questionId, and answer (choiceId)
        List<TestQuestionQuery> testQuestions =
                testQuestionRepository.getAllTestQuestionByTestId(test.getTestId());

        //Transform data into Map<Integer, Integer> with key questionId and value its answer
        Map<Integer, Integer> questionAnswerMap = testQuestions.stream()
                .collect(Collectors.toMap(TestQuestionQuery::getQuestionId, TestQuestionQuery::getAnswer));

        //Make list integer that contains all questionId
        List<Integer> idQuestions = testQuestions
                .stream().map(TestQuestionQuery::getQuestionId).toList();

        //Make dto for store questionId, questionLevelId, and choiceId that the true answer base on list questionId
        List<QuestionAnswerQuery> questionAnswerQueries =
                questionRepository.findAllQuestionIdQuestionLevelIdCorrectChoiceIdInListQuestionId(idQuestions);

        //forEach dto and compare with questionAnswerMap, and update the result variable until get final result
        //Remember there must be null answer, when applicant not answer the following question
        for (QuestionAnswerQuery questionAnswerQuery : questionAnswerQueries) {
            //Compare correct answer from db with applicant answer
            if (questionAnswerQuery.getChoiceId().equals(questionAnswerMap.get(questionAnswerQuery.getQuestionId()))){
                result += questionLevelMap.get(questionAnswerQuery.getQuestionLevelId());
            }
        }
        log.info("Result applicants with token {} is : {}", token, result);

        //Update result in table test
        test.setResult(result);
        testRepository.save(test);

        final float finalResult = (float) (result/24) * 100;
        log.info("Final result applicants with token {} is : {}", token, finalResult);
        //Send email to applicant with custom message, in this case i make condition if result less than 15
        //Email message contains rejection, and vice versa. Also send the final result

    }
}
