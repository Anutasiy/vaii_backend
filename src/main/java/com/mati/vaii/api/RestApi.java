package com.mati.vaii.api;

import com.mati.vaii.model.Courses;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mati.vaii.repository.CoursesRepository;
import com.mati.vaii.service.CourseService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RestApi {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses")
    public List<Courses> getAllCourses(){
        return courseService.getCourses();
    }

    @GetMapping(value = "/courses/{id}")
    public ResponseEntity<Courses> getCoursesByID(@PathVariable(value = "id") Long courseId)
       // throws ResourceNotFoundException
       {
        Courses course = courseService.findById(courseId);
       //     .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + courseId));
        return ResponseEntity.ok().body(course);

    }

    @PostMapping("/courses")
    public Courses createCourse(@RequestBody Courses course) {
        return courseService.postCourses(course);
    }

    @PutMapping("/courses")
    public ResponseEntity<Courses> updateCourse(@RequestBody Courses courseDetails)
         //   throws ResourceNotFoundException
         {
//         Courses course = courseService.findById(courseId);
//         //       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        course.setNazov(courseDetails.getNazov());
//        course.setCena(courseDetails.getCena());
//        course.setPopis(courseDetails.getPopis());
        final Courses updatedCourse = courseService.updateCourses(courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long id)
          //  throws ResourceNotFoundException {
    {
       // Courses course = courseService.findById(courseId);
          //      .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        courseService.deleteCourses(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
