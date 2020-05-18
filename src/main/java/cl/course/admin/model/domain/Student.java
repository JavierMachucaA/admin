package cl.course.admin.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private String rut;
    private String name;
    private String lastname;
    private Integer age;
    private String course;
    @Transient
    private Course courseItem;
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Course getCourseItem() {
        return courseItem;
    }

    public void setCourseItem(Course courseItem) {
        this.courseItem = courseItem;
    }
}
