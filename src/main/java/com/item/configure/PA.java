package com.item.configure;


import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.Map;

/**
 * 公共属性(public attribute) 简写 PA
 */
public class PA {

    /**
     * 今天网站访问量
     */
    public static int TODAYPV=0;

    /**
     * 今日上线数
     */
    public static int TODAYONLINE=0;

    /**
     * account session
     */
    public static Map<String, HttpSession> AS =new Hashtable<>();

    /**
     * websocketId account
     */
    public static Map<String,String> WA=new Hashtable<>();

    /**
     * account websocket
     */
    public static Map<String,String> AW=new Hashtable<>();

    /**
     * webSocketSessionId webSocketSession
     */
    public static Map<String, WebSocketSession> WW=new Hashtable<>();


}
