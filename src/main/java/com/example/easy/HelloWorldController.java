package com.example.easy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zachary on 2018/11/27.
 */
@RequestMapping("/helloWorld")
@RestController
public class HelloWorldController {
    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return "Hi, " + name;
    }
}
