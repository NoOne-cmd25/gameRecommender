package com.example.gameRecommender.service.impl;

import com.example.gameRecommender.mapper.UserMapper;
import com.example.gameRecommender.model.Game;
import com.example.gameRecommender.model.GamePage;
import com.example.gameRecommender.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Mapper
    String steamApiKey=System.getenv("STEAM_API_KEY");

    @Autowired
    UserMapper userMapper;

    @SneakyThrows
    public String getGameLib(String id)
    {
        String res="";
        String url="http://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?"
                +"key="+steamApiKey+"&"
                +"steamid="+id+"&"
                +"include_appinfo=1&include_played_free_games=1";
        HttpClient httpclient=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpclient.send(request,HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        // 获取响应体字符串
        String jsonString = response.body();

// 创建 ObjectMapper 实例
        ObjectMapper mapper = new ObjectMapper();

// 解析为 JsonNode（通用 JSON 树模型）
        JsonNode rootNode = mapper.readTree(jsonString);

// 处理 JSON 数据

        JsonNode responseNode = rootNode.path("response");
        JsonNode gamesArray = responseNode.path("games");

        for(JsonNode gameNode:gamesArray)
        {
            int playtime_forever = gameNode.path("playtime_forever").asInt();
            String name = gameNode.path("name").asText();
            res=res+"有"+name+",游戏时长为"+ playtime_forever +"分钟；";
        }
//        if (rootNode.isArray()) {
//            for (JsonNode node : rootNode) {
//                System.out.printf(node.toString());
//            }
//        } else if (rootNode.isObject()) {
//            System.out.printf(rootNode.toString());
//            String title = rootNode.get("game_count").asText();
//            System.out.println(title);
//        }


        System.out.println(response.body());
        return res;
    }

    @SneakyThrows
    public String login(String id)
    {
        String res="";
        String url="http://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?"
                +"key="+steamApiKey+"&"
                +"steamid="+id+"&"
                +"include_appinfo=1&include_played_free_games=1";
        HttpClient httpclient=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = httpclient.send(request,HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        // 获取响应体字符串
        String jsonString = response.body();

// 创建 ObjectMapper 实例
        ObjectMapper mapper = new ObjectMapper();

// 解析为 JsonNode（通用 JSON 树模型）
        JsonNode rootNode = mapper.readTree(jsonString);

// 处理 JSON 数据

        JsonNode responseNode = rootNode.path("response");
        JsonNode gamesArray = responseNode.path("games");

        for(JsonNode gameNode:gamesArray)
        {
            Game game = new Game();
            int playtime_forever = gameNode.path("playtime_forever").asInt();
            String name = gameNode.path("name").asText();
            game.setGameName(name);
            game.setGameTime(playtime_forever);
            game.setSteamId(id);
            userMapper.insert(game);
        }

        return "绑定成功！";
    }

    public GamePage Page(Integer start, Integer pageSize)
    {
        //获取总的记录数；
        Integer total=userMapper.count();

        //获取分页查询结果列表；
        start = (start-1)*pageSize;
        List<Game> gameList=userMapper.page(start, pageSize);

        GamePage gamePage=new GamePage();
        gamePage.setTotal(total);
        gamePage.setGameList(gameList);

        return gamePage;
    }

}
