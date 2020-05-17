package cl.course.admin.model.response;

import cl.course.admin.model.domain.Course;

import java.io.Serializable;
import java.util.List;

public class CoursePageablerResponse implements Serializable {
    private Integer page;
    private Integer offset;
    private Long total;
    private List<Course> listCourse;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(List<Course> listCourse) {
        this.listCourse = listCourse;
    }
}
