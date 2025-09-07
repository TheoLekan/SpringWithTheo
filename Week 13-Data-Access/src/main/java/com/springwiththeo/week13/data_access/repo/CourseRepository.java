package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
