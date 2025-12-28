package com.example.gameRecommender.service.impl;

import com.example.gameRecommender.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Mapper
    String steamApiKey=System.getenv("STEAM_API_KEY");

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
            int appId = gameNode.path("appid").asInt();
            String name = gameNode.path("name").asText();
            int totalPlaytime = gameNode.path("playtime_forever").asInt();
//            System.out.println(name+" "+appId+" "+totalPlaytime);
            res=res+"有"+name+",游戏时长为"+appId+"分钟；";
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


}
