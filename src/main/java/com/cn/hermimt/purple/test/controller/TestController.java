package com.cn.hermimt.purple.test.controller;

import com.cn.hermimt.purple.test.service.UserDeptService;
import com.cn.hermimt.purple.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hermit-purple-config
 * @author: Hydra
 * @create: 2022-01-24 10:39
 **/
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    UserDeptService userDeptService;

    @GetMapping("name")
    public String getUserName(){
        return " name: " +userService.getName()+"\r\n age:  "+userService.getAge();
    }

    @GetMapping("name2")
    public String getUserDeptName(){
        return "name: " +userDeptService.getName();
    }
}
