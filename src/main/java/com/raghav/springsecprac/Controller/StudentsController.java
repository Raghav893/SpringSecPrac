package com.raghav.springsecprac.Controller;


import com.raghav.springsecprac.Entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StudentsController {
    private  List<Student> Studentss = new ArrayList<>((List.of(
       new Student(1,"Raghav",20),
            new Student(2,"Aditya",25)
    )));

    @GetMapping("/students")
    private List<Student> getStudents(){
        return Studentss;
    }
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    private List<Student> addStudent(@RequestBody Student student){
        Studentss.add(student);
        return Studentss;
    }
}
