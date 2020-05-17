package cl.course.admin.service.impl;

import cl.course.admin.model.domain.Course;
import cl.course.admin.model.domain.Student;
import cl.course.admin.model.response.StudentResponse;
import cl.course.admin.model.response.StudentsPageableResponse;
import cl.course.admin.repository.CourseRepository;
import cl.course.admin.repository.StudentRepository;
import cl.course.admin.service.StudentService;
import cl.course.admin.utils.ConstantsUtils;
import cl.course.admin.utils.RutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentsPageableResponse getAll() {
        StudentsPageableResponse studentsPageableResponse = new StudentsPageableResponse();
        studentsPageableResponse.setListStudents(this.studentRepository.findAll());
        studentsPageableResponse.setTotal(this.studentRepository.count());
        studentsPageableResponse.setOffset(0);
        studentsPageableResponse.setPage(-1);
        return studentsPageableResponse;
    }

    @Override
    public StudentsPageableResponse getPage(Integer page, Integer items) {
        StudentsPageableResponse studentsPageableResponse = new StudentsPageableResponse();
        Pageable pageOfItems = PageRequest.of(page, items);
        studentsPageableResponse.setListStudents(this.studentRepository.getAllBy(pageOfItems));
        studentsPageableResponse.setTotal(this.studentRepository.count());
        studentsPageableResponse.setOffset(items);
        studentsPageableResponse.setPage(page);
        return studentsPageableResponse;
    }

    @Override
    public StudentResponse getByRut(String rut) {
        Student studentExist = this.studentRepository.getByRut(rut);
        if (studentExist != null) {
            return new StudentResponse(true, ConstantsUtils.MSJ_RUT_EXIST, studentExist);
        } else {
            return new StudentResponse(false, ConstantsUtils.MSJ_STUDENT_NOT_EXIST, null);
        }
    }

    @Override
    public StudentResponse createStudent(Student student) {
        StudentResponse errors = this.getErrors(student);
        if (!errors.getOk()) {
            return errors;
        }

        StudentResponse validate = this.validateStudent(student, false);
        if (!validate.getOk()) {
            return validate;
        }
        student.setRut(RutUtil.cleanRut(student.getRut()));
        StudentResponse studentResponse = new StudentResponse(true,ConstantsUtils.MSJ_STUDENT_SUCCESS_CREATED, this.studentRepository.save(student));
        return studentResponse;
    }

    @Override
    public StudentResponse editStudent(Student student) {
        StudentResponse errors = this.getErrors(student);
        if (!errors.getOk()) {
            return errors;
        }

        StudentResponse validate = this.validateStudent(student, true);
        if (!validate.getOk()) {
            return validate;
        }
        student.setRut(RutUtil.cleanRut(student.getRut()));
        StudentResponse studentResponse = new StudentResponse(true,ConstantsUtils.MSJ_STUDENT_SUCCESS_EDIT, this.studentRepository.save(student));
        return studentResponse;
    }

    @Override
    public StudentResponse deleteStudent(String rut) {
        Student studentExist = this.studentRepository.getByRut(rut);
        if (studentExist!=null) {
            this.studentRepository.deleteByRut(rut);
            return new StudentResponse(true, ConstantsUtils.MSJ_STUDENT_DELETED_SUCCES,rut);
        } else {
            return new StudentResponse(false, ConstantsUtils.MSJ_STUDENT_NOT_EXIST, null);
        }
    }

    private StudentResponse getErrors(Student student) {
        if (student == null){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_NULL, null);
        }
        if (student.getRut() == null){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_RUT_NULL, null);
        }
        if (student.getName() == null ){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_NAME_NULL, null);
        }
        if (student.getLastname() == null ){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_LASTNAME_NULL, null);
        }
        if (student.getAge() == null ){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_AGE_NULL, null);
        }
        if (student.getCourse() == null ){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_COUSE_NULL, null);
        }
        return new StudentResponse(true, null, null);
    }

    private StudentResponse validateStudent(Student student, Boolean edit) {
        Student studentBD = this.studentRepository.getByRut(student.getRut());
        if (studentBD != null) {
            if (!edit) {
                return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_RUT_ALREDY_USE, null);
            }
        } else {
            if (edit) {
                return new StudentResponse(false, ConstantsUtils.MSJ_STUDENT_NOT_EXIST, null);
            }
        }

        if (student.getRut().length() > 12) {
            return new StudentResponse(false, ConstantsUtils.MSJ_STUDENT_IS_COUSE_NULL, null);
        }

        if (!RutUtil.validateRut(student.getRut())) {
            return new StudentResponse(false, ConstantsUtils.MSJ_STUDENT_RUT_NOT_RIGHT, null);
        }

        if (student.getAge() < 18) {
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_UNDER_EIGHTEEN_YEARS, null);
        }

        if (student.getCourse().length() > 4) {
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_RUT_EXTENDS_LENGHT, null);
        }

        Course course = this.courseRepository.getByCode(student.getCourse());
        if (course == null){
            return new StudentResponse(false,ConstantsUtils.MSJ_STUDENT_IS_COUSE_NOT_EXIST, null);
        }
        return new StudentResponse(true, null, null);
    }
}
