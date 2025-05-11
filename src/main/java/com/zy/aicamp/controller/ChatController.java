package com.zy.aicamp.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description
 * @Author zy
 * @Date 2025/5/9 19:10
 **/
@RestController
@RequestMapping("/ai")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @RequestMapping("/chat")
    public String chat(String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * 流式返回
     * @param prompt
     * @return
     */
    @RequestMapping(value = "/chatstream",produces = "text/html;charset=utf-8")
    public Flux<String> chatstream(String prompt, @RequestParam(name = "chatId")String chatId){
        return chatClient.prompt()
                //存储会话id
                .advisors(advisorSpec -> advisorSpec.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                .user(prompt)
                .stream().content();
    }
}
