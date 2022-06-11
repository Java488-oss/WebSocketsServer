package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketsServerApplication {

	private static UserService userService;

	WebSocketsServerApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsServerApplication.class, args);
	}
}
