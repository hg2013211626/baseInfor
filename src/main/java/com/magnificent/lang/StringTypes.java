package com.magnificent.lang;

import java.util.Date;
import java.util.Locale;

public class StringTypes {

    public static String toString(Object input) {
        if (null == input) {
            return null;
        } else if (input instanceof Date) {
            return DateTimeTypes.toDateTimeString(input);
        } else {
            return input.toString();
        }
    }

    public static String toLowerCamelCase(String input) {
        StringBuilder output = new StringBuilder();
        boolean upper = false;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (upper) {
                if (current >= 'A' && current <= 'z') {
                    output.append(String.valueOf(current).toUpperCase(Locale.ENGLISH));
                } else if (current >= '0' && current <= '9') {
                    output.append(current);
                }
            } else {
                if (current >= 'A' && current <= 'Z') {
                    output.append(String.valueOf(current).toLowerCase(Locale.ENGLISH));
                } else if (current >= 'a' && current <= 'z') {
                    output.append(current);
                } else if (current >= '0' && current <= '9') {
                    output.append(current);
                    upper = true;
                } else {
                    upper = true;
                }
            }
        }
        return output.toString();
    }

    public static String toUpperCamelCase(String input) {
        String lower = toLowerCamelCase(input);
        if (input.length() > 1) {
            return String.valueOf(lower.charAt(0)).toUpperCase(Locale.ENGLISH) + lower.substring(1);
        } else if (input.length() == 1) {
            return String.valueOf(lower.charAt(0)).toUpperCase(Locale.ENGLISH);
        }
        return input;
    }

    /**
     * 驼峰转蛇形
     */
    public static String camelCaseSnakeCase(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = camelCaseStr.toCharArray();
        for (char aChar : chars) {
            if (aChar >= 'A' && aChar <= 'Z') {
                sb.append("_").append(String.valueOf(aChar).toLowerCase());
            } else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }
}
