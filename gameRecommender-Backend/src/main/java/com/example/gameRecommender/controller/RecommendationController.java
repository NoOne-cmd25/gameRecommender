package com.example.gameRecommender.controller;

import com.example.gameRecommender.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/doRecommendation")
    public String doRecommendation(@RequestBody Map<String, Object>mp)
    {
        String userId = (String) mp.get("userId");
        String userPrompt = (String) mp.get("userPrompt");

        System.out.println(userId);
        System.out.println(userPrompt);

        return recommendationService.doRecommendation(userId, userPrompt);
//        return recommendationService.doRecommendation("76561199026086579", "");
//        return "";
    }

}
