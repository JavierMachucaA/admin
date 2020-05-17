package cl.course.admin.model.response;

import java.io.Serializable;

public class CourseResponse implements Serializable {
    private Boolean ok;
    private String message;
    private Object data;

    public CourseResponse(Boolean ok, String message, Object data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public Boolean getOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
