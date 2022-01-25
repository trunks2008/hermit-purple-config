package com.cn.hermimt.purple.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 10:38
 **/
@Service
public class UserDeptService {

    @Value("${person.name}")
    String pname;

    public String getName(){
        return pname;
    }

}
