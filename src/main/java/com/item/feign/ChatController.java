package com.item.feign;
import com.item.bean.Chat;
import com.item.bean.User;
import com.item.configure.PA;
import feign.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Configuration
@FeignClient(value = "SERVICE")
public interface ChatController {


    @RequestMapping("/chat/getLastChatContent")
    List<Chat> getLastChatContent(@Param("send") String send, @Param("accept") String accpet);

    @RequestMapping("/chat/getLastChatAcceptId")
    String getLastChatAcceptId(@Param("user") User user);

    @RequestMapping("/chat/addChatRecord")
    boolean addChatRecord(@Param("chat") Chat chat);
}
