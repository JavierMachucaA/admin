package cl.course.admin.model;

import java.io.Serializable;
import java.util.Random;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}