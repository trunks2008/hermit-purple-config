package com.cn.hermimt.purple.core;

import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 14:10
 **/
public class VariablePool {

    public static Map<String, Map<Class,String>> pool=new HashMap<>();
    private static final String regex="^(\\$\\{)(.)+(\\})$";
    private static Pattern pattern;

    static{
        pattern=Pattern.compile(regex);
    }

    public static void add(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
//        String clazzName = clazz.getName();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)){
                Value annotation = field.getAnnotation(Value.class);
                // present in ${...},use regex to check
                String annoValue = annotation.value();
                if (!pattern.matcher(annoValue).matches())
                    continue;

                annoValue=annoValue.replace("${","");
                annoValue=annoValue.substring(0,annoValue.length()-1);

                Map<Class,String> clazzMap = Optional.ofNullable(pool.get(annoValue))
                        .orElse(new HashMap<>());
                clazzMap.put(clazz,field.getName());
                pool.put(annoValue,clazzMap);
            }
        }
    }

    public static Map<String, Map<Class,String>> getPool() {
        return pool;
    }
}
