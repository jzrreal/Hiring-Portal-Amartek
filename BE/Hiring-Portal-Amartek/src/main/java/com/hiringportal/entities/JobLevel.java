package com.hiringportal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_m_job_levels")
public class JobLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_level_id")
    private Integer id;
    @NotBlank
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "jobLevel")
    public List<JobPost> jobPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "jobLevel")
    public List<WorkExperience> workExperiences;
}
