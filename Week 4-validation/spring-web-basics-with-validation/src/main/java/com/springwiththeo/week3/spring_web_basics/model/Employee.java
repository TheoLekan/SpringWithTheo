package com.springwiththeo.week3.spring_web_basics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Getter(onMethod_ = {@Id, @GeneratedValue})
    private Long id;

    @Getter(onMethod_ = {@NotBlank(message = "Name must not be blank"), @Size(min = 2, max = 40)})
    private String name;

    @Getter(onMethod_ = {
            @Email(message = "Email must be a valid email address"),
            @NotBlank(message = "Email must not be blank")
    })
    private String email;

    @Getter(onMethod_ = {@NotNull,@NotBlank, @Size(min = 2, max = 30)})
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }
    public Employee(String name, String position, String email) {
        this.name = name;
        this.position = position;
        this.email= email;
    }
}
