package com.hiringportal.service.question;

import com.hiringportal.dto.*;
import com.hiringportal.entities.Choice;
import com.hiringportal.entities.QuestionLevel;
import com.hiringportal.entities.Questions;
import com.hiringportal.repository.ChoiceRepository;
import com.hiringportal.repository.QuestionLevelRepository;
import com.hiringportal.repository.QuestionRepository;
import com.hiringportal.service.ValidationService;
import com.hiringportal.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final ValidationService validationService;
    private final QuestionLevelRepository questionLevelRepository;

    @Override
    public List<QuestionResponse> getAllQuestion() {
        List<QuestionResponse> questionResponses = questionRepository.findAll()
                .stream().map(questions -> QuestionResponse.builder()
                        .createdAt(questions.getCreatedAt().toLocalDateTime())
                        .id(questions.getQuestionId())
                        .question(questions.getQuestion())
                        .segment(WordUtil.capitalizeEachLetter(questions.getSegment().toString().replace("_", " ").toLowerCase()))
                        .questionLevel(questions.getQuestionLevel().getName())
                        .build())
                .collect(Collectors.toList());

        return questionResponses;
    }

    @Override
    public QuestionDetailResponse getQuestionById(Integer questionId) {
        Questions questions = getQuestionByIdOrThrow(questionId);

        return setQuestionAndChoiceToQuestionResponse(
                questions,
                choiceRepository.findAllByQuestionId(questionId)
        );
    }

    @Override
    public QuestionDetailResponse saveQuestionAndChoice(QuestionRequest request) {
        validationService.validate(request);

        QuestionLevel questionLevel = questionLevelRepository.findById(request.getQuestionLevelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Level with Id : " + request.getQuestionLevelId() + " not found"));

        Questions questions = Questions.builder()
                .questionLevel(questionLevel)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .question(request.getQuestion())
                .segment(request.getSegment())
                .build();
        questionRepository.save(questions);

        List<Choice> choices = request.getChoiceRequests()
                .stream().map(choiceRequest -> Choice.builder()
                        .question(questions)
                        .choice(choiceRequest.getChoice())
                        .correct(choiceRequest.getCorrect())
                        .build())
                .collect(Collectors.toList());

        choiceRepository.saveAll(choices);

        return setQuestionAndChoiceToQuestionResponse(
                questions, choices
        );
    }

    @Override
    public QuestionDetailResponse updateQuestionAndChoice(QuestionRequest request) {
        validationService.validate(request);

        Questions questions = getQuestionByIdOrThrow(request.getId());

        QuestionLevel questionLevel = questionLevelRepository.findById(request.getQuestionLevelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Level with Id : " + request.getQuestionLevelId() + " not found"));

        questions.setQuestion(request.getQuestion());
        questions.setQuestionLevel(questionLevel);
        questions.setSegment(request.getSegment());

        questionRepository.save(questions);

        List<Choice> choices = choiceRepository.findAllByQuestionId(request.getId());

        for (int i = 0; i < 5; i++) {
            Choice choice = choices.get(i);
            ChoiceRequest choiceReq = request.getChoiceRequests().get(i);

            choice.setChoice(choiceReq.getChoice());
            choice.setCorrect(choiceReq.getCorrect());

            choices.set(i, choice);
        }

        choiceRepository.saveAll(choices);

        return setQuestionAndChoiceToQuestionResponse(questions, choices);
    }

    @Override
    public void deleteQuestionAndChoice(Integer questionId) {
        getQuestionByIdOrThrow(questionId);

        choiceRepository.deleteAllChoiceByQuestionId(questionId);

        questionRepository.deleteById(questionId);

    }

    private Questions getQuestionByIdOrThrow(Integer questionId) {
        return questionRepository.findById(questionId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with Id " + questionId + " not found")
        );
    }

    private QuestionDetailResponse setQuestionAndChoiceToQuestionResponse(Questions questions, List<Choice> choices) {
        List<ChoiceResponse> choiceResponses = choices.stream().map(choice ->
                ChoiceResponse.builder()
                        .correct(choice.isCorrect())
                        .choice(choice.getChoice())
                        .id(choice.getChoiceId())
                        .build()).toList();

        return QuestionDetailResponse.builder()
                .questionLevel(questions.getQuestionLevel().getName())
                .id(questions.getQuestionId())
                .segment(questions.getSegment())
                .question(questions.getQuestion())
                .choices(choiceResponses)
                .build();
    }

    //    @Autowired
//    QuestionRepository questionRepository;
//    @Autowired
//    ValidationService validationService;
//    @Override
//    public List<Questions> getAll() {
//        return questionRepository.findAll();
//    }
//
//    @Override
//    public Questions getById(Integer id) {
//        return questionRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question  with Id " + id + " not found")
//        );
//    }
//
//    @Override
//    public Questions save(Questions entity) {
//        validationService.validate(entity);
//        var saveEntity = questionRepository.save(entity);
//        return saveEntity;
//    }
//
//    @Override
//    public Questions update(Questions entity) {
//        validationService.validate(entity);
//        var saveEntity = questionRepository.save(entity);
//        return saveEntity;
//    }
//
//    @Override
//    public Boolean delete(Integer id) {
//        questionRepository.deleteById(id);
//        return questionRepository.findById(id).isEmpty();
//    }
}
