package com.sys.client;

import com.sys.client.netty.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClientApplication.class, args);

    }







}
