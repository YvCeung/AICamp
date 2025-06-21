package com.zy.aicamp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author zy
 * @Date 2025/6/8 23:14
 **/
@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test1")
    public String test1(@RequestParam String name,@RequestParam int age){
        return name + age;
    }

    @GetMapping("/test2")
    public String test2(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age){
        return name + age;
    }

    @GetMapping("/test3")
    public String test3(@NotNull @RequestParam(name = "stuName") String name, @RequestParam(value = "stuAge") int age){
        return name + age;
    }
}
