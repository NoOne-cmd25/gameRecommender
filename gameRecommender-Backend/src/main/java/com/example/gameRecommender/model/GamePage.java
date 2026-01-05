package com.example.gameRecommender.model;

import java.util.List;

public class GamePage {
    private int total;
    private List<Game> gameList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
