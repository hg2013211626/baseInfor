package com.magnificent.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author : Huang Gen
 * @Since : Created in 2021/11/14 10:27
 * @Description : 集成工具类
 */
public class CollectionTypes {

    public static <T> List<T> blankList() {
        return new ArrayList<>(0);
    }

    public static boolean isArray(Object value) {
        if (null == value) {
            return false;
        }
        if (value.getClass().isArray()) {
            return true;
        } else {
            return value instanceof Collection;
        }
    }

    public static int getSize(Object value) {
        if (null == value) {
            return 0;
        }
        if (value.getClass().isArray()) {
            return ((Object[]) value).length;
        } else if (value instanceof Collection) {
            return ((Collection) value).size();
        }
        return 0;
    }

    /**
     * 遍历数组或者集合
     *
     * @param value   数组或者集合
     * @param consumer 便利消费方法
     */
    public static void forEach(Object value, Consumer<Object> consumer) {
        if(null == value) {
            return;
        }
        if(value instanceof Collection) {
            Collection<?> list = (Collection<?>) value;
            for (Object obj : list) {
                consumer.accept(obj);
            }
        } else if(value.getClass().isArray()) {
            Object[] array = (Object[]) value;
            for(Object obj: array) {
                consumer.accept(obj);
            }
        }
    }


}
