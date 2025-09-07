package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {

    @Getter(onMethod_ = {@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;
    private String name;


    public Student(String name) {
        this.name = name;
    }

    @EqualsAndHashCode.Exclude
    @Getter(onMethod_ = {@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}),
            @JoinTable(
                    name = "student_course",
                    joinColumns = @JoinColumn(name = "student_id"),
                    inverseJoinColumns = @JoinColumn(name = "course_id")
            )})
    Set<Course> courses = new HashSet<>();

    public void enroll(Course... course) {
        for (Course c : course) {
            c.getStudents().add(this);
            courses.add(c);
        }
    }


}