package com.example.WebSocketsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class WebSocketsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketsServerApplication.class, args);

    }
}
