package com.zzz.draw.content;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/9/22.
 */
public class ApplicationContext {
    public static ApplicationContext INSTANCE;
    Map<String,Object> map = new ConcurrentHashMap<String, Object>();
    public static ApplicationContext getInstance() {
        return INSTANCE;
    }
    public ApplicationContext(){
        INSTANCE = this;
    }

    public void add(String name,Object val){
        map.put(name,val);
    }
    public Object get(String name){
        return map.get(name);
    }
}
