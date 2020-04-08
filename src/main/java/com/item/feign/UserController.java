package com.item.feign;


import com.item.bean.User;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "SERVICE")
public interface UserController {

    @RequestMapping(value = "/user/login",consumes = "application/json",method = {RequestMethod.POST})
    public Map<String,String> userLogin(@RequestBody User user);

    @PostMapping(value = "/user/signUp",consumes = "application/json")
    public Boolean signUpUser(@RequestBody User user);

    @PostMapping(value = "/user/updatePwd",consumes = "application/json")
    public boolean updatePwd(@RequestBody User user);
}
