package com.template.base.net.Interceptor;

import java.util.Comparator;
import java.util.TreeMap;

public class SortMap extends TreeMap<String, String> {
    public SortMap() {
        // TODO 根据实际排序规则设置
        super(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                //return -obj2.compareTo(obj1); //升序
                return obj2.compareTo(obj1); //降序
            }
        });
    }
}