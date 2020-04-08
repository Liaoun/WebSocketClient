package com.item.bean.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class Server {
    private String name;

    public Server(String name) {
        this.name = name;
    }

    public Server() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
