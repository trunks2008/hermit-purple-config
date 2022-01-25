package com.cn.hermimt.purple.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 10:38
 **/
@Service
public class UserService {

    @Value("${person.name}")
    String name;

    @Value("${person.age}")
    Integer age;

    public String getName(){
        return name;
    }

    public Integer getAge(){
        return age;
    }

}
