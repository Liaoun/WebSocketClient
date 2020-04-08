package com.item;

import com.item.bean.custom.Server;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

    @Autowired
    Server server;
    @Test
    void contextLoads() {
        System.out.println(server.getName());
    }

}
