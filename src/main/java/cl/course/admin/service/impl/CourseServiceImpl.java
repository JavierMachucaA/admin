package cl.course.admin.service.impl;

import cl.course.admin.model.domain.Course;
import cl.course.admin.model.response.CoursePageablerResponse;
import cl.course.admin.model.response.CourseResponse;
import cl.course.admin.repository.CourseRepository;
import cl.course.admin.repository.StudentRepository;
import cl.course.admin.service.CourseService;
import cl.course.admin.utils.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CoursePageablerResponse getAll() {
        CoursePageablerResponse coursePageablerResponse = new CoursePageablerResponse();
        List<Course> listCourse = this.courseRepository.findAll();
        listCourse.parallelStream().forEach(c ->c.setListStudent(this.studentRepository.findAllByCourse(c.getCode())));
        coursePageablerResponse.setListCourse(listCourse);
        coursePageablerResponse.setTotal(this.courseRepository.count());
        coursePageablerResponse.setOffset(0);
        coursePageablerResponse.setPage(-1);
        return coursePageablerResponse;
    }

    @Override
    public CoursePageablerResponse getPage(Integer page, Integer items) {
        CoursePageablerResponse coursePageablerResponse = new CoursePageablerResponse();
        Pageable pageOfItems = PageRequest.of(page, items);
        List<Course> listCourse = this.courseRepository.getAllBy(pageOfItems);
        listCourse.parallelStream().forEach(c ->c.setListStudent(this.studentRepository.findAllByCourse(c.getCode())));
        coursePageablerResponse.setListCourse(listCourse);
        coursePageablerResponse.setTotal(this.courseRepository.count());
        coursePageablerResponse.setOffset(items);
        coursePageablerResponse.setPage(page);
        return coursePageablerResponse;
    }

    @Override
    public CourseResponse getByCode(String code) {
        Course courseExist = this.courseRepository.getByCode(code);
        if (courseExist != null) {
            courseExist.setListStudent(this.studentRepository.findAllByCourse(code));
            return new CourseResponse(true, ConstantsUtils.MSJ_COURSE_EXIST, courseExist);
        } else {
            return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_NOT_EXIST, null);
        }
    }

    @Override
    public CourseResponse createCourse(Course course) {
        CourseResponse errors = this.getErrors(course);
        if (!errors.getOk()) {
            return errors;
        }

        CourseResponse validate = this.validateCourse(course, false);
        if (!validate.getOk()) {
            return validate;
        }

        CourseResponse courseResponse = new CourseResponse(true,ConstantsUtils.MSJ_COURSE_SUCCESS_CREATED, this.courseRepository.save(course));
        return courseResponse;
    }

    @Override
    public CourseResponse editCourse(Course course) {
        CourseResponse errors = this.getErrors(course);
        if (!errors.getOk()) {
            return errors;
        }

        CourseResponse validate = this.validateCourse(course, true);
        if (!validate.getOk()) {
            return validate;
        }

        CourseResponse courseResponse = new CourseResponse(true,ConstantsUtils.MSJ_COURSE_SUCCESS_EDITED, this.courseRepository.save(course));
        return courseResponse;
    }

    @Override
    public CourseResponse deleteCode(String code) {
        Course course = this.courseRepository.getByCode(code);
        if (course != null){
            this.courseRepository.deleteByCode(code);
            return new CourseResponse(true, ConstantsUtils.MSJ_COURSE_DELETED_SUCCES, code);
        } else {
            return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_NOT_EXIST, null);
        }
    }

    private CourseResponse getErrors(Course course){
        if (course==null) {
            return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_IS_NULL, null);
        }
        if (course.getCode() == null) {
            return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_IS_CODE_NULL, null);
        }
        if (course.getName() == null) {
            return  new CourseResponse(false, ConstantsUtils.MSJ_COURSE_IS_NAME_NULL, null);
        }
        return new CourseResponse(true, null, null);
    }

    private CourseResponse validateCourse(Course course, Boolean isEdit) {
        Course courseIsExist = this.courseRepository.getByCode(course.getCode());

        if (courseIsExist !=null) {
            if (!isEdit){
                return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_ALREADY_EXIST, null);
            }
        } else {
            if (isEdit) {
                return new CourseResponse(false, ConstantsUtils.MSJ_COURSE_NOT_EXIST, null);
            }
        }
        return new CourseResponse(true, null, null);
    }
}
