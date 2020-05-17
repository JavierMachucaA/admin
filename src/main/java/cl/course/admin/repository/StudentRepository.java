package cl.course.admin.repository;

import cl.course.admin.model.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {

    List<Student> findAll();
    List<Student> getAllBy(Pageable pageable);
    Student getByRut(String rut);
    Integer deleteByRut(String rut);
}
