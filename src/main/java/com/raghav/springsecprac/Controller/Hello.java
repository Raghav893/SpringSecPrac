package com.raghav.springsecprac.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/")
    void hello(){
        System.out.println("HEllo");
    }
}
