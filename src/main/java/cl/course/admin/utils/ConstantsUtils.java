package cl.course.admin.utils;

public class ConstantsUtils {
    public static final String BEARER_TYPE = "Bearer ";
    public static final String AUTHORITY_USER = "USER";

    //Messages
    // errors
    public static final String MSJ_USER_IS_NULL = "[Error] User is null";
    public static final String MSJ_USER_IS_RUT_NULL = "[Error] User has field rut null";
    public static final String MSJ_USER_IS_NAME_NULL = "[Error] User has field name null";
    public static final String MSJ_USER_IS_LASTNAME_NULL = "[Error] User has field lastname null";
    public static final String MSJ_USER_IS_AGE_NULL = "[Error] User has field age null";
    public static final String MSJ_USER_IS_COUSE_NULL = "[Error] User has field course null";

    // validations
    public static final String MSJ_USER_NOT_EXIST = "[Valitation] User not exist";
    public static final String MSJ_USER_RUT_ALREDY_USE = "[Valitation] User has same rut";
    public static final String MSJ_USER_RUT_EXTENDS_LENGHT = "[Valitation] Rut field extends over allowed (12 characters)";
    public static final String MSJ_USER_RUT_NOT_RIGHT = "[Valitation] Rut is not valid";
    public static final String MSJ_USER_UNDER_EIGHTEEN_YEARS = "[Valitation] User is under 18 years old";
    public static final String MSJ_USER_IS_COUSE_NOT_EXIST = "[Valitation] Course of user not exist";

    // correct response
    public static final String MSJ_RUT_EXIST = "User exist.";
    public static final String MSJ_SUCCESS_CREATED = "User succefull created";
    public static final String MSJ_SUCCESS_EDIT = "User succefull edited";
    public static final String MSJ_STUDENTE_DELETED_SUCCES = "Succefull deleted";
}
