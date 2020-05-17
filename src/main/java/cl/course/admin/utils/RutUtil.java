package cl.course.admin.utils;

public class RutUtil {
    public static boolean validateRut(String rut) {
        boolean validation = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validation = true;
            }

        } catch (java.lang.NumberFormatException e) {
            System.err.println("Error : "+ e.getStackTrace());
            return false;
        } catch (Exception e) {
        }
        return validation;
    }

    public static String cleanRut(String rut){
        return rut.replaceAll("\\.", "");
    }
}
