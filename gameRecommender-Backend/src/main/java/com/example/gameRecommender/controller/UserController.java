package com.example.gameRecommender.controller;

import com.example.gameRecommender.model.GamePage;
import com.example.gameRecommender.model.Result;
import com.example.gameRecommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getData")
    public Result getPage(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="5")Integer pageSize){
        GamePage gamePage=userService.Page(page, pageSize);
        return Result.success(gamePage);
    }

}
