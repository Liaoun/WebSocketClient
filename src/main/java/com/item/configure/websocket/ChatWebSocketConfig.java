package com.item.configure.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Component
@EnableWebSocket
public class ChatWebSocketConfig implements WebSocketConfigurer {
    @Autowired
    ChatWebSocketHandler handler;
    @Autowired
    ChatWebSocketIntercept intercept;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler,"/ws").addInterceptors(intercept).setAllowedOrigins("http://localhost:8089");


    }
}
