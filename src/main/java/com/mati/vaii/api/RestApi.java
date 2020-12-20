package com.mati.vaii.api;
import com.mati.vaii.model.Courses;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mati.vaii.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    {
        Courses course = courseService.findById(courseId);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping("/courses")
    public Courses createCourse(@Valid @RequestBody Courses course) {
        return courseService.postCourses(course);
    }

    @PutMapping("/courses")
    public ResponseEntity<Courses> updateCourse(@Valid @RequestBody Courses courseDetails)
    {
        final Courses updatedCourse = courseService.updateCourses(courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long id)
    {
        courseService.deleteCourses(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
