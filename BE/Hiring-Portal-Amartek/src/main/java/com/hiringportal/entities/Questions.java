package com.hiringportal.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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

    @Column(name = "question_level_id", nullable = false)
    private Integer questionLevelId;

    @Enumerated(EnumType.STRING)
    @Column(name = "segment", nullable = false)
    private Segment segment;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

//    @ManyToOne
//    @JoinColumn(name = "question_level_id", referencedColumnName = "question_level_id", insertable = false, updatable = false)
//    private QuestionLevel questionLevel;

    public enum Segment {
        DATABASE,
        BASIC_PROGRAMMING,
        LOGIKA_MATEMATIKA
    }
}
