package com.magnificent.lang;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author : Huang Gen
 * @Since : Created in 2021/11/15 8:28
 * @Description : 数字类型转换
 */
public class NumberTypes {

    public static Integer toInteger(Object input) {
        if (null == input) {
            return null;
        } else if (input instanceof Integer || input.getClass() == int.class) {
            return (Integer) input;
        } else if (input instanceof BigDecimal) {
            return ((BigDecimal) input).toBigInteger().intValue();
        } else if (input instanceof String) {
            if (StringUtils.isBlank((String) input)) {
                return null;
            }
            assertNumber(input);
            return Integer.parseInt((String) input);
        } else {
            assertNumber(input);
            return Integer.parseInt(String.valueOf(input));
        }
    }

    public static Long toLong(Object input) {
        if (null == input) {
            return null;
        } else if (input instanceof Long || input.getClass() == long.class) {
            return (Long) input;
        } else if (input instanceof Integer || input.getClass() == int.class) {
            return ((Integer) input).longValue();
        } else if (input instanceof BigInteger) {
            return ((BigInteger) input).longValue();
        } else if (input instanceof BigDecimal) {
            return null;
        }
        return null;
    }

    private static void assertNumber(Object input) {
        if (null == input) {
            return;
        }
        boolean isNumberDights = NumberUtils.isCreatable(String.valueOf(input));
        if (!isNumberDights) {
            try {
                throw new Exception("输入应该为数字类型");
            } catch (Exception e) {
            }
        }
    }


}
