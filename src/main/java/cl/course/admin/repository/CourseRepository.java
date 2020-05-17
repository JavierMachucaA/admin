package cl.course.admin.repository;

import cl.course.admin.model.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    Course getByCode(String code);
    List<Course> findAll();
    List<Course> getAllBy(Pageable pageable);
    Integer deleteByCode(String code);
}
