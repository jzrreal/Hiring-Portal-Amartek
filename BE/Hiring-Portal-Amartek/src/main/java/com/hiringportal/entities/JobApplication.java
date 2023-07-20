package com.hiringportal.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tr_job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Integer id;

    private Date apply_date;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;
    
     @ManyToOne
     @JoinColumn(name = "cadidate_profile_id")
     private CandidateProfile candidateProfile;
    
    @ManyToOne
    @JoinColumn(name = "application_status_id")
    private JobApplicationStatus jobApplicationStatus;
}