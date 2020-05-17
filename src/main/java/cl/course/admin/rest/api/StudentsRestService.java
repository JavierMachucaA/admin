package cl.course.admin.rest.api;

import cl.course.admin.model.domain.Student;
import cl.course.admin.model.response.StudentResponse;
import cl.course.admin.model.response.StudentsPageableResponse;
import cl.course.admin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsRestService {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public StudentsPageableResponse getPaginated(@RequestParam("page") Integer page,
                                                 @RequestParam("items") Integer items
                                        ) {
        return this.studentService.getPage(page, items);
    }

    @GetMapping("/all")
    public StudentsPageableResponse getAll() {
        return this.studentService.getAll();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<StudentResponse> getByRun(@PathVariable("rut") String rut){
        StudentResponse studentResponse = this.studentService.getByRut(rut);
        if (studentResponse.getOk()) {
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studentResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody Student student){
        StudentResponse studentResponse = this.studentService.createStudent(student);
        if (studentResponse.getOk()) {
            return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<StudentResponse> editStudent(@RequestBody Student student) {
        StudentResponse studentResponse = this.studentService.editStudent(student);
        if (studentResponse.getOk()) {
            return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable("rut") String rut) {
        StudentResponse studentResponse = this.studentService.deleteStudent(rut);
        if (studentResponse.getOk()) {
            return new ResponseEntity<>(studentResponse, HttpStatus.OK );
        } else {
            return new ResponseEntity<>(studentResponse, HttpStatus.NOT_FOUND);
        }
    }

}
