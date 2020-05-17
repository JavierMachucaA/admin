package cl.course.admin.service;

import cl.course.admin.model.domain.Student;
import cl.course.admin.model.response.StudentResponse;
import cl.course.admin.model.response.StudentsPageableResponse;

public interface StudentService {

    StudentsPageableResponse getAll() ;
    StudentsPageableResponse getPage(Integer page, Integer items) ;
    StudentResponse getByRut(String rut) ;
    StudentResponse createStudent(Student student);
    StudentResponse editStudent(Student student);
    StudentResponse deleteStudent(String rut);
}
