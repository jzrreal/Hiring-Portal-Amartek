package com.hiringportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hiringportal.enums.Segment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_tr_questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @NotBlank
    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "segment", nullable = false, columnDefinition = "enum")
    private Segment segment;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_level_id", referencedColumnName = "question_level_id")
    private QuestionLevel questionLevel;


    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Choice> choices;


    @OneToMany(mappedBy = "questions")
    @JsonIgnore
    private List<TestQuestion> testQuestions;
}
