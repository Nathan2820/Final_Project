package com.qa.sfia3.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cohort {

    @Id
    @GeneratedValue
    private Long cohortId;

    @Column
    private String name;

    @OneToMany(mappedBy = "cohort", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Trainee> trainees = new ArrayList<>();

    public Cohort() {
    }

    public Cohort(String name) {
        this.name = name;
    }

    public Long getCohortId() { return cohortId; }

    public void setCohortId(Long cohortId) { this.cohortId = cohortId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Trainee> getTrainees() { return trainees; }

    public void setTrainees(List<Trainee> trainees) { this.trainees = trainees; }
}
