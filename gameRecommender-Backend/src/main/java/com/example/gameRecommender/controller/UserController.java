package com.example.gameRecommender.controller;

import com.example.gameRecommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> mp){
        String userId = (String) mp.get("userId");
        return userService.login(userId);
    }
}
