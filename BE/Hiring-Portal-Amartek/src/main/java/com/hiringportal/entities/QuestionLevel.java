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
@Table(name = "tb_m_question_levels")
public class QuestionLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_level_id")
    private Integer questionLevelId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "point", nullable = false)
    private int point;

}
