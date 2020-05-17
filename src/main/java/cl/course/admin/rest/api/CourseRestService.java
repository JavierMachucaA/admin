package cl.course.admin.rest.api;

import cl.course.admin.model.domain.Course;
import cl.course.admin.model.response.CoursePageablerResponse;
import cl.course.admin.model.response.CourseResponse;
import cl.course.admin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseRestService {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public CoursePageablerResponse getPaginated(@RequestParam("page") Integer page,
                                                                @RequestParam("items") Integer items){
        return this.courseService.getPage(page, items);
    }

    @GetMapping("/all")
    public CoursePageablerResponse getAll(){
        return this.courseService.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<CourseResponse> getByCode(@PathVariable("code") String code) {
        CourseResponse courseResponse = this.courseService.getByCode(code);
        if (courseResponse.getOk()) {
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courseResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody Course course) {
        CourseResponse courseResponse = this.courseService.createCourse(course);
        if (courseResponse.getOk()) {
            return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<CourseResponse> editCourse(@RequestBody Course course){
        CourseResponse courseResponse = this.courseService.editCourse(course);
        if (courseResponse.getOk()) {
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<CourseResponse> deleteCourse(@PathVariable("code") String code) {
        CourseResponse courseResponse = this.courseService.deleteCode(code);
        if (courseResponse.getOk()){
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(courseResponse, HttpStatus.NOT_FOUND);
        }
    }

}
