package com.sundev.music.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundev.music.model.Users;
import com.sundev.music.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class PublicController {

    @Autowired
    private UserService service;
 
    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }
    
    @GetMapping("/students")
    public List<String> getStudents(HttpRequest request) {
        return new ArrayList<String>(Arrays.asList("Hello","there"));
    }

    @PostMapping("/add_user")
    public Users postMethodName(@RequestBody Users users) {
        return service.saveUsers(users);
    }

    @PostMapping("/user-login")
    public String login(@RequestBody Users users) {
        return service.verify(users);
    }
    
    
}
