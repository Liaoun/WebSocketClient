package com.item.feign;


import com.item.bean.User;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Configuration
@FeignClient(value = "SERVICE")
public interface UserController {

    @PostMapping("/user/login")
    public Map<String,String> userLogin(@Param("us") User us);

    @PostMapping("/user/signUp")
    public Boolean signUpUser(@Param("user") User user);

    @PostMapping("/user/updatePwd")
    public boolean updatePwd(@Param("user") User user);
}
