package cl.course.admin.model.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Embeddable
public class Course {

    private String name;
    @Id
    private String code;

    @Transient
    private List<Student> listStudent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }
}
