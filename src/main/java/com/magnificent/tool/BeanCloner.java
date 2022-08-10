package com.magnificent.tool;

import com.alibaba.fastjson.JSON;
import com.rits.cloning.Cloner;

/**
 * @Author : Huang Gen
 * @Since : Created in 2022/5/26 23:14
 * @Description : 克隆相关代码
 */
public class BeanCloner {
    private static Cloner cloner = new Cloner();

    public static <T> T deepClone(T obj) {
        try {
            return cloner.deepClone(obj);
        } catch (Throwable e) {
            return (T) JSON.parse(JSON.toJSONString(obj));
        }
    }

    public static <T> T shallowClone(T obj) {
        return cloner.shallowClone(obj);
    }



}
