package com.example.gameRecommender.service.impl;

import com.example.gameRecommender.service.RecommendationService;

import com.example.gameRecommender.service.UserService;
import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionRequest;
import com.volcengine.ark.runtime.model.bot.completion.chat.BotChatCompletionResult;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessage;
import com.volcengine.ark.runtime.model.completion.chat.ChatMessageRole;
import com.volcengine.ark.runtime.service.ArkService;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Resource
    private ArkService service;

    @Autowired
    UserService userService;


    @Override
    public String doRecommendation(String userId,String pref)
    {
        System.out.println("\n----- standard request -----");

//        System.out.println(userService.getGameLib(userId));
        String UserPrompt="我的游戏库有《远星物语》";
        UserPrompt="我的游戏库";
        UserPrompt=UserPrompt+userService.getGameLib(userId);
        System.out.println(UserPrompt);

        final List<ChatMessage> messages = new ArrayList<>();
//        final ChatMessage systemMessage = ChatMessage.builder().role(ChatMessageRole.SYSTEM).content(SystemPrompt).build();
        final ChatMessage userMessage = ChatMessage.builder().role(ChatMessageRole.USER).content(UserPrompt).build();
//        messages.add(systemMessage);
        messages.add(userMessage);


        BotChatCompletionRequest chatCompletionRequest = BotChatCompletionRequest.builder()
                .botId("bot-20251223114851-rxgzx")
                .messages(messages)
                .build();



        BotChatCompletionResult chatCompletionResult =  service.createBotChatCompletion(chatCompletionRequest);
        chatCompletionResult.getChoices().forEach(choice -> System.out.println(choice.getMessage().getContent()));

//        return "";

        //        // the references example
        if (chatCompletionResult.getReferences() != null) {
            chatCompletionResult.getReferences().forEach(ref -> System.out.println(ref.getUrl()));
        }
        return chatCompletionResult.getChoices().get(0).getMessage().getContent().toString();
//        return "";
    }
}
