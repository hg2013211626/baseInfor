package com.magnificent.lang;

import org.apache.commons.lang3.math.NumberUtils;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @Author : Huang Gen
 * @Since : Created in 2021/11/10 8:22
 * @Description : map的工具类
 */
public class MapTypes {
    public static <T, K> Map<T, K> blankMap() {
        return new HashMap<T, K>(0);
    }

    public static Object path(Map<?,?> map, String path){
        String[] steps = path.split("\\.");

        Object temp = map;
        for(String step: steps){
            if(temp instanceof Map) {
                Map map0 = (Map) temp;
                temp = map0.get(step);
            } else if(temp instanceof List && NumberUtils.isDigits(step)) {
                int index = NumberUtils.toInt(step);
                List<?> list = (List<?>) temp;
                if(index < list.size()) {
                    temp = list.get(index);
                }
            }else {
                return null;
            }
        }
        return temp;
    }

    public static Map<String, Object> asMap(Object... args) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(String.valueOf(args[i]), args[i + 1]);
        }
        return map;
    }

    public static <K,V> String join(Map<K,V> map, String separator, String keyValueSeparator, boolean isIngoreNull, String ... otherParams){
        final StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        if(map != null && !map.isEmpty()) {
            for(Map.Entry<K,V> entry: map.entrySet()) {
                if((!isIngoreNull) || entry.getKey() != null && entry.getValue() != null) {
                    if(isFirst) {
                        isFirst  = false;
                    } else  {
                        stringBuilder.append(separator);
                    }
                    stringBuilder.append(entry.getKey()).append(keyValueSeparator).append(entry.getValue());
                }
            }
        }
        if(otherParams != null && otherParams.length > 0) {
            for(String otherParam:otherParams) {
                stringBuilder.append(otherParam);
            }
        }
        return stringBuilder.toString();
    }

    public static <K,V> Map sort(Map<K, V> map) {
        return sort(map, null);
    }

    public static <K, V> Map sort(Map<K,V> map, Comparator comparator) {
        if(null == map) {
            return null;
        }

        TreeMap<K, V>  result = new TreeMap(comparator);
        result.putAll(map);
        return result;
    }

}
