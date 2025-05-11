package com.zy.aicamp.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author zy
 * @Date 2025/5/9 19:08
 **/
@Configuration
public class AIConfiguration {

    @Bean
    public ChatMemory chatMemory(){
        //本质是一个Map把会话id和Message给存了起来
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OllamaChatModel model, ChatMemory chatMemory){
        return ChatClient.builder(model)
                .defaultSystem("你是一个严厉的小学数学老师，叫孙晓晓，请以数学老师的身份和语气回答问题")
                //用来专门记录日志的一个环绕增强
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }
}
