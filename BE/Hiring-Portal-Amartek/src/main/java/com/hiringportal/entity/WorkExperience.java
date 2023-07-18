package com.hiringportal.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "tb_tr_work_experiences")
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_experience_id")
    private Integer id;

    @NotBlank
    private String title;
    @NotBlank
    private String company;
    @NotNull
    private Date startDate;
    private Boolean current;
    @Column(columnDefinition = "text")
    private String description;
    private Date until;

//    @ManyToOne
//    @JoinColumn(name = "job_function_id")
//    private JobFunction jobFunction;

//    @ManyToOne
//    @JoinColumn(name = "job_level_id")
//    private JobLevel jobLevel;

//    @ManyToOne
//    @JoinColumn(name = "candidate_profile_id")
//    private CandidateProfile candidateProfile;
}
