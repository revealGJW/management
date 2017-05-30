package com.cars.management.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GJW on 2017/5/29.
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<>();
    public void set(String key, Object value) {
        objs.put(key, value);
    }
    public Object get(String key){
        return objs.get(key);
    }
}
