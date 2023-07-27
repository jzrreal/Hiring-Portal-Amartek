package com.hiringportal.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_tr_tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Integer testId;

    @NotNull
    @Column(name = "start_at")
    private Date startAt;

    @NotNull
    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "result")
    private Integer result;
    @Column(name = "end_test")
    private Date endTest;

    @NotNull
    @Column(name = "test_token")
    private String testToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_application_id", referencedColumnName = "job_application_id")
    private JobApplication jobApplication;

    @OneToMany(mappedBy = "test")
    private List<TestQuestion> testQuestions;

}
