package com.magnificent.lang;

import java.math.BigDecimal;

/**
 * @author ：Huang Gen
 * @Date ：Created in 2021/11/10 0:48
 * @Description：布尔类型处理类
 */
public class BooleanTypes {

    public static Boolean toBoolean(Object input) {
        if (null == input) {
            return null;
        } else if (input instanceof Boolean) {
            return (Boolean) input;
        } else if (Integer.valueOf(0).equals(input)) {
            return false;
        } else if (BigDecimal.valueOf(0).equals(input)) {
            return false;
        } else if ("false".equalsIgnoreCase(input.toString())) {
            return false;
        } else {
            return true;
        }

    }

}
