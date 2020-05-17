package cl.course.admin.utils;

public class ConstantsUtils {
    public static final String BEARER_TYPE = "Bearer ";
    public static final String AUTHORITY_USER = "USER";

    //Messages
    // errors
    public static final String MSJ_STUDENT_IS_NULL = "[Error] Student is null";
    public static final String MSJ_STUDENT_IS_RUT_NULL = "[Error] Student has field rut null";
    public static final String MSJ_STUDENT_IS_NAME_NULL = "[Error] Student has field name null";
    public static final String MSJ_STUDENT_IS_LASTNAME_NULL = "[Error] Student has field lastname null";
    public static final String MSJ_STUDENT_IS_AGE_NULL = "[Error] Student has field age null";
    public static final String MSJ_STUDENT_IS_COUSE_NULL = "[Error] Student has field course null";

    public static final String MSJ_COURSE_IS_NULL = "[Error] Course is null";
    public static final String MSJ_COURSE_IS_CODE_NULL = "[Error] Course has field code null";
    public static final String MSJ_COURSE_IS_NAME_NULL = "[Error] Course has field name null";

    // validations
    public static final String MSJ_STUDENT_NOT_EXIST = "[Valitation] Student not exist";
    public static final String MSJ_STUDENT_RUT_ALREDY_USE = "[Valitation] Student has same rut";
    public static final String MSJ_STUDENT_RUT_EXTENDS_LENGHT = "[Valitation] Rut field extends over allowed (12 characters)";
    public static final String MSJ_STUDENT_RUT_NOT_RIGHT = "[Valitation] Rut is not valid";
    public static final String MSJ_STUDENT_UNDER_EIGHTEEN_YEARS = "[Valitation] Student is under 18 years old";
    public static final String MSJ_STUDENT_IS_COUSE_NOT_EXIST = "[Valitation] Course of Student not exist";

    public static final String MSJ_COURSE_NOT_EXIST = "[Valitation] Course not exist";
    public static final String MSJ_COURSE_ALREADY_EXIST = "[Valitation] Course already exist";

    // correct response
    public static final String MSJ_RUT_EXIST = "Student exist.";
    public static final String MSJ_STUDENT_SUCCESS_CREATED = "Student succefull created";
    public static final String MSJ_STUDENT_SUCCESS_EDIT = "Student succefull edited";
    public static final String MSJ_STUDENT_DELETED_SUCCES = "Succefull deleted";

    public static final String MSJ_COURSE_EXIST = "Course exist.";
    public static final String MSJ_COURSE_SUCCESS_CREATED = "Course succefull created";
    public static final String MSJ_COURSE_SUCCESS_EDITED = "Course succefull edited";
    public static final String MSJ_COURSE_DELETED_SUCCES = "Succefull deleted";
}
