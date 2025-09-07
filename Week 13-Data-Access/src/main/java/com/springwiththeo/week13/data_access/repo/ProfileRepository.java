package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
