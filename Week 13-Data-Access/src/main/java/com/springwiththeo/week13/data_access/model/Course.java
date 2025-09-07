package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data

public class Course {
    @Getter(onMethod_ = {@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;

    private String name;

    public Course(String name) {
        this.name = name;
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(onMethod_ = @ManyToMany(mappedBy = "courses"))
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student... students) {
        for (Student student : students) {
            student.enroll(this);
        }
    }

}
