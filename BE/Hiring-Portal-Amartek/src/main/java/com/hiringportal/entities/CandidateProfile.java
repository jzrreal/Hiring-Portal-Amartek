package com.hiringportal.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "tb_m_candidate_profiles")
public class CandidateProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_profile_id")
    private Integer id;

    private String phone;
    @Column(columnDefinition = "text")
    private String summary;
    private Date birthDate;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "candidateProfile")
    @JsonIgnore
    private List<EducationHistory> educationHistories;
    @OneToMany(mappedBy = "candidateProfile")
    @JsonIgnore
    private List<Achievement> achievements;
    @OneToMany(mappedBy = "candidateProfile")
    @JsonIgnore
    private List<Certificate> certificates;
    @OneToMany(mappedBy = "candidateProfile")
    @JsonIgnore
    private List<Skill> skills;
    @OneToMany(mappedBy = "candidateProfile")
    @JsonIgnore
    private List<WorkExperience> workExperiences;
}
