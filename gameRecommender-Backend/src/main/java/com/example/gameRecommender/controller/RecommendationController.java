package com.example.gameRecommender.controller;

import com.example.gameRecommender.service.RecommendationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/doRecommendation")
    public String doRecommendation(@RequestParam Map<String,String>mp) {
        return recommendationService.doRecommendation(mp.get("userId"), mp.get("userPrompt"));
    }

}
