package cl.course.admin.repository;

import cl.course.admin.model.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
    Course getByCode(String code);
}
