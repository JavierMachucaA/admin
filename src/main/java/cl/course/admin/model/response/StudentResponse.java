package cl.course.admin.model.response;

import java.io.Serializable;

public class StudentResponse implements Serializable {
    private Boolean ok;
    private String message;
    private Object data;

    public StudentResponse(Boolean ok, String message, Object data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
