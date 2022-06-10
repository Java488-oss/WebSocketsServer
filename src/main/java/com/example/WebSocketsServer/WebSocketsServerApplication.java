package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import jakarta.transaction.Transactional;
import java.util.List;

@SpringBootApplication
public class WebSocketsServerApplication {

	private static UserService userService;

	WebSocketsServerApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsServerApplication.class, args);


//		List<UserEntity> userEntityList = userService.findAll();
//		userEntityList.forEach(list->{
//			System.out.println(list.getUser()+" "+list.getPass());
//		});
	}
}
