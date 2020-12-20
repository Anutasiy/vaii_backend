package com.mati.vaii.repository;

import com.mati.vaii.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
}
