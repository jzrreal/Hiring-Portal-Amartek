package com.hiringportal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_m_job_levels")
public class JobLevels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_level_id")
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "jobLevels")
    public List<JobPosts> jobPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "jobLevels")
    public List<WorkExperience> workExperiences;
}
