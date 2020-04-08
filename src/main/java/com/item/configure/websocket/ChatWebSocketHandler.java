package com.item.configure.websocket;

import com.alibaba.fastjson.JSON;
import com.item.bean.Chat;
import com.item.configure.PA;
import com.item.feign.ChatController;
import com.item.tool.StringToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@Component
public class ChatWebSocketHandler implements WebSocketHandler {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    ChatController control;
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

        Map<String,Object> map=webSocketSession.getAttributes();

        String account=map.get("userAccount").toString();

        String username=map.get("userName").toString();

        PA.WW.put(webSocketSession.getId(),webSocketSession);

        PA.AW.put(account,webSocketSession.getId());

        PA.WA.put(webSocketSession.getId(),account);

        System.out.println(username+"------connection build");

    }



    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> message) throws Exception {

        String sendAccount=webSocketSession.getAttributes().get("userAccount").toString();

        Map<String,Object> map= JSON.parseObject(message.getPayload().toString(),Map.class);
        Object he=map.get("header");
        Object bo=map.get("body");

        Map<String,Object> header= JSON.parseObject(he.toString(),Map.class);
        Map<String,Object> body= JSON.parseObject(bo.toString(),Map.class);

        String status=header.get("status").toString();
        String acceptAccount=header.get("accept").toString();
        String text=body.get("message").toString();

        addToDatabase(StringToBean.stringToChat(sendAccount,acceptAccount,text));

        System.out.println(acceptAccount);

        sendMessageToUser(acceptAccount,message);


    }


    /**
     * 把聊天添加到数据库
     * @param chat
     * @return
     */
    public boolean addToDatabase(Chat chat){
        boolean p=false;
        p=control.addChatRecord(chat);
        return p;
    }
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        String id= webSocketSession.getId();
        String account= PA.WA.get(id);

        PA.WW.remove(id);
        PA.WA.remove(id);
        PA.AW.remove(account);
        System.out.println(PA.AW.get(account));


        new Thread(()->{

            try {
                //判断用户是断开连接还是下线
                Thread.sleep(5000);
                if (PA.AW.get(account)==null){
                    System.out.println(account+"-------connection closed");
                    PA.AS.remove(account);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageAll(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage, boolean system) throws IOException {
        for (WebSocketSession session: PA.WW.values()) {
            new Thread(()->{
                try {
                    if (!session.getId().equals(webSocketSession.getId()))
                        session.sendMessage(webSocketMessage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
    public void sendMessageToUser(String acceptAccount, WebSocketMessage<?> webSocketMessage){

        WebSocketSession webSocketSession=null;
        try {

            String sessionId= PA.AW.get(acceptAccount);

            webSocketSession= PA.WW.get(sessionId);

            System.out.println(webSocketSession==null);

            if (webSocketSession==null)
                return;

            webSocketSession.sendMessage(webSocketMessage);

        }catch (IOException e){
            logger.info("message send error path com.item.configure.sendMessageToUser");
        }
        catch (Exception e){
            logger.info("webSocketSession is empty user no online");
            return;
        }


    }
}
