package com.mati.vaii.service;

import com.mati.vaii.model.Courses;
import com.mati.vaii.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CoursesRepository coursesRepository;

    public List<Courses> getCourses(){

        List<Courses> courses = coursesRepository.findAll();
        return courses;
    }

    public Courses postCourses(Courses course) {
        Courses courses = coursesRepository.save(course);
        return courses;
    }

    public Courses findById(Long courseId){
        Courses courses = coursesRepository.findById(courseId).orElse(null);
        return courses;
    }

    public Courses updateCourses(Courses course){
        Courses courseExists = coursesRepository.findById(course.getId()).orElse(null);
        Courses courses = null;
        if (courseExists != null) {
            courses = coursesRepository.save(course);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return courses;
    }

    public Courses deleteCourses(Long id){
        Courses courseExists = coursesRepository.findById(id).orElse(null);
        Courses courses = null;
        if (courseExists != null) {
            coursesRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return courses;
    }
}
