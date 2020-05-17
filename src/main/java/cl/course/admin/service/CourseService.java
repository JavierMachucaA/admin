package cl.course.admin.service;

import cl.course.admin.model.domain.Course;
import cl.course.admin.model.response.CoursePageablerResponse;
import cl.course.admin.model.response.CourseResponse;

public interface CourseService {
    CoursePageablerResponse getAll() ;
    CoursePageablerResponse getPage(Integer page, Integer items) ;
    CourseResponse getByCode(String code) ;
    CourseResponse createCourse(Course course);
    CourseResponse editCourse(Course course);
    CourseResponse deleteCode(String code);
}
