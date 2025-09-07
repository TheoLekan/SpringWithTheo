package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Profile {
    @Getter(onMethod_ = {@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;
    private String bio;
    private String website;

    public Profile(String bio, String website) {
        this.bio = bio;
        this.website = website;
    }
}
