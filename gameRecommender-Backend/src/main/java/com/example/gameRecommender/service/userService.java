package com.example.gameRecommender.service;

import com.example.gameRecommender.model.GamePage;

import java.util.List;

public interface UserService {
    public String  getGameLib(String id);

    public String login(String steamId);

    public GamePage Page(Integer start, Integer pageSize);
}
