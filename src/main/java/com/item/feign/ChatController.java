package com.item.feign;
import com.item.bean.Chat;
import com.item.bean.User;
import com.item.configure.PA;
import feign.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "SERVICE")
public interface ChatController {


    @PostMapping("getLastChatContent")
    List<Chat> getLastChatContent(@RequestParam("send") String send, @RequestParam("accept") String accpet);

    @PostMapping("getLastChatAcceptId")
    String getLastChatAcceptId(@RequestParam("user") User user);

    @PostMapping("addChatRecord")
    boolean addChatRecord(@RequestParam("chat") Chat chat);
}
