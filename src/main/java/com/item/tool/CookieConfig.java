package com.item.tool;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CookieConfig<T>{

    /**
     * redis session共享配置 param(参数)
     */
    public HttpEntity get_httpHeader(HttpServletRequest request, Map<String,Object> param) throws Exception{
        //手动设置header
        HttpHeaders header = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        Cookie[] cc = request.getCookies();
        for (int i = 0; i < cc.length; i++)
        {
            cookies.add(cc[i].getName() + "=" + cc[i].getValue());
            System.out.println(cc[i].getName() + "=" + cc[i].getValue());
        }
        header.put(HttpHeaders.COOKIE, cookies);
        header.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        header.put(HttpHeaders.CONTENT_ENCODING, Collections.singletonList("UTF-8"));
        header.put(HttpHeaders.ACCEPT_ENCODING, Collections.singletonList("UTF-8"));
        header.put(HttpHeaders.TRANSFER_ENCODING, Collections.singletonList("UTF-8"));
//        header.add("user",param);
//        =new HashMap<String,Object>();
//        System.out.println(param);
//        Map<String,String> map=new HashMap<>();
//        map.put()
        HttpEntity httpEntity = new HttpEntity(param, header);
        return httpEntity;
    }


}
