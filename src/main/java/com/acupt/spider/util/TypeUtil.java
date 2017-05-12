package com.acupt.spider.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liujie on 2017/5/11.
 */
public class TypeUtil {

    private static Map<String, String> bilibiliType = new ConcurrentHashMap<String, String>();

    public static String getTypeByBilibiliTid(String tid) {
        String type = bilibiliType.get(tid);
        if (type != null) {
            return type;
        }
        return "其它";
    }

    public static String putBilibiliType(String tid, String type) {
        return bilibiliType.put(tid, type);
    }


}
