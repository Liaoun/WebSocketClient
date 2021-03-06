package com.item.configure.websocket;

import com.item.bean.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class ChatWebSocketIntercept implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        if (serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest request=(ServletServerHttpRequest)serverHttpRequest;
            HttpSession session=request.getServletRequest().getSession(false);
            if (session == null) {
                System.out.println("进入判断");
                return false;
            }

            User user= (User) session.getAttribute("user");
            if (user!=null){
                map.put("userAccount",user.getAccount());
                map.put("userName",user.getUsername());
                return true;

            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
