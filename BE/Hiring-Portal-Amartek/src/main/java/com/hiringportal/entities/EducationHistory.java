package com.hiringportal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hiringportal.enums.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "tb_tr_education_histories")
public class EducationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_history_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private EducationLevel level;
    private String name;
    private String major;

    @Column(columnDefinition = "year")
    private Integer yearStart;

    @Column(columnDefinition = "year")
    private Integer yearEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_profile_id")
    @JsonIgnore
    private CandidateProfile candidateProfile;

}
