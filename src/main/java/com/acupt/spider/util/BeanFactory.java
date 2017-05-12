package com.acupt.spider.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liujie on 2017/5/11.
 */
public class BeanFactory {

    private static Map<Class, Object> classBeans = new ConcurrentHashMap<Class, Object>();

    public static <T> T getBean(Class<T> beanClass) {
        T obj = (T) classBeans.get(beanClass);
        if (obj == null) {
            try {
                obj = beanClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            classBeans.put(obj.getClass(), obj);
        }
        return obj;
    }
}
