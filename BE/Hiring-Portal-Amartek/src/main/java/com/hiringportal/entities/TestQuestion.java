package com.hiringportal.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_tr_test_questions")
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_question_id")
    private Integer testQuestionId;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id", nullable = false)
    private Questions questions;

    @Column(name = "answer")
    private Integer answer;

}
