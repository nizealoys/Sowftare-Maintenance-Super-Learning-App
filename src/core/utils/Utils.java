package src.core.utils;

public class Utils {

    public static boolean isNum(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
