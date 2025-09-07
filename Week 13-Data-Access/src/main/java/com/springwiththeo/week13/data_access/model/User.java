package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users") // "user" is a reserved keyword in some SQL databases
public class User {
    @Getter(onMethod_ = {@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;
    private String username;

    @Getter(onMethod_ = {@OneToOne(cascade = CascadeType.ALL), @JoinColumn(name = "profile_id")})
    private Profile profile;

    public User(String username) {
        this.username = username;
    }

    public User(String username, Profile profile) {
        this.username = username;
        this.profile = profile;
    }


}
